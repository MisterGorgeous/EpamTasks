package com.slabadniak.Task1.Builder;

import com.slabadniak.Task1.Action.Action;
import com.slabadniak.Task1.Triangle.Triangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.util.ArrayList;

public abstract class BaseBuilder {
    public static final Logger LOGGER = LogManager.getLogger(BaseBuilder.class);
    private ArrayList<Triangle> triangles;

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        File file = new File("src\\main\\resources\\log4j2");
        context.setConfigLocation(file.toURI());
    }

    public  ArrayList<Triangle> getTriangles(){
        return triangles;
    }

    public void setTriangles(ArrayList<Triangle> triangles){
        this.triangles = triangles;
    }

    public abstract void buildTriangles();
}