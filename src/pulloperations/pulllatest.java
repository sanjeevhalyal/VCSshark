package pulloperations;

import branchoperations.getlatestversion;
import branchoperations.listtree;
import copyfiles.copyclass;
import treeoperations.gettreeaslist;
import treeoperations.getversiontree;
import versiontree.versiontree;

import java.io.File;
import java.util.LinkedList;

public class pulllatest {

    public  pulllatest(String path, String branch,String projectname) throws Exception {
        String latest = new getlatestversion().getlatestversion(projectname, branch);

        String latesttreepath=loadenv.loadenv.getSavefilepath()+"\\"+projectname+"\\branch\\"+branch+"\\"+latest+"\\"+projectname+".ser";

        String projectfilespath=loadenv.loadenv.getSavefilepath()+projectname+"\\files\\";

        System.out.println(path);

        File node=new File(path);
        if (node.isDirectory()) {
            File[] subNote = node.listFiles();
            for (File filename : subNote) {
                deletefiles.deletedir.deleteDir(filename);
            }
        }

        versiontree latesttree = getversiontree.getversiontree(latesttreepath);



        LinkedList<listtree> latesttreelist =gettreeaslist.getversiontreeaslist(latesttree);

        String projectroot=path.substring(0,path.length()-projectname.length());
        System.out.println(projectroot);
        copyclass.copyThreads.clear();

        for (listtree o : latesttreelist) {

            String filename=o.getFilename();
            String sha=o.getSha();

            String destpath=projectroot+o.getPathname();

            String sourcepath=projectfilespath+filename+"\\"+sha+"\\"+filename;



            if(o.isFolder())continue;
            System.out.println(destpath);
            System.out.println(filename);
            System.out.println(sourcepath);


            copyfiles.copyclass cc =new copyfiles.copyclass(destpath,filename,new File(sourcepath));
            Thread t = new Thread(cc);
            t.start();
            copyclass.copyThreads.add(t);
        }



        for (int i = 0; i < copyclass.copyThreads.size(); i++)
        {
            copyclass.copyThreads.get(i).join();
        }

    }

}
