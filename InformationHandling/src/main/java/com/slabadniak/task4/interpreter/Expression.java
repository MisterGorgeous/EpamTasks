package com.slabadniak.task4.interpreter;

import com.slabadniak.task4.notation.PolishNotation;

public interface Expression {
    void interpreting(PolishNotation context);
}