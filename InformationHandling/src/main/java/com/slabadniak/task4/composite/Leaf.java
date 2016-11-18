package com.slabadniak.task4.composite;

import java.util.List;

public class Leaf implements Component {
    private CompositeName rootName;
    private String word;

    public Leaf(CompositeName level) {
        this.rootName = level;
    }

    @Override
    public void add(Component component) {
        return;
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
        return rootName;
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public Leaf clone() {
        Leaf copy = null;
        try {
            copy = (Leaf) super.clone();
            copy.rootName = this.rootName;
            copy.word = this.word;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
