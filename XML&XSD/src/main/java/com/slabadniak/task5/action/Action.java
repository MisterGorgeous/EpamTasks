package com.slabadniak.task5.action;


import com.slabadniak.task5.builder.Builder;
import com.slabadniak.task5.builder.DOMBuilder;
import com.slabadniak.task5.builder.SAXBuilder;
import com.slabadniak.task5.builder.StAXBuilder;
import com.slabadniak.task5.entityes.Jorney;
import com.slabadniak.task5.parser.DOMParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Action {
    private static final Logger LOGGER = LogManager.getLogger(DOMParser.class);

    public static void main(String[] args) {
        ArrayList<Jorney> result;

        Builder builder = new DOMBuilder();
        result = builder.create();
        LOGGER.info(result.toString());
        builder = new SAXBuilder();
        result = builder.create();
        LOGGER.info(result.toString());
        builder = new StAXBuilder();
        result = builder.create();
        LOGGER.info(result.toString());
    }


}
