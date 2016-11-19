package com.slabadniak.task5.parser;

import com.slabadniak.task5.entityes.Jorney;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;


public class SAXParser {
    private final static Logger LOGGER = LogManager.getLogger(SAXParser.class);
    private SAXHandler handler;
    private XMLReader reader;
    private ArrayList<Jorney> jorneys;

    public SAXParser() {
        handler = new SAXHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            LOGGER.error("SAX exeption: " + e);
        }
    }

    public ArrayList<Jorney> getJorneys() {
        return jorneys;
    }

    public void buildJorneys(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            LOGGER.error("SAX exeption: " + e);
        } catch (IOException e) {
            LOGGER.error("I/O exeption " + e);
        }
        jorneys = handler.getJorneys();
    }
}
