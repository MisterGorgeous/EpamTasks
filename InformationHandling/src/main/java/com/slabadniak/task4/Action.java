package com.slabadniak.task4;

import com.slabadniak.task4.algoritm.ModifyLexeme;
import com.slabadniak.task4.algoritm.SortSentence;
import com.slabadniak.task4.algoritm.SwapLexeme;
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

        //cloning the tree to leave text clean
        Composite clonedTree = tree.clone();

        LOGGER.log(Level.INFO, tree.toString());
        LOGGER.log(Level.INFO, clonedTree.toString());
        ModifyLexeme.perform(clonedTree);
        SortSentence.perform(clonedTree);
        SwapLexeme.perform(clonedTree);
        LOGGER.log(Level.INFO, tree.toString());
        LOGGER.log(Level.INFO, clonedTree.toString());
    }
}
