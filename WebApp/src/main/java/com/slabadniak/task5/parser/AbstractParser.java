package com.slabadniak.task5.parser;

import com.slabadniak.task5.entity.Journey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public abstract class AbstractParser {
    protected static final Logger LOGGER = LogManager.getLogger(AbstractParser.class); //to use in subcalsses
    private ArrayList<Journey> jorneys;

    public AbstractParser(){
        jorneys = new ArrayList<Journey>();
    }

    public ArrayList<Journey> getJorneys() {
        return jorneys;
    }

    public abstract void buildJourneys(String filename);
}
