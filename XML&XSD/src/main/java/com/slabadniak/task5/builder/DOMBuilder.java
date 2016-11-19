package com.slabadniak.task5.builder;

import com.slabadniak.task5.entityes.Jorney;
import com.slabadniak.task5.parser.DOMParser;

import java.util.ArrayList;


public class DOMBuilder extends Builder {
     ArrayList<Jorney> parse() {
        DOMParser dom = new DOMParser();
        dom.buildJorneys(filename);
        return dom.getJorneys();
    }
}
