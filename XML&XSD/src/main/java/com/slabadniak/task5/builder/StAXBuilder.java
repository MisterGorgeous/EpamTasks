package com.slabadniak.task5.builder;


import com.slabadniak.task5.entityes.Jorney;
import com.slabadniak.task5.parser.StAXParser;

import java.util.ArrayList;

public class StAXBuilder extends Builder {
    ArrayList<Jorney> parse() {
        StAXParser stax = new StAXParser();
        stax.buildJorneys(filename);
        return stax.getJorneys();
    }
}
