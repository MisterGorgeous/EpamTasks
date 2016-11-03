package com.slabadniak.task4.regular;

import java.util.regex.Pattern;

public class Regular {
    public static final String LEXEME = "[\\w]";//|(|)|+|-|*|/

    public static Pattern get(String str) {
        return Pattern.compile(str);
    }
}
