package com.slabadniak.task4.chain;

import com.slabadniak.task4.composite.ComponentPattern;
import com.slabadniak.task4.composite.Leaf;
import com.slabadniak.task4.composite.Root;
import com.slabadniak.task4.regular.Regular;
import org.apache.logging.log4j.Level;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LexemeHandler extends Handler {
    public LexemeHandler(){

    }

    void parse(String text,ComponentPattern component) {
        Leaf leaf = new Leaf();
        component.add(leaf);

        Pattern p = Pattern.compile(Regular.LEXEME);
        Stream
                .of(text)
                .map(p::matcher)
                .filter(Matcher::matches)
                .findFirst()
                .ifPresent(matcher -> leaf.setAfterWord(matcher.group(1)));

        if(leaf.getAfterWord().equals("")){
            leaf.setWord(text);
        }
        else{
            leaf.setWord(text.substring(0,text.length() - 2));
        }

    }
}
