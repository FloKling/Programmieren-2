package de.dhbwka.java.exercise.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Florian Langknecht <flangknecht@united-internet.de> on 11.07.17.
 */
public class SAXGMapsContentHandler extends DefaultHandler {
    private Map<String, String> tags;
    private Set<String> requiredElements = new HashSet<>();
    private String currentValue;
    private String currentTag;

    public SAXGMapsContentHandler(String... requiredElements) {
        this.requiredElements.addAll(Arrays.asList(requiredElements));
    }

    @Override
    public void startDocument() throws SAXException {
        tags = new HashMap<>();
    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (currentTag != null) {
            tags.put(currentTag, currentValue);
        }
        currentTag = null;
        currentValue = null;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (requiredElements.contains(qName)) {
            currentTag = qName;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue = new String(ch, start, length).trim();
//        if (!currentValue.isEmpty()) {
//            System.out.println(currentValue);
//        }
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public Map<String, String> getTags() {
        return tags;
    }
}