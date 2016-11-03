package com.slabadniak.task4.notation;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

public class PolishNotation {
    public static Deque<Character> notation = new LinkedList<>();
    private static Deque<Character> operationStack = new LinkedList<>();

    public static void make(String expression) {

        char[] symbols = expression.toCharArray();

        for (char nextSymbol : symbols) {
            if (Character.isDigit(nextSymbol)) {
                notation.add(nextSymbol);
            } else {//(Operations.isOperation(nextSymbol)) {
                if(nextSymbol == '('){
                    operationStack.addFirst(nextSymbol);
                } else if (nextSymbol == ')') {
                    while (operationStack.getFirst() != '(') {
                        notation.add(operationStack.poll());
                    }
                    operationStack.poll();
                } else {
                    while (!operationStack.isEmpty() && Operations.comparePriorities(operationStack.getFirst(), nextSymbol) >= 0
                            && operationStack.getFirst() != '(') {
                        notation.add(operationStack.poll());
                    }
                    operationStack.addFirst(nextSymbol);
                }
            }
        }

        while (!operationStack.isEmpty()) {
            notation.add(operationStack.poll());
        }
    }
}

class Operations {
   // public static final char[] operations = {'+', '-' , '*', '/'};
    public static final ArrayList<Character> operations = new ArrayList<Character>() {
        {
            add(new Character('('));
            add(new Character(')'));
            add(new Character('+'));
            add(new Character('-'));
            add(new Character('*'));
            add(new Character('/'));
        }
    };

    /*static int comparePriorities(char f, char s){
        Comparator<Character> .comparing()
    }*/



    static boolean isOperation(char symbol){
        return operations.contains(symbol);
      /*  for(char operation : operations) {
            if (symbol == operation) {
                return true;
            }
        }
        return false;*/
    }

   /* static int getPriority(char symbol){

        /*for(char operation : operations) {
            if (symbol == operation) {
                return true;
            }
        }
        return false;*/


    static int comparePriorities(char f, char s){
        return Integer.compare(operations.indexOf(f)/2,operations.indexOf(s)/2);
    }

}
