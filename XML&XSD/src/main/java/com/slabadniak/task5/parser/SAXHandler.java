package com.slabadniak.task5.parser;

import com.slabadniak.task5.entities.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;

public class SAXHandler extends DefaultHandler {
    private ArrayList<Journey> jorneys;
    private Journey current;
    private JourneyEnum currentEnum;
    private EnumSet<JourneyEnum> withText;

    public SAXHandler(ArrayList<Journey> jorneys) {
        this.jorneys = jorneys;
        withText = EnumSet.range(JourneyEnum.ID, JourneyEnum.COUNTRIES);
    }

    public ArrayList<Journey> getJorneys() {
        return jorneys;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("rest".equals(localName) || "excurtion".equals(localName)) {
            if ("rest".equals(localName)) {
                current = new Rest();
            } else {
                current = new Excurtion();
            }
        } else {
            if(localName.indexOf('-') == -1) {  //avoid tags with '-'
                JourneyEnum temp = JourneyEnum.valueOf(localName.toUpperCase());
                if (withText.contains(temp)) {
                    currentEnum = temp;
                }
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("rest".equals(localName) || "excurtion".equals(localName)) {
            jorneys.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String string = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case ID:
                    current.setId(string);
                    break;
                case DAYS:
                    current.setDays(Integer.parseInt(string));
                    break;
                case COST:
                    current.setCost(Float.parseFloat(string));
                    break;
                case TRANSPORT:
                    current.setTransport(Transport.valueOf(string.toUpperCase()));
                    break;
                case COUNTRY:
                    Rest temp = (Rest) current;
                    temp.setCountry(string);
                    break;
                case HOTEL:
                    break;
                case NAME:
                    Rest rest = (Rest) current;
                    Hotel hotel = rest.getHotel();
                    hotel.setName(string);
                    break;
                case STAR:
                    rest = (Rest) current;
                    hotel = rest.getHotel();
                    hotel.setStars(Star.valueOf(string.toUpperCase()));
                    break;
                case COUNTRIES:
                    Excurtion excurtion = (Excurtion) current;
                    excurtion.setContries(string);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}

