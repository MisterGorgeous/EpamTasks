package com.slabadniak.task4.composite;

import org.apache.logging.log4j.Level;

import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.text;

public class Root implements ComponentPattern {
    private List<ComponentPattern> descendants;

    public Root() {
        descendants = new ArrayList<ComponentPattern>();
    }

    public void add(ComponentPattern descendant) {
        descendants.add(descendant);
    }

    public List<ComponentPattern> getDescendant() {
        return descendants;
    }

    @Override
    public String toString() {
        String resString = "";
        for(ComponentPattern component : descendants){
            resString = resString + component.toString();
        }
        return resString;
    }
}
