import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

/**
 * RadioInfo
 *
 * Version: v.1.0
 * Author: Johan Hultbäck
 * CS-user: id18jhk
 */
public class RadioInfo {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller();

        String url = "http://api.sr.se/api/v2/channels?pagination=false";
        String p1 = "132";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new URL(url).openStream());
            document.getDocumentElement().normalize();

            //Kod för att ta fram en url till en kanals tablå givet kanalens id
            NodeList channels = document.getElementsByTagName("channel");
            for (int i = 0; i < channels.getLength(); i++) {
                Node channel = channels.item(i);
                if (channel.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementChannel = (Element) channel;
                    if (elementChannel.getAttributeNode("id").getValue().equals(p1)) {
                        System.out.println("Är P1");
                        NodeList scheduleList = elementChannel.getElementsByTagName("scheduleurl");
                        for (int j = 0; j < scheduleList.getLength(); j++) {
                            Node scheduleURL = scheduleList.item(j);
                            System.out.println(scheduleURL.getTextContent());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.getCause();
        }
    }
}
