package com.slabadniak.task4;

import com.slabadniak.task4.chain.TextHandler;
import com.slabadniak.task4.composite.Root;
import com.slabadniak.task4.interpreter.NonTerminalExpression;
import com.slabadniak.task4.interpreter.TerminalExpression;
import com.slabadniak.task4.notation.PolishNotation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Action {
    public  static void main(String[] args){
        final Logger LOGGER = LogManager.getLogger(Action.class);
        Root tree = new Root();
        TextHandler handler = new TextHandler();
        handler.parse("\tIt has survived not only five centuries, but also the leap into " +
                "13+(3++) electronic --3 typesetting, remaining 3+5 essentially 6+9*(3-" +
                "4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5-4)-3)-2)-1) with" +
                " the release of Letraset sheets containing Lorem Ipsum passages, and more" +
                " recently with desktop publishing software like Aldus PageMaker including" +
                " versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by" +
                " the readable content of a page when looking at its layout. The point of" +
                " using (0-(2*2*(3*(2-1/2*2)-2)-10/2))*(++5) Ipsum is that it has a more-" +
                "or-less normal distribution of letters, as opposed to using 'Content" +
                "here, content here', making it look like readable English." +
                "\tIt is a (5+1/2*(2+5*2))*1200 established fact that a reader will" +
                " be of a page when looking at its layout.\n" +
                "\tHello.\n", tree);
        LOGGER.log(Level.INFO, tree.toString());

      /*  PolishNotation notation = new PolishNotation();
        notation.make("3+5-");
        new TerminalExpression().interpreting(notation);
        LOGGER.log(Level.INFO, notation.getResultStack().toString());*/
    }
}
