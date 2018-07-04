package branchoperations;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;

import versiontree.versiontree;
import getfiles.getfiles;
import treeoperations.getversiontree;

import static treeoperations.gettreeaslist.getversiontreeaslist;


public class latestbranchdiff {

    static String savefilepath = loadenv.loadenv.getSavefilepath()+"Staging\\";

    public void latestbranchdiff(String projectname, String branch) {
        try {
            String latest = new getlatestversion().getlatestversion(projectname, branch);

            String stageshark = getfiles.getsharkfile(loadenv.loadenv.getSavefilepath() + "Staging\\" + projectname, projectname);


            String latestshark = getfiles.getsharkfile(loadenv.loadenv.getSavefilepath() + projectname + "\\branch\\" + branch + "\\" + latest, projectname);

            System.out.println(stageshark);
            System.out.println(latestshark);

            if (stageshark.compareTo(latestshark) == 0) {
                System.out.println("No difference");
            } else {
                String stageser = getfiles.getserfile(loadenv.loadenv.getSavefilepath() + "\\Staging\\" + projectname, projectname);

                String latestserpath=loadenv.loadenv.getSavefilepath() + projectname + "\\branch\\" + branch + "\\" + latest;
                System.out.println(latestserpath);
                String latestser = getfiles.getserfile(latestserpath,projectname);

                versiontree stagetree = getversiontree.getversiontree(loadenv.loadenv.getSavefilepath() + "\\Staging\\" + projectname + "\\" + stageser);
                versiontree latesttree = getversiontree.getversiontree(loadenv.loadenv.getSavefilepath() + projectname + "\\branch\\" + branch + "\\" + latest + "\\" + latestser);


                storedifference(stagetree,latesttree,projectname);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    private void storedifference(versiontree stagetree, versiontree latesttree,String projectname) {
        LinkedList<listtree> stagetreelist= getversiontreeaslist(stagetree);
        LinkedList<listtree> latesttreelist=getversiontreeaslist(latesttree);

        Iterator itr = stagetreelist.iterator();

        LinkedList<listtree> push=new LinkedList<listtree>();

        while(itr.hasNext()) {
            listtree element = (listtree)itr.next();
            listtree match=treematch(latesttreelist,element.getPathname(),element.getSha());
            if(element.isFolder())
            {
                continue;
            }
            if(match==null) {
                System.out.println(element.getPathname()+element.getFilename());
                push.add(element);
            }
            else if(element.getSha().compareTo(match.getSha())!=0) {
                System.out.println(element.getPathname()+element.getFilename()+"     "+match.getPathname()+element.getFilename());
                push.add(element);
            }
        }

        try {
            String pushpath=savefilepath + projectname + "\\" + projectname + ".push";
            FileOutputStream fileOut =
                    new FileOutputStream(pushpath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(push);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + pushpath);
        } catch (IOException i) {
            i.printStackTrace();
        }


    }


    static listtree treematch(LinkedList<listtree> list, String pathname,String sha) {

        for (listtree o : list) {
            if (o.getPathname().compareTo(pathname) == 0 && o.getSha().compareTo(sha) == 0) return o;
        }


        return null;
    }











}
