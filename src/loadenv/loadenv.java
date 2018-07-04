package loadenv;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;


public class loadenv {

    private static String savefilepath;
    private static Document doc;

    public loadenv() {

        loadxml();
        setSavefilepath();
    }



     private void loadxml()
    {
        try {


            Path path = FileSystems.getDefault().getPath(".env").toAbsolutePath();
            System.out.println(path.toString());

            File inputFile = new File(path.toString());
            System.out.println(inputFile.getAbsolutePath());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static String getSavefilepath() {
        return savefilepath;
    }

    public static void setSavefilepath() {
        NodeList nList = doc.getElementsByTagName("Savefilepath");
        loadenv.savefilepath = nList.item(0).getTextContent();
    }
}
