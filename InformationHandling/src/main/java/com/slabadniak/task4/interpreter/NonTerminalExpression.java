package com.slabadniak.task4.interpreter;

import com.slabadniak.task4.notation.PolishNotation;

public class NonTerminalExpression implements Expression {

    @Override
   public void interpreting(PolishNotation context) {
        int arg1 = context.getResultStack().poll();
        int arg2 = context.getResultStack().poll();

        switch (context.getNotation().getFirst()) {
            case "+":
                context.getResultStack().addFirst(arg2 + arg1);
                break;
            case "-":
                context.getResultStack().addFirst(arg2 - arg1);
                break;
            case "*":
                context.getResultStack().addFirst(arg2 * arg1);
                break;
            default:
                context.getResultStack().addFirst(arg2 / arg1);
                break;

        }

        context.getNotation().poll();
        new TerminalExpression().interpreting(context);
    }
}
