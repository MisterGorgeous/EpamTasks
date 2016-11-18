package com.slabadniak.task4;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regular {
    public static final String PARAGRAPH = "\t.+";
    public static final String SENTENCE = "[^\\.]+";
    public static final String LEXEME = "[^ ]+";
    public static final String WORD = "[A-z]+";
    public static final String EXPRESION = "[\\)\\(\\*\\/\\+\\-\\d]+";


    public static List<String> getMatches(String data, String rule) {
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(data);
        List<String> paragraphs = new ArrayList<>();
        while (m.find()) {
            String str = data.substring(m.start(), m.end());
            paragraphs.add(str);
        }
        return paragraphs;
    }

    public static boolean isMatch(String word, String rule) {
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(word);
        return m.matches();
    }

    public static Matcher getMatcher(String word, String rule) {
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(word);
        return m;
    }
}
