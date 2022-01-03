import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.net.URL;

public class XMLParser {

    public XMLParser() {

    }

    public void parseXML() {
        String url = "http://api.sr.se/api/v2/channels/132";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new URL(url).openStream());
            document.getDocumentElement().normalize();
            Element scheduleURL = (Element) document.getElementsByTagName("scheduleurl");
            System.out.println(scheduleURL.getNodeName());

        } catch (Exception e) {
            e.getCause();
        }
    }
}
