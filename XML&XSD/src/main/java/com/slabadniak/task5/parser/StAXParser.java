package com.slabadniak.task5.parser;

import com.slabadniak.task5.entities.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StAXParser extends AbstractParser {
    private XMLInputFactory inputFactory;

    public StAXParser() {
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public void buildJourneys(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String string;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    string = reader.getLocalName();

                    if ("rest".equals(string)) {
                        Rest rest = buildRest(reader);
                        getJorneys().add(rest);
                    }
                    if ("excurtion".equals(string)) {
                        Excurtion excurtion = buildEcurtion(reader);
                        getJorneys().add(excurtion);
                    }
                }
            }
        } catch (XMLStreamException|FileNotFoundException e) {
            LOGGER.log(Level.ERROR, e);
        } finally {
            try {
                if (inputStream == null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.log(Level.ERROR, e);
            }
        }
    }

    private Rest buildRest(XMLStreamReader reader) throws XMLStreamException {
        Rest rest = (Rest) buildJorney(reader, new Rest());
        String string;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    string = reader.getLocalName();
                    switch (JourneyEnum.valueOf(string.toUpperCase())) {
                        case COUNTRY:
                            string = getXMLText(reader);
                            rest.setCountry(string);
                            break;
                        case HOTEL:
                            Hotel hotel = rest.getHotel();
                            buildHotel(reader, hotel);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    string = reader.getLocalName();
                    if ("rest".equals(string)) {
                        return rest;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element.");
    }

    private Excurtion buildEcurtion(XMLStreamReader reader) throws XMLStreamException {
        Excurtion excurtion = (Excurtion) buildJorney(reader, new Excurtion());
        String string;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    string = reader.getLocalName();
                    switch (JourneyEnum.valueOf(string.toUpperCase())) {
                        case COUNTRIES:
                            string = getXMLText(reader);
                            excurtion.setContries(string);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    string = reader.getLocalName();
                    if ("excurtion".equals(string)) {
                        return excurtion;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element.");
    }

    private Journey buildJorney(XMLStreamReader reader, Journey jorney) throws XMLStreamException {
        String string;
        int index = 0; //iteration contoll
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    string = reader.getLocalName();
                    switch (JourneyEnum.valueOf(string.toUpperCase())) {
                        case ID:
                            string = getXMLText(reader);
                            jorney.setId(string);
                            break;
                        case DAYS:
                            string = getXMLText(reader);
                            jorney.setDays(Integer.parseInt(string));
                            break;
                        case COST:
                            string = getXMLText(reader);
                            jorney.setCost(Float.parseFloat(string));
                            break;
                        case TRANSPORT:
                            string = getXMLText(reader);
                            jorney.setTransport(Transport.valueOf(string.toUpperCase()));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    ++index;
                    string = reader.getLocalName();
                    if ("excurtion".equals(string) || "rest".equals(string) || index == 3) { //magic symbol. that necessary
                        return jorney;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element.");
    }

    private void buildHotel(XMLStreamReader reader, Hotel hotel) throws XMLStreamException {
        int type;
        String string;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    string = reader.getLocalName();
                    switch (JourneyEnum.valueOf(string.toUpperCase())) {
                        case HOTEL:
                            //
                            break;
                        case NAME:
                            string = getXMLText(reader);
                            hotel.setName(string);
                            break;
                        case STAR:
                            string = getXMLText(reader);
                            hotel.setStars(Star.valueOf(string.toUpperCase()));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    string = reader.getLocalName();
                    if ("hotel".equals(string)) {
                        return;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element.");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}