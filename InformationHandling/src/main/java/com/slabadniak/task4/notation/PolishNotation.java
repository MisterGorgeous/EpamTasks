package com.slabadniak.task4.notation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

public class PolishNotation {
    private Deque<String> notation;
    private Deque<String> operationStack;
    private Deque<Integer> resultStack;

    public PolishNotation() {
        notation = new LinkedList<>();
        operationStack = new LinkedList<>();
        resultStack = new LinkedList<>();
    }

    public Deque<String> getNotation() {
        return notation;
    }

    public Deque<String> getOperationStack() {
        return operationStack;
    }

    public Deque<Integer> getResultStack() {
        return resultStack;
    }

    public String getResult() {
        return resultStack.poll().toString();
    }

    public void make(String expression) {
        char[] symbols = expression.toCharArray();

        for (int i = 0; i < symbols.length; ++i) {
            String number = "";

            //make number from digits
            while (i < symbols.length && Character.isDigit(symbols[i])) {
                number = number + symbols[i];
                ++i;
            }

            if (number != "") {
                notation.add(number);
            }
            //if number is last in notation
            if (i == symbols.length) {
                break;
            }

            if (symbols[i] == '(') {
                operationStack.addFirst(String.valueOf(symbols[i]));
            } else if (symbols[i] == ')') {
                while (!operationStack.getFirst().equals("(")) {
                    notation.add(operationStack.poll());
                }
                operationStack.poll();
            } else {
                String nextOperation;

                //check for unary
                if (symbols[i] == symbols[i + 1]) {
                    if (i + 2 != symbols.length && Character.isDigit(symbols[i + 2])) {
                        nextOperation = String.valueOf(symbols[i]) + String.valueOf(symbols[i + 1]) + "i";
                    } else {
                        nextOperation = String.valueOf(symbols[i]) + String.valueOf(symbols[i + 1]) + "p";
                    }
                    ++i;
                } else {
                    nextOperation = String.valueOf(symbols[i]);
                }

                while (!operationStack.isEmpty() && Operation.comparePriorities(operationStack.getFirst(), nextOperation) > 0
                        && !operationStack.getFirst().equals("(")) {
                    notation.add(operationStack.poll());
                }
                operationStack.addFirst(nextOperation);
            }

        }

        while (!operationStack.isEmpty()) {
            notation.add(operationStack.poll());
        }
    }
}