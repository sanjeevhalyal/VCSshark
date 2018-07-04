package getfiles;

import java.io.File;
import java.io.FilenameFilter;

public class getfiles {

     public static String getsharkfile(String path, String projectname) throws Exception {
        File file = new File(path);
        System.out.println(path);
        String[] subfiles = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.lastIndexOf('.')>0) {

                    // get last index for '.' char
                    int lastIndex = name.lastIndexOf('.');

                    // get extension
                    String str = name.substring(lastIndex);

                    if(str.equals(".shark")) {
                        return true;
                    }
                }

                return false;
            }});

        if(subfiles.length==0)
        {
            throw new Exception("No Shark File");
        }
        return subfiles[0];

//        return customverifyandreturn(".ser", ".shark", subfiles, projectname);
    }

     public static String getserfile(String path, String projectname) throws Exception {
        File file = new File(path);
        String[] subfiles = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.lastIndexOf('.')>0) {

                    // get last index for '.' char
                    int lastIndex = name.lastIndexOf('.');

                    // get extension
                    String str = name.substring(lastIndex);

                    // match path name extension
                    if(str.equals(".ser")) {
                        return true;
                    }
                }

                return false;
            }});

        if(subfiles.length==0)
        {
            throw new Exception("No object File");
        }
        return subfiles[0];
    }
}
