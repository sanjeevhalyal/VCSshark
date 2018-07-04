package treeoperations;

import versiontree.versiontree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class getversiontree {

    public static versiontree getversiontree(String path) {
        versiontree out = null;
        try {

            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);

            out = (versiontree) in.readObject();

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
