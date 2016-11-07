package com.slabadniak.task4.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private CompositeName rootName;
    private List<Component> descendants;

    public Composite(CompositeName rootName) {
        this.rootName = rootName;
        descendants = new ArrayList<Component>();
    }

    @Override
    public CompositeName getRootName() {
        return rootName;
    }

    @Override
    public void add(Component descendant) {
        descendants.add(descendant);
    }

    public List<Component> getDescendant() {
        return descendants;
    }

    @Override
    public void setData(String data) {
        //
    }

    @Override
    public String getData() {
        return null;
    }

    @Override
    public String toString() {
        String resString = "";
        for(Component component : descendants){
            resString = resString + component.toString();
        }
        return resString;
    }
}
