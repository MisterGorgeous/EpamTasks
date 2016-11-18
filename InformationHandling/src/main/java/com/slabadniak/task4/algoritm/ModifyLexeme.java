package com.slabadniak.task4.algoritm;

import com.slabadniak.task4.composite.Component;
import com.slabadniak.task4.Regular;

import java.util.List;

public class ModifyLexeme {

    public static void perform(Component composite) {
        List<Component> descedents = composite.getDescendant();
        if (composite.getDescendant() != null) {
            for (Component descedent : descedents) {
                perform(descedent);
            }
        } else {
            String word = composite.getData();
            if (Regular.isMatch(word, Regular.WORD)) {
                word = modify(word);
                composite.setData(word);
            }
        }
    }

    private static String modify(String word) {
        String first = word.substring(0, 1);
        String str = word.replace(first, "");
        return first + str;
    }
}