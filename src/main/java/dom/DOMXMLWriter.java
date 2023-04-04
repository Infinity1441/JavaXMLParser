package dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

public class DOMXMLWriter {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root
        Document document = docBuilder.newDocument();
        Element rootElement = document.createElement("group");
        document.appendChild(rootElement);

        // add xml node
        Element student = document.createElement("student");
        // add staff to root
        rootElement.appendChild(student);
        // add xml attribute
        student.setAttribute("id", "1");

        Element name = document.createElement("fullname");
        name.setTextContent("Bakhtigul Jumabekova");
        student.appendChild(name);

        Element age = document.createElement("age");
        age.setTextContent("23");
        student.appendChild(age);

        // student 2
        Element student2 = document.createElement("student");
        // add student to root
        rootElement.appendChild(student2);
        student2.setAttribute("id", "2");

        Element name2 = document.createElement("fullname");
        name2.setTextContent("Elbek Kholmatov");
        student2.appendChild(name2);

        Element age2 = document.createElement("age");
        age2.setTextContent("19");
        student2.appendChild(age2);

        // print XML to system console
        writeXml(document, System.out);
    }

    // write document to output stream
    private static void writeXml(Document document, OutputStream output) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);
    }
}