package com.slabadniak.task4.algoritm;

import com.slabadniak.task4.composite.Component;
import com.slabadniak.task4.composite.CompositeName;

import java.util.Collections;
import java.util.List;

public class SwapLexeme {
    public static void perform(Component composite) {
        List<Component> descedents = composite.getDescendant();
        if (composite.getRootName() != CompositeName.SENTENCE) {
            for (Component descedent : descedents) {
                perform(descedent);
            }
        } else {
            Collections.swap(descedents, 0, descedents.size() - 1);
        }
    }
}
