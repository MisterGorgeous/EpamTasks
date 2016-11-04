package com.slabadniak.task4.composite;


public class Leaf implements ComponentPattern {
    private String word;

    public void add(ComponentPattern component) {
        //
    }

    public ComponentPattern getDescendant() {
        return null;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String toString() {
        return word;
    }
}
