package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class DOMXMLReader {
    public static void main(String[] args) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();

            Document doc = db.parse(new File("src/main/resources/read.xml"));

            System.out.println("Root Element : " + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            NodeList list = doc.getElementsByTagName("student");

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    String id = element.getAttribute("id");

                    String firstname = element.getElementsByTagName("firstname").item(0).getTextContent();
                    String lastname = element.getElementsByTagName("lastname").item(0).getTextContent();
                    String nickname = element.getElementsByTagName("nickname").item(0).getTextContent();

                    NodeList salaryNodeList = element.getElementsByTagName("level");
                    String level = salaryNodeList.item(0).getTextContent();

                    String year = salaryNodeList.item(0).getAttributes().getNamedItem("year").getTextContent();

                    System.out.println("Current Element :" + node.getNodeName());
                    System.out.println("Student Id : " + id);
                    System.out.println("Firstname : " + firstname);
                    System.out.println("Lastname : " + lastname);
                    System.out.println("Nickname : " + nickname);
                    System.out.printf("Level : %s %s year %n%n", Integer.parseInt(level), year);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}