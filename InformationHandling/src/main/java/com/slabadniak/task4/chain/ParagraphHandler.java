package com.slabadniak.task4.chain;

import com.slabadniak.task4.composite.Component;
import com.slabadniak.task4.composite.Composite;
import com.slabadniak.task4.composite.CompositeName;
import com.slabadniak.task4.Regular;

import java.util.List;

public class ParagraphHandler extends Handler {
    private static final char DOT = '.';

    public ParagraphHandler() {
        setNextHandler(new SentenceHandler());
    }

    void parse(String text, Component component) {
        List<String> sentences = Regular.getMatches(text, Regular.SENTENCE);

        sentences.forEach(s -> {
            Composite sentence = new Composite(CompositeName.SENTENCE);
            s = s + DOT;
            component.add(sentence);
            getNextHandler().parse(s, sentence);
        });
    }
}
