package com.slabadniak.task4.chain;

import com.slabadniak.task4.composite.Component;
import com.slabadniak.task4.composite.Composite;
import com.slabadniak.task4.composite.CompositeName;
import com.slabadniak.task4.regular.Regular;

import java.util.List;

public class SentenceHandler extends Handler {
    public SentenceHandler() {
        setNextHandler(new LexemeHandler());
    }

    void parse(String text, Component component) {
        List<String> lemes = Regular.getMatches(text, Regular.LEXEME);

        lemes.forEach(s -> {
            Composite lexeme = new Composite(CompositeName.LEXEME);
            s = s + ' ';
            component.add(lexeme);
            getNextHandler().parse(s, lexeme);
        });
    }
}