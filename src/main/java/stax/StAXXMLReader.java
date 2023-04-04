package stax;

import javax.xml.XMLConstants;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StAXXMLReader {

    public static void main(String[] args) {
        try {
            printXmlByXmlCursorReader(Paths.get("src/main/resources/read.xml"));
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static void printXmlByXmlCursorReader(Path path) throws FileNotFoundException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();

        // prevent xxe(XML External Entity) attack
        xmlInputFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        xmlInputFactory.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new FileInputStream(path.toFile()));
        int eventType;

        while (reader.hasNext()) {
            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {
                switch (reader.getName().getLocalPart()) {
                    case "student" -> {
                        String id = reader.getAttributeValue(null, "id");
                        System.out.printf("Student id : %s%n", id);
                    }
                    case "firstname" -> {
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            System.out.printf("Firstname : %s%n", reader.getText());
                        }
                    }
                    case "lastname" -> {
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            System.out.printf("Lastname : %s%n", reader.getText());
                        }
                    }
                    case "nickname" -> {
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            System.out.printf("Nickname : %s%n", reader.getText());
                        }
                    }
                    case "level" -> {
                        String year = reader.getAttributeValue(null, "year");
                        eventType = reader.next();
                        if (eventType == XMLEvent.CHARACTERS) {
                            String level = reader.getText();
                            System.out.printf("Level : %s %s year%n",
                                    Integer.parseInt(level), year);
                        }
                    }
                }

            }

            if (eventType == XMLEvent.END_ELEMENT) {    // </student>
                if (reader.getName().getLocalPart().equals("student")) {
                    System.out.println("-----------------");
                }
            }
        }
    }
}
