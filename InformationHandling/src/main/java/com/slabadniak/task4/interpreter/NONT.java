package com.slabadniak.task4.interpreter;


import com.slabadniak.task4.notation.PolishNotation;

public class NONT implements Expression {


    @Override
    public void interpreting(PolishNotation context) {
        int arg = context.getResultStack().poll();

        switch (context.getNotation().getFirst()) {
            case "++p":
                context.getResultStack().addFirst(arg);
                break;
            case "--p":
                context.getResultStack().addFirst(arg);
                break;
            case "++i":
                context.getResultStack().addFirst(++arg);
                break;
            default:
                context.getResultStack().addFirst(--arg);
                break;

        }

        context.getNotation().poll();
        new TerminalExpression().interpreting(context);
    }
}
