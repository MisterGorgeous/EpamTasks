package com.slabadniak.task5.parser;

import com.slabadniak.task5.entityes.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;


public class SAXHandler extends DefaultHandler {
    private ArrayList<Jorney> jorneys;
    private Jorney current = null;
    private JorneyEnum currentEnum = null;
    private EnumSet<JorneyEnum> withText;

    public SAXHandler() {
        jorneys = new ArrayList<Jorney>();
        withText = EnumSet.range(JorneyEnum.ID, JorneyEnum.COUNTRIES);
    }

    public ArrayList<Jorney> getJorneys() {
        return jorneys;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("rest".equals(localName) || "excurtion".equals(localName)) {
            if ("rest".equals(localName)) {
                current = new Rest();
            } else {
                current = new Excurtion();
            }
         /*   current.setName(attrs.getValue(0));
            if (attrs.getLength() == 2) {
                current.setType(attrs.getValue(1));
            }
            else {
                current.setType("unfilled");
            }*/
        } else {
            if(localName.indexOf('-') == -1) {
                JorneyEnum temp = JorneyEnum.valueOf(localName.toUpperCase());
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
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case ID:
                    current.setId(s);
                    break;
                case DAYS:
                    current.setDays(Integer.parseInt(s));
                    break;
                case COST:
                    current.setCost(Float.parseFloat(s));
                    break;
                case TRANSPORT:
                    current.setTransport(Transport.valueOf(s.toUpperCase()));
                    break;
                case COUNTRY:
                    //if(current.getClass().toString().equals(Rest.class.toString())){
                    Rest temp = (Rest) current;
                    temp.setCountry(s);
                    break;
                case HOTEL:
                    //
                    break;
                case NAME:
                    Rest rest = (Rest) current;
                    Hotel hotel = rest.getHotel();
                    hotel.setName(s);
                    break;
                case STAR:
                    rest = (Rest) current;
                    hotel = rest.getHotel();
                    hotel.setStars(Star.valueOf(s.toUpperCase()));
                    break;
                case COUNTRIES:
                    Excurtion excurtion = (Excurtion) current;
                    excurtion.setContries(s);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}

