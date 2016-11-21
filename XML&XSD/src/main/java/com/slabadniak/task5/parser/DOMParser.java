package com.slabadniak.task5.parser;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import com.slabadniak.task5.entities.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser extends AbstractParser {
    private DocumentBuilder docBuilder;

    public DOMParser() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }

    @Override
    public void buildJourneys(String fileName) {
        Document document = null;
        try {
            document = docBuilder.parse(fileName);
            Element root = document.getDocumentElement();

            NodeList rests = root.getElementsByTagName("rest");
            for (int i = 0; i < rests.getLength(); i++) {
                Element restElement = (Element) rests.item(i);
                Rest rest = buildRest(restElement);
                getJorneys().add(rest);
            }

            NodeList excurtions = root.getElementsByTagName("excurtion");
            for (int i = 0; i < excurtions.getLength(); i++) {
                Element exElement = (Element) excurtions.item(i);
                Excurtion ex = buildExcurtion(exElement);
                getJorneys().add(ex);
            }
        } catch (SAXException|IOException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }

    private Rest buildRest(Element element) {
        Rest rest = new Rest();
        buildJorney(element, rest);

        Hotel hotel = rest.getHotel();
        Element hotelEl = (Element) element.getElementsByTagName("hotel").item(0);
        hotel.setName(getText(hotelEl, "name"));

        Star star = Star.valueOf(getText(hotelEl, "star"));
        hotel.setStars(star);

        rest.setCountry(getText(element, "country"));
        rest.setRoom(element.getAttribute("room"));
        rest.setFood(element.getAttribute("food"));
        rest.setTv(new Boolean(element.getAttribute("tv")));
        rest.setAirCondition(new Boolean(element.getAttribute("air-condition")));

        return rest;
    }

    private Excurtion buildExcurtion(Element element) {
        Excurtion excurtion = new Excurtion();
        buildJorney(element, excurtion);

        NodeList countEl = element.getElementsByTagName("countries");
        for (int i = 0; i < countEl.getLength(); i++) {
            Node node = countEl.item(i);
            String text = node.getTextContent();
            excurtion.setContries(text);
        }
        return excurtion;
    }

    private void buildJorney(Element element, Journey jorney) {
        jorney.setId(getText(element, "id"));
        jorney.setDays(Integer.parseInt(getText(element, "days")));
        jorney.setCost(Float.parseFloat(getText(element, "cost")));
        Transport transport = Transport.valueOf(element.getAttribute("transport").toUpperCase());
        jorney.setTransport(transport);
    }

    private static String getText(Element element, String name) {
        NodeList nList = element.getElementsByTagName(name);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
