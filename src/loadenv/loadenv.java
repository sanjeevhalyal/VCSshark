package loadenv;


import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;


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
            File inputFile = new File("src\\.env");
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
