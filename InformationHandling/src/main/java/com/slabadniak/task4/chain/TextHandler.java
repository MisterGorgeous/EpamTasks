package com.slabadniak.task4.chain;

import com.slabadniak.task4.composite.Component;
import com.slabadniak.task4.composite.Composite;
import com.slabadniak.task4.composite.CompositeName;
import com.slabadniak.task4.regular.Regular;

import java.util.List;

public class TextHandler extends Handler {
    public TextHandler() {
        setNextHandler(new ParagraphHandler());
    }

    public void parse(String text, Component component) {
        List<String> paragraphs = Regular.getMatches(text, Regular.PARAGRAPH);

        paragraphs.forEach(s -> {
            Composite paragraph = new Composite(CompositeName.PARAGRAPH);
            component.add(paragraph);
            getNextHandler().parse(s, paragraph);
        });
    }
}
