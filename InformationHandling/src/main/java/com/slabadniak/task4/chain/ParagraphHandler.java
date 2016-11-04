package com.slabadniak.task4.chain;

import com.slabadniak.task4.composite.ComponentPattern;
import com.slabadniak.task4.composite.Root;
import com.slabadniak.task4.regular.Regular;
import org.apache.logging.log4j.Level;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParagraphHandler extends Handler {
    public ParagraphHandler(){
        setNextHandler(new SentenceHandler());
    }

    void parse(String text,ComponentPattern component) {
       /* List<String> paragraphs = Stream
                .of(text)
                .map(p -> p.split("\\."))
                .flatMap(Arrays::stream)
                .filter(p -> !p.equals("\n"))
                .collect(Collectors.toList());
*/
        List<String> paragraphs = Regular.get(text, Regular.SENTENCE);
        paragraphs.forEach(s -> {
            Root paragraph = new Root();
            s = s + '.';
            component.add(paragraph);
            getNextHandler().parse(s,paragraph);
        });
    }
}
