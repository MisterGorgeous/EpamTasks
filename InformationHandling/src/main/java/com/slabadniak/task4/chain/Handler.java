package com.slabadniak.task4.chain;

import com.slabadniak.task4.composite.Component;

public abstract class Handler {
    private Handler nextHandler;

    public Handler getNextHandler() {
        return nextHandler;
    }

    //using for initialing next handler
    protected void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    abstract void parse(String data, Component component);
}
