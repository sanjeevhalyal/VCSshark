package branchoperations;

import java.io.File;
import java.io.FilenameFilter;

public class getlatestversion {
    public String getlatestversion(String projectname, String branch) throws Exception {
        File node = new File(loadenv.loadenv.getSavefilepath() + projectname + "\\branch\\" + branch);
        String out = "";

        if (node.isDirectory() && node.exists()) {
            String[] subNote = node.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {

                    File file=new File(dir+"\\"+name);
                    if(file.isDirectory()) {
                        return true;
                    }
                    return false;
                }


            });;


            for (String filename : subNote) {
                out = out.compareTo(filename) < 0 ? filename : out;

            }
            if (out.compareTo("") == 0) {
                throw new Exception("Empty Branch");

            }


        } else {
            throw new Exception("Branch Doesn't Exist");
        }


        return out;

    }
}
