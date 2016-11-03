package com.slabadniak.task4.chain;

import com.slabadniak.task4.composite.ComponentPattern;
import com.slabadniak.task4.composite.Root;
import org.apache.logging.log4j.Level;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SentenceHandler extends Handler {
    public SentenceHandler() {
        setNextHandler(new LexemeHandler());
    }

    void parse(String text,ComponentPattern component) {
        List<String> paragraphs = Stream
                .of(text)
                .map(p -> p.split(" "))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        paragraphs.forEach(s -> {
            Root paragraph = new Root();
            s = s + ' ';
            component.add(paragraph);
            getNextHandler().parse(s,paragraph);
        });
    }
}