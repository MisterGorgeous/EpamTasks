package com.slabadniak.task4;

import com.slabadniak.task4.chain.TextHandler;
import com.slabadniak.task4.composite.Root;
import com.slabadniak.task4.notation.PolishNotation;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Action {
    public  static void main(String[] args){
        final Logger LOGGER = LogManager.getLogger(Action.class);
       /* Root tree = new Root();
        TextHandler handler = new TextHandler();
        handler.parse("It has survived not only five centuries, but also the leap into\n" +
                "13+(3++) electronic --3 typesetting, remaining 3+5- essentially 6+9*(3-\n" +
                "4) unchanged. It was popularised in the 5*(1*2*(3*(4*(5-4)-3)-2)-1) with\n" +
                " the release of Letraset sheets containing Lorem Ipsum passages, and more\n" +
                " recently with desktop publishing software like Aldus PageMaker including\n" +
                " versions of Lorem Ipsum.\n" +
                "\tIt is a long established fact that a reader will be distracted by\n" +
                " the readable content of a page when looking at its layout. The point of\n" +
                " using (0-(2*2*(3*(2-1/2*2)-2)-10/2))*(++5) Ipsum is that it has a more-\n" +
                "or-less normal distribution of letters, as opposed to using 'Content\n" +
                "here, content here', making it look like readable English.\n" +
                "\tIt is a (-5+1/2*(2+5*2))*1200 established fact that a reader will\n" +
                " be of a page when looking at its layout.\n" +
                "\tHello.\n", tree);
        */

        PolishNotation.make("(0-(2*2*(3*(2-1/2*2)-2)-10/2))");
        LOGGER.log(Level.INFO,PolishNotation.notation.toString());
    }
}
