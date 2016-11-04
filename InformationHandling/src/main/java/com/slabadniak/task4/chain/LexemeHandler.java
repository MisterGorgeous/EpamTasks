package com.slabadniak.task4.chain;

import com.slabadniak.task4.composite.ComponentPattern;
import com.slabadniak.task4.composite.Leaf;
import com.slabadniak.task4.interpreter.TerminalExpression;
import com.slabadniak.task4.notation.PolishNotation;
import com.slabadniak.task4.regular.Regular;
import org.apache.logging.log4j.Level;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeHandler extends Handler {
    public LexemeHandler() {

    }

    void parse(String text, ComponentPattern component) {
        ArrayList<String> words = new ArrayList<>();
        Pattern p = Pattern.compile(Regular.WORD);
        Matcher m = p.matcher(text);
        String str ;

        if (!m.find()) {
            p = Pattern.compile(Regular.EXPRESION);
            m = p.matcher(text);
            m.find();
            str = text.substring(m.start(), m.end());
            PolishNotation notation = new PolishNotation();
            notation.make(str);
            new TerminalExpression().interpreting(notation);
            str = notation.getCalculationResult();
            LOGGER.log(Level.INFO,str);
        }
        else{
            str = text.substring(m.start(), m.end());
        }

        words.add(str);
        if (m.start() > 0) {
            words.add(0, text.substring(0, m.start()));
        }
        if (m.end() <= text.length() - 1) {
            words.add(text.substring(m.end(), text.length() ));
        }

        for(String word: words) {
            Leaf leaf = new Leaf();
            leaf.setWord(word);
            component.add(leaf);
        }
      /*  Stream
                .of(text)
                .map(p::matcher)
                .filter(Matcher::matches)
                .findFirst()
                .ifPresent(matcher -> leaf.setAfterWord(matcher.group(1)));*/

    }
}
