package com.slabadniak.task4.chain;

import com.slabadniak.task4.composite.ComponentPattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Handler {
    public static final Logger LOGGER = LogManager.getLogger(Handler.class);
    private Handler nextHandler;

    abstract void parse(String data,ComponentPattern component);

  /*  public void handle(String data){
        String parsedData = parse(data);
        if(nextHandler != null) {
            nextHandler.handle(parsed);
        }

    };*/

    protected void setNextHandler(Handler nextHandler){
        this.nextHandler = nextHandler;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }
}
