package branchoperations;

import java.io.File;

public class createbranch {

    public static boolean createbranch(String projectname,String branch)
    {
        System.out.println(loadenv.loadenv.getSavefilepath() +projectname+"\\branch\\"+ branch);
        return new File(loadenv.loadenv.getSavefilepath() +projectname+"\\branch\\"+ branch).mkdirs();
    }
}
