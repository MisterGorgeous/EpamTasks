package com.slabadniak.task4.interpreter;


public class NonTerminalExpression implements Expression {

    private Expression expr1 = null;
    private Expression expr2 = null;

    public NonTerminalExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}
