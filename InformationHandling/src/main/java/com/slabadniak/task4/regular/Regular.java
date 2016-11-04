package com.slabadniak.task4.regular;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Regular {
    public static final String PARAGRAPH = "\t.+";
    public static final String SENTENCE = "[^\\.]+";
    public static final String LEXEME = "[^ ]+";
    public static final String WORD = "[A-z]+";
    public static final String EXPRESION = "[\\)\\(\\*\\/\\+\\-\\d]+";


    public static List<String> get(String data,String rule) {
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(data);
        List<String> paragraphs = new ArrayList<>();
        while(m.find()) {
            String str = data.substring(m.start(), m.end());
            paragraphs.add(str);
        }
        //Pattern par = Pattern.compile(rule);
          /*  List<String> paragraphs = Stream
                .of(data)
                .map(p::matcher)
                .filter(Matcher::matches)
                .map(Matcher::find)
                .collect(Collectors.toList());*/
        return paragraphs;
    }
}
