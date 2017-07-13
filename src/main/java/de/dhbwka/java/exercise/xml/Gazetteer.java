package de.dhbwka.java.exercise.xml;

import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

/**
 * Created by Florian Langknecht <flangknecht@united-internet.de> on 11.07.17.
 */
public class Gazetteer {
    private SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
    private DocumentBuilderFactory documentBuilderFactory = new DocumentBuilderFactoryImpl();
    private DefaultHandler handler;

    public Gazetteer() throws ParserConfigurationException, SAXException {
    }

    public static void main(String[] args) {
        try {
            Gazetteer gas = new Gazetteer();
            gas.parseAndPrintShizzle(new URL(args[0]), "formatted_address", "long_name", "location", "bounds");
        } catch (ParserConfigurationException | URISyntaxException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }


    private void parseAndPrintShizzle(URL url, String... requiredNodes) throws ParserConfigurationException, URISyntaxException, IOException, SAXException {
        System.out.println("XML GMaps parsing");
        handler = new SAXGMapsContentHandler(requiredNodes);
        XMLReader xmlReader = parser.getXMLReader();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(url.toURI().toString());
        xmlReader.setErrorHandler(new ErrorHandler() {
            @Override
            public void warning(SAXParseException exception) throws SAXException {
                System.err.println(exception.toString());

            }

            @Override
            public void error(SAXParseException exception) throws SAXException {
                System.err.println(exception.toString());
            }

            @Override
            public void fatalError(SAXParseException exception) throws SAXException {
                System.err.println(exception.toString());
            }
        });
        Map<String, String> tags = ((SAXGMapsContentHandler) xmlReader.getContentHandler()).getTags();

        for (String s : tags.keySet()) {
            System.out.println(s + ": " + tags.get(s));
        }
    }
}
