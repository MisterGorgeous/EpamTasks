package com.slabadniak.task4.algoritm;

import com.slabadniak.task4.composite.Component;
import com.slabadniak.task4.composite.CompositeName;

import java.util.*;

public class SortSentence {
    private static ArrayList<Component> sentences = new ArrayList<>();
    private static CompositeComparator comparator = new CompositeComparator();

    public static void perform(Component composite) {
        selectAllSentences(composite);
        Collections.sort(sentences, comparator);
    }

    private static void selectAllSentences(Component composite) {
        List<Component> descedents = composite.getDescendant();
        if (composite.getRootName() != CompositeName.PARAGRAPH) {
            for (Component descedent : descedents) {
                selectAllSentences(descedent);
            }
        } else {
            sentences.addAll(composite.getDescendant());
        }
    }

    public static String getResult() {
        String result = "";
        for (Component composite : sentences) {
            result = result + composite.toString() + '\n';
        }
        return result;
    }
}


