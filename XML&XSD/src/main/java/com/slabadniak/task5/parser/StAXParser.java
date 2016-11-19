package com.slabadniak.task5.parser;

import com.slabadniak.task5.jorney.*;
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

public class StAXParser {
    private static final Logger LOGGER = LogManager.getLogger(StAXParser.class);
    private XMLInputFactory inputFactory;
    private ArrayList<Jorney> jorneys;

    public StAXParser() {
        inputFactory = XMLInputFactory.newInstance();
        jorneys = new ArrayList<Jorney>();
    }

    public ArrayList<Jorney> getJorneys() {
        return jorneys;
    }

    public void buildJorneys(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String temp;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    temp = reader.getLocalName();

                    if ("rest".equals(temp)) {
                        Rest rest = buildRest(reader);
                        jorneys.add(rest);
                    }
                    if ("excurtion".equals(temp)) {
                        Excurtion excurtion = buildEcurtion(reader);
                        jorneys.add(excurtion);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            LOGGER.error("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            LOGGER.error("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream == null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                LOGGER.error("Impossible close file " + fileName + " : " + e);
            }
        }
    }

    private Rest buildRest(XMLStreamReader reader) throws XMLStreamException {
      //  Rest rest = (Rest) buildJorney(reader, new Rest());
        Rest rest = new Rest();
        String temp;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    temp = reader.getLocalName();
                    switch (JorneyEnum.valueOf(temp.toUpperCase())) {
                        case ID:
                            temp = getXMLText(reader);
                            rest.setId(temp);
                            break;
                        case DAYS:
                            temp = getXMLText(reader);
                            rest.setDays(Integer.parseInt(temp));
                            break;
                        case COST:
                            temp = getXMLText(reader);
                            rest.setCost(Float.parseFloat(temp));
                            break;
                        case TRANSPORT:
                            temp = getXMLText(reader);
                            rest.setTransport(Transport.valueOf(temp.toUpperCase()));
                            break;
                        case COUNTRY:
                            //if(current.getClass().toString().equals(Rest.class.toString())){
                            temp = getXMLText(reader);
                            rest.setCountry(temp);
                            break;
                        case HOTEL:
                            Hotel hotel = rest.getHotel();
                            buildHotel(reader, hotel);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    temp = reader.getLocalName();
                    if ("rest".equals(temp)) {
                        return rest;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element.");
    }

    private Excurtion buildEcurtion(XMLStreamReader reader) throws XMLStreamException {
       // Excurtion excurtion = (Excurtion) buildJorney(reader, new Excurtion());
        Excurtion excurtion = new Excurtion();
        String temp;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    temp = reader.getLocalName();
                    switch (JorneyEnum.valueOf(temp.toUpperCase())) {
                        case ID:
                            temp = getXMLText(reader);
                            excurtion.setId(temp);
                            break;
                        case DAYS:
                            temp = getXMLText(reader);
                            excurtion.setDays(Integer.parseInt(temp));
                            break;
                        case COST:
                            temp = getXMLText(reader);
                            excurtion.setCost(Float.parseFloat(temp));
                            break;
                        case TRANSPORT:
                            temp = getXMLText(reader);
                            excurtion.setTransport(Transport.valueOf(temp.toUpperCase()));
                            break;
                        case COUNTRIES:
                            temp = getXMLText(reader);
                            excurtion.setContries(temp);
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:

                    temp = reader.getLocalName();
                    if ("excurtion".equals(temp)) {
                        return excurtion;

                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element.");
    }

    private Jorney buildJorney(XMLStreamReader reader, Jorney jorney) throws XMLStreamException {
        String temp;
        int index = 0;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    temp = reader.getLocalName();
                    switch (JorneyEnum.valueOf(temp.toUpperCase())) {
                        case ID:
                            temp = getXMLText(reader);
                            jorney.setId(temp);
                            break;
                        case DAYS:
                            temp = getXMLText(reader);
                            jorney.setDays(Integer.parseInt(temp));
                            break;
                        case COST:
                            temp = getXMLText(reader);
                            jorney.setCost(Float.parseFloat(temp));
                            break;
                        case TRANSPORT:
                            temp = getXMLText(reader);
                            jorney.setTransport(Transport.valueOf(temp.toUpperCase()));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    ++index;
                    temp = reader.getLocalName();
                    if ("excurtion".equals(temp) || "rest".equals(temp) || index == 3) {
                        return jorney;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element.");
    }

    private void buildHotel(XMLStreamReader reader, Hotel hotel) throws XMLStreamException {
        int type;
        String temp;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    temp = reader.getLocalName();
                    switch (JorneyEnum.valueOf(temp.toUpperCase())) {
                        case HOTEL:
                            //
                            break;
                        case NAME:
                            temp = getXMLText(reader);
                            hotel.setName(temp);
                            break;
                        case STAR:
                            temp = getXMLText(reader);
                            hotel.setStars(Star.valueOf(temp.toUpperCase()));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    temp = reader.getLocalName();
                    if ("hotel".equals(temp)) {
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