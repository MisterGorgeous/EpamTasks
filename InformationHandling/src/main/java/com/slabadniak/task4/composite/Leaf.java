package com.slabadniak.task4.composite;


import org.apache.logging.log4j.Level;

public class Leaf implements ComponentPattern {
    private String word;
    private String beforeWord;
    private String afterWord;

    public Leaf() {
        this.beforeWord = "";
        this.word = "";
        this.afterWord = "";
    }

    public void add(ComponentPattern component) {
        //
    }

    public ComponentPattern getDescendant() {
        return null;
    }

    public void setBeforeWord(String beforeWord) {
        this.beforeWord = beforeWord;
    }

    public void setAfterWord(String afterWord) {
        this.afterWord = afterWord;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getAfterWord() {
        return afterWord;
    }

    @Override
    public String toString() {
        return beforeWord + word + afterWord;
    }
}
