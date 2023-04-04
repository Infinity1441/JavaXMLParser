package stax;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class StAXXMLWriter {
    public static void main(String[] args) throws XMLStreamException {
        try (FileOutputStream out = new FileOutputStream("src/main/resources/write.xml")) {
            writeXml(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeXml(OutputStream out) throws XMLStreamException {
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = output.createXMLStreamWriter(out);

        writer.writeStartDocument("utf-8", "1.0");
        writer.writeStartElement("group");
        //--------------------------------------
        writer.writeStartElement("student");
        writer.writeAttribute("id", "1");

        writer.writeStartElement("firstname");
        writer.writeCharacters("Javohir");
        writer.writeEndElement();

        writer.writeStartElement("lastname");
        writer.writeCharacters("Elmurodov");
        writer.writeEndElement();

        writer.writeStartElement("level");
        writer.writeAttribute("year", "SENIOR");
        writer.writeCharacters("4");
        writer.writeEndElement();

        writer.writeStartElement("skill");
        writer.writeCData("HTML tag <i>Java</i>");
        writer.writeEndElement();
        writer.writeEndElement();

        //----------------------------------------------
        writer.writeStartElement("student");
        writer.writeAttribute("id", "2");

        writer.writeStartElement("firstname");
        writer.writeCharacters("Nodira");
        writer.writeEndElement();

        writer.writeStartElement("lastname");
        writer.writeCharacters("Shoraxmedova");
        writer.writeEndElement();

        writer.writeStartElement("level");
        writer.writeAttribute("year", "JUNIOR");
        writer.writeCharacters("3");
        writer.writeEndElement();

        writer.writeStartElement("skill");
        writer.writeCData("HTML tag <i>SQL</i>");
        writer.writeEndElement();
        writer.writeEndElement();
        //------------------------------------------------
        writer.writeEndDocument();

        writer.flush();
        writer.close();
    }
}