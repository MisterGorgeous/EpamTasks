package com.slabadniak.task4;

import com.slabadniak.task4.algoritm.Eighth;
import com.slabadniak.task4.chain.TextHandler;
import com.slabadniak.task4.composite.Composite;
import com.slabadniak.task4.composite.CompositeName;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Action {
    private static final Logger LOGGER = LogManager.getLogger(Action.class);

    public static void main(String[] args) {
        Composite tree = new Composite(CompositeName.TEXT);
        TextHandler handler = new TextHandler();
        handler.parse(TextReader.getText(), tree);

        LOGGER.log(Level.INFO, tree.toString());
        Eighth.perform(tree);
        LOGGER.log(Level.INFO, tree.toString());
    }
}
