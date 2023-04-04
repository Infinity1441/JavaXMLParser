package sax;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;
public class SAXHandler extends DefaultHandler {
    private StringBuilder currentValue = new StringBuilder();
    @Override
    public void startElement(String uri, String localName, String element, Attributes attributes) {

        // reset the tag value
        currentValue.setLength(0);

        if (element.equalsIgnoreCase("student")) {
            // get tag's attribute by name
            String id = attributes.getValue("id");
            System.out.printf("Student id : %s%n", id);
        }

        if (element.equalsIgnoreCase("level")) {
            String year = attributes.getValue(0);
            System.out.printf("year : %s%n", year);
        }
    }

    @Override
    public void endElement(String uri, String localName, String element) {

        if (element.equalsIgnoreCase("firstname")) {
            System.out.printf("Firstname : %s%n", currentValue.toString());
        }

        if (element.equalsIgnoreCase("lastname")) {
            System.out.printf("Lastname : %s%n", currentValue.toString());
        }

        if (element.equalsIgnoreCase("nickname")) {
            System.out.printf("Nickname : %s%n", currentValue.toString());
        }

        if (element.equalsIgnoreCase("level")) {
            System.out.printf("Level : %s%n", currentValue.toString());
            System.out.println("-----------------------------");
        }

    }

    @Override
    public void characters(char ch[], int start, int length) {
        currentValue.append(ch, start, length);
    }
}