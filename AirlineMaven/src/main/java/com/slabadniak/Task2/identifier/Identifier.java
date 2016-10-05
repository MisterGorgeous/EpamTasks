package com.slabadniak.task2.identifier;

public class Identifier {
    private long id;

    public long getNextId() {
        return ++id;
    }
}
