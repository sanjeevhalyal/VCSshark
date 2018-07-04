package branchoperations;

import java.io.File;

public class reverttoperviousversion {

    public reverttoperviousversion(String projectname,String branch) throws Exception {
        String latest = new getlatestversion().getlatestversion(projectname, branch);

        String latesttreepath=loadenv.loadenv.getSavefilepath()+"\\"+projectname+"\\branch\\"+branch+"\\"+latest;

        String versionpath=loadenv.loadenv.getSavefilepath()+"\\"+projectname+"\\branch\\"+branch+"\\";

        File versiondirectory=new File(versionpath);

        if(versiondirectory.isDirectory() && versiondirectory.list().length>1)
        {
            deletefiles.deletedir.deleteDir(new File(latesttreepath));
        }
        else
        {
            System.out.println("No pervious version");
        }
    }
}
