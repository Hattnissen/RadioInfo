import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;
import java.util.ArrayList;

/**
 * Model
 *
 * Version: v.1.0
 * Author: Johan Hultbäck
 * CS-user: id18jhk
 */
public class Model {
    String urlString = "http://api.sr.se/api/v2/channels?pagination=false";
    Document document;

    public Model() {
        this.document = buildDocument(urlString); //Document for all channels on SR
    }

    /**
     * "buildDocument"
     * Parses a XML document given a URL String.
     *
     * @param urlString
     * @return a parsed XML document
     */
    private Document buildDocument(String urlString) {
        Document document = null;
        try {
            URL url = new URL(urlString);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(url.openStream());
            document.getDocumentElement().normalize();
        } catch (Exception e) {
            e.getCause();
        }
        return document;
    }

    /**
     * "getTableURL"
     * Looks up a schedule URL given an id and a parsed XML document
     * that can be used to get a table for programs on Sveriges Radio.
     *
     * @param id
     * @param document
     * @return URL, the schedule URL
     */
    public String getScheduleURL(Document document, String id) {
        String url = null;
        NodeList channels = document.getElementsByTagName("channel");
        for (int i = 0; i < channels.getLength(); i++) {
            Node channel = channels.item(i);
            if (channel.getNodeType() == Node.ELEMENT_NODE) {
                Element elementChannel = (Element) channel;
                if (elementChannel.getAttributeNode("id").getValue().equals(id)) {
                    NodeList scheduleURLList = elementChannel.getElementsByTagName("scheduleurl");
                    Node scheduleURLNode = scheduleURLList.item(0);
                    url = scheduleURLNode.getTextContent();
                }
            }
        }
        return url;
    }

    /**
     * "getScheduleData"
     * Parses an XML document with the given URL and then builds a String[][]
     * with a channels schedule from Sveriges Radio.
     * @param scheduleURL
     * @return
     */
    public String[][] getScheduleData(String scheduleURL) {
        Document document = buildDocument(scheduleURL);
        NodeList episodes = document.getElementsByTagName("scheduledepisode");
        String[][] data = new String[episodes.getLength()][3];
        for (int i = 0; i < episodes.getLength(); i++) {
            ArrayList<String> programInfo = new ArrayList<>();
            Node episode = episodes.item(i);
            if (episode.getNodeType() == Node.ELEMENT_NODE) {
                Element elementEpisode = (Element) episode;
                String programName = getElementString(elementEpisode, "title");
                System.out.println(programName);
                programInfo.add(programName);
                String startTime = getElementString(elementEpisode, "starttimeutc");
                System.out.println(startTime);
                programInfo.add(startTime);
                String endTime = getElementString(elementEpisode, "endtimeutc");
                System.out.println(endTime);
                programInfo.add(endTime);
            }
            for (int j = 0; j < programInfo.size(); j++) {
                data[i][j] = programInfo.get(j);
            }
        }
        return data;
    }

    private String getElementString(Element element, String elementName) {
        String elementString = null;
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element nodeElement = (Element) node;
            elementString = nodeElement.getTextContent();
        }
        return elementString;
    }

    private String getElementAttributeString(Element element, String elementName, String attributeName) {
        String elementAttributeString = null;
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element nodeElement = (Element) node;
            elementAttributeString = nodeElement.getAttributeNode(attributeName).getValue();
        }
        return elementAttributeString;
    }

//    public Object hoursBeforeAndAfter(int hours) {

  //  }
}
