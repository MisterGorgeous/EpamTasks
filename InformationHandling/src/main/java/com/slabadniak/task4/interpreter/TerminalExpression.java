package com.slabadniak.task4.interpreter;

import com.slabadniak.task4.notation.Operation;
import com.slabadniak.task4.notation.PolishNotation;

public class TerminalExpression implements Expression {

    @Override
    public void interpreting(PolishNotation context) {
        if (context.getNotation().isEmpty()) {
            return;
        }

        //add numbers in result stack
        while (!Operation.isOperation(context.getNotation().getFirst())) {
            context.getResultStack().addFirst(Integer.parseInt(context.getNotation().poll()));
        }


        if (Operation.isUnary(context.getNotation().getFirst())) {
            new UnaryExpression().interpreting(context);
        } else {
            new BinaryExpression().interpreting(context);
        }
    }
}