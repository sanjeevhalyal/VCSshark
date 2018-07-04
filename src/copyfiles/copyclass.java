package copyfiles;

import java.io.*;
import java.util.ArrayList;

public class copyclass implements Runnable{

    public static ArrayList<Thread> copyThreads = new ArrayList<Thread>();
    private String copypath, name;
    private File node;


    public copyclass(String copypath, String name, File node) {
        this.copypath = copypath;
        this.name = name;
        this.node = node;
    }

    public void run(){

        System.out.println("Creating "+copypath+"\\"+name);
        try {
            checkiffileexistsandcreate();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Created "+copypath+"\\"+name);
    }


    private  void checkiffileexistsandcreate() throws IOException {

        File dest=new File(copypath+"\\"+name);
        File source=node;
        if(!dest.exists()) {
            new File(copypath).mkdirs();
        }
            try {
                copyFile(source,dest);
            } catch (IOException e) {
                e.printStackTrace();
            }



    }
    private void copyFile(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {

            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;

            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        finally {
            is.close();
            os.close();
        }
    }
}
