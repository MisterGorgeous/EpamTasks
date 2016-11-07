package com.slabadniak.task4.composite;

import java.util.List;

public interface Component {
    void add(Component component);
    List<Component> getDescendant();
    void setData(String data);
    String getData();
    CompositeName getRootName();
}
