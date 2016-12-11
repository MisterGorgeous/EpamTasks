package com.slabadniak.task5.parser;

import org.apache.logging.log4j.Level;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class SAXParser extends AbstractParser{
    private SAXHandler handler;
    private XMLReader reader;

    public SAXParser() {
        handler = new SAXHandler(getJorneys());
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }

    @Override
    public void buildJourneys(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException|IOException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}
