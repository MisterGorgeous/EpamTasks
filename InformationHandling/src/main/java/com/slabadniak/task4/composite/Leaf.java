package com.slabadniak.task4.composite;


import java.util.List;

public class Leaf implements Component {
    private CompositeName level;
    private String word;

    public Leaf(CompositeName level) {
        this.level = level;
    }

    @Override
    public void add(Component component) {
        //
    }

    @Override
    public List<Component> getDescendant() {
        return null;
    }

    @Override
    public void setData(String word) {
        this.word = word;
    }

    @Override
    public String getData() {
        return word;
    }

    @Override
    public CompositeName getRootName() {
        return level;
    }

    @Override
    public String toString() {
        return word;
    }
}
