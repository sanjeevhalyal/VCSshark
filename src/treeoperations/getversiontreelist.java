package treeoperations;

import versiontree.versiontree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

public class getversiontreelist {

   public static LinkedList<versiontree> getversiontreelist(String path) {
        LinkedList<versiontree> out = null;
        try {

            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            out = (LinkedList<versiontree>) in.readObject();

            in.close();
            fileIn.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return out;

    }

}
