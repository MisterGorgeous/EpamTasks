package com.slabadniak.task4.chain;

import com.slabadniak.task4.composite.Component;
import com.slabadniak.task4.composite.Leaf;
import com.slabadniak.task4.composite.CompositeName;
import com.slabadniak.task4.exeption.IncorrectDataExeption;
import com.slabadniak.task4.interpreter.TerminalExpression;
import com.slabadniak.task4.notation.PolishNotation;
import com.slabadniak.task4.regular.Regular;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeHandler extends Handler {
    public LexemeHandler() {
    }

    void parse(String text, Component component) {
        ArrayList<String> words = new ArrayList<>();
        Matcher m = Regular.getMatcher(text, Regular.WORD);
        String data;

        //if not a word parse like expression
        if (!m.find()) {
            m = Regular.getMatcher(text, Regular.EXPRESION);
            m.find();
            data = text.substring(m.start(), m.end());
            //calculate expression
            PolishNotation notation = new PolishNotation();
            notation.make(data);
            new TerminalExpression().interpreting(notation);
            data = notation.getResult();
        } else {
            data = text.substring(m.start(), m.end());
        }

        words.add(data);

        //if lexeme contain extra symbols
        if (m.start() > 0) {
            words.add(0, text.substring(0, m.start()));
        }
        if (m.end() <= text.length() - 1) {
            words.add(text.substring(m.end(), text.length()));
        }

        for (String word : words) {
            Leaf leaf = new Leaf(CompositeName.WORD);
            leaf.setData(word);
            component.add(leaf);
        }
    }
}
