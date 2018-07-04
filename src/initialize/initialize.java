package initialize;

import branchoperations.createbranch;
import branchoperations.listtree;
import copyfiles.copyclass;
import treeoperations.getversiontree;
import versioning.versioning_staging;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import getfiles.getfiles;
import versiontree.versiontree;

import static deletefiles.deletedir.deleteDir;
import static treeoperations.gettreeaslist.getversiontreeaslist;

public class initialize {
    private String path;
    private String branch;
private String projectfolder;

    public initialize(String rootpath, String projectfolder) {
        this.path = rootpath + "\\" + projectfolder;
        this.branch = "Main";
        this.projectfolder=projectfolder;
        System.out.println(path);

    }

    public void init() throws Exception {
        String stagepath=loadenv.loadenv.getSavefilepath() + "Staging\\"+projectfolder;
        String filespath=loadenv.loadenv.getSavefilepath() + projectfolder + "\\files";
        deleteDir(new File(loadenv.loadenv.getSavefilepath()+ projectfolder));

        new File(filespath).mkdirs();


        new createbranch().createbranch(projectfolder,branch);
        String versionfolder = loadenv.loadenv.getSavefilepath() + projectfolder + "\\branch\\" + branch + "\\" + Long.toString(System.currentTimeMillis() / 1000L);
        new File(versionfolder).mkdirs();

        versioning_staging.initalize(path,branch);


        String stageshark = getfiles.getsharkfile(loadenv.loadenv.getSavefilepath() + "Staging\\" + projectfolder, projectfolder);
        String stageser = getfiles.getserfile(loadenv.loadenv.getSavefilepath() + "\\Staging\\" + projectfolder, projectfolder);


        copyclass.copyThreads.clear();

        System.out.println("\nVersionzed");
        copyfiles.copyclass cc =new copyfiles.copyclass(versionfolder,stageshark,new File(stagepath+"\\"+stageshark));
        Thread t = new Thread(cc);
        t.start();
        copyclass.copyThreads.add(t);

        cc =new copyfiles.copyclass(versionfolder,stageser,new File(stagepath+"\\"+stageser));
        t = new Thread(cc);
        t.start();
        copyclass.copyThreads.add(t);



        versiontree stagetree = getversiontree.getversiontree(loadenv.loadenv.getSavefilepath() + "Staging\\" + projectfolder+"\\"+stageser);

        copytreefiletopath(stagetree,filespath);

        for (int i = 0; i < copyclass.copyThreads.size(); i++)
        {
            copyclass.copyThreads.get(i).join();
        }


    }

    public void copytreefiletopath(versiontree stagetree,String filespath)
    {
        LinkedList<listtree> stagetreelist= getversiontreeaslist(stagetree);
        Iterator itr = stagetreelist.iterator();


        while(itr.hasNext()) {
            listtree element = (listtree)itr.next();

            if(element.getFilename().compareTo("Root")==0 ||element.getFilename().compareTo(projectfolder)==0)
                continue;
            if(element.isFolder())
                continue;

            String tempfilepath=filespath+"\\"+element.getFilename()+"\\"+element.getSha();
            System.out.println(tempfilepath);
            System.out.println(element.getFilename());
            String srcpath=loadenv.loadenv.getSavefilepath() + "Staging\\"+projectfolder+"\\"+element.getFilename()+"\\"+element.getSha()+"\\"+element.getFilename();
            System.out.println(srcpath);
            System.out.println(new File(srcpath).exists());

            copyfiles.copyclass cc =new copyfiles.copyclass(tempfilepath,element.getFilename(),new File(srcpath));
            Thread t = new Thread(cc);
            t.start();
            copyclass.copyThreads.add(t);
        }
    }
}
