package pushtobranch;

import branchoperations.listtree;
import copyfiles.copyclass;
import getfiles.getfiles;
import versiontree.versiontree;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;
import java.util.LinkedList;

import  treeoperations.getversiontreelist;

public class pushtobranch {

    static String savefilepath = loadenv.loadenv.getSavefilepath();

    public  pushtobranch(String projectname, String branch) throws Exception {

        String stagepath=savefilepath+"Staging\\"+projectname;
        String pushfilepath=stagepath+"\\"+projectname+".push";
        String pushpath=savefilepath+projectname+"\\files\\";
        LinkedList<versiontree> list=getversiontreelist.getversiontreelist(pushfilepath);

        Iterator itr = list.iterator();

        String versionfolder = loadenv.loadenv.getSavefilepath() + projectname + "\\branch\\" + branch + "\\" + Long.toString(System.currentTimeMillis() / 1000L);
        String stageshark = getfiles.getsharkfile(loadenv.loadenv.getSavefilepath() + "Staging\\" + projectname, projectname);
        String stageser = getfiles.getserfile(loadenv.loadenv.getSavefilepath() + "\\Staging\\" + projectname, projectname);


        System.out.println("\nVersionzed");

        copyclass.copyThreads.clear();
        copyfiles.copyclass cc =new copyfiles.copyclass(versionfolder,stageshark,new File(stagepath+"\\"+stageshark));
        Thread t = new Thread(cc);
        t.start();
        copyclass.copyThreads.add(t);

        cc =new copyfiles.copyclass(versionfolder,stageser,new File(stagepath+"\\"+stageser));
        t = new Thread(cc);
        t.start();
        copyclass.copyThreads.add(t);

        for (int i = 0; i < copyclass.copyThreads.size(); i++)
        {
            copyclass.copyThreads.get(i).join();
        }

        while(itr.hasNext()) {
            listtree element = (listtree)itr.next();
            String destpath=pushpath+element.getFilename()+"\\"+element.getSha();
            new File(destpath).mkdirs();
            String sourcefile=stagepath+"\\"+element.getFilename()+"\\"+element.getSha()+"\\"+element.getFilename();
            File source=new File(sourcefile);

            cc = new copyclass(destpath, element.getFilename(), source);
            t = new Thread(cc);
            t.start();

        }




    }

}
