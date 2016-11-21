package com.slabadniak.task5.action;

import com.slabadniak.task5.builder.Builder;
import com.slabadniak.task5.entities.Journey;
import com.slabadniak.task5.parser.DOMParser;
import com.slabadniak.task5.parser.SAXParser;
import com.slabadniak.task5.parser.StAXParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Action {
    private static final Logger LOGGER = LogManager.getLogger(DOMParser.class);

    public static void main(String[] args) {
        ArrayList<Journey> result;

        Builder builder = new Builder();
        result = builder.create(new DOMParser());
        LOGGER.info(result.toString());
        result = builder.create(new SAXParser());
        LOGGER.info(result.toString());
        result = builder.create(new StAXParser());
        LOGGER.info(result.toString());
    }
}
