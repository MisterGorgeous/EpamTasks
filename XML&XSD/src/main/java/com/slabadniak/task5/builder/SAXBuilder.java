package com.slabadniak.task5.builder;


import com.slabadniak.task5.entityes.Jorney;

import java.util.ArrayList;

public class SAXBuilder extends Builder {
    ArrayList<Jorney> parse() {
        com.slabadniak.task5.parser.SAXParser sax = new com.slabadniak.task5.parser.SAXParser();
        sax.buildJorneys(filename);
        return sax.getJorneys();
    }
}
