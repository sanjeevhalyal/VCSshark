package versioning;


import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import checksum.Getchecksum.*;
import versiontree.versiontree;
import versiontree.versiontreeroot;
import copyfiles.copyclass;

import static deletefiles.deletedir.deleteDir;


public class versioning_staging {

    static String savefilepath = loadenv.loadenv.getSavefilepath()+"Staging\\";


    public static void initalize(String path, String branch) throws IOException, NoSuchAlgorithmException, InterruptedException {


        versiontreeroot rootnode = new versiontreeroot(null, "Root");

        File rootfolder = new File(path);


        deleteDir(new File(savefilepath + rootfolder.getName()));

        new File(savefilepath + rootfolder.getName()).mkdirs();

        copyclass.copyThreads.clear();


        createvcsexport(rootnode, rootfolder, rootfolder);

        for (int i = 0; i < copyclass.copyThreads.size(); i++)
        {
            copyclass.copyThreads.get(i).join();
        }

        String chksum = checksum.Getchecksum.getdirectorychecksum(rootnode.getSubs());
        rootnode.setShavalue(chksum);
        rootnode.setBranch(branch);

        File file = new File(savefilepath + rootfolder.getName() + "\\" + chksum + ".shark");

        boolean blnCreated = false;
        try {
            blnCreated = file.createNewFile();
        } catch (IOException ioe) {
            System.out.println("Error while creating a new empty file :" + ioe);
        }

        rootnode.printtree();

        try {
            FileOutputStream fileOut =
                    new FileOutputStream(savefilepath + rootfolder.getName() + "\\" + rootfolder.getName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(rootnode);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in " + savefilepath + rootfolder.getName() + "\\" + rootfolder.getName() + ".ser");
        } catch (IOException i) {
            i.printStackTrace();
        }


    }



    public static void createvcsexport(versiontree parent, File node, File rootfolder) throws IOException, NoSuchAlgorithmException {

        List<versiontree> subs = parent.getSubs();

        if (node.isDirectory()) {
            String[] subNote = node.list();
            versiontree directory = new versiontree(parent, node.getName(),true);


            for (String filename : subNote) {
                File subfile = new File(node, filename);
                createvcsexport(directory, subfile, rootfolder);
            }

            String chksum = checksum.Getchecksum.getdirectorychecksum(directory.getSubs());
            directory.setShavalue(chksum);
            subs.add(directory);


        } else {
            String path = node.getAbsolutePath();

            try {
                String chksum = checksum.Getchecksum.getchecksum(path);
                subs.add(new versiontree(chksum, parent, node.getName(), false));


                String copypath = savefilepath + rootfolder.getName() + "\\" + node.getName() + "\\" + chksum;

                copyfiles.copyclass cc = new copyclass(copypath, node.getName(), node);
                Thread t = new Thread(cc);
                t.start();
                copyclass.copyThreads.add(t);


            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }


        }

    }


}
