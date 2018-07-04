import initialize.initialize;

public class Startclass {
    public static void main(String[] Args)
    {

        String path=Args[0];
        String projectfolder=Args[1];
        String branch=Args[2];



        new loadenv.loadenv();

        try {
            new initialize(path,projectfolder,branch).init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
