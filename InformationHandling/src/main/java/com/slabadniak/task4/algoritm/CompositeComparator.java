package com.slabadniak.task4.algoritm;

import com.slabadniak.task4.composite.Component;

import java.util.Comparator;

public class CompositeComparator implements Comparator<Component> {
    @Override
    public int compare(Component o1, Component o2) {
        return Integer.compare(o1.getDescendant().size(), o2.getDescendant().size());
    }
}