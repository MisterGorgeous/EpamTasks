package com.slabadniak.task4.composite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ComponentPattern {
    void add(ComponentPattern component);
    Object getDescendant();
}
