package com.slabadniak.Task1.Action;

import com.slabadniak.Task1.Builder.BaseBuilder;
import com.slabadniak.Task1.Builder.Director;
import com.slabadniak.Task1.Builder.FileBuilder.FileBuider;
import com.slabadniak.Task1.Builder.HardcoreBuilder.HardcoreBuilder;
import com.slabadniak.Task1.Exeption.InvalidInputData;
import com.slabadniak.Task1.Exeption.NotATriangleExeption;
import com.slabadniak.Task1.Point.Point;
import com.slabadniak.Task1.Triangle.Triangle;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Action {
    public static void main(String[] args){
        ArrayList<Triangle> trianglesFromFile = Director.createTriangles(new FileBuider());
        ArrayList<Triangle> trianglesHardcore = Director.createTriangles(new HardcoreBuilder());
        try {
            Iterator<Triangle> iterator = trianglesFromFile.iterator();
            while (iterator.hasNext()) {
                Triangle triangle = iterator.next();
                double perimetr = triangle.calculatePerimetr();
                BaseBuilder.LOGGER.log(Level.DEBUG, triangle.toString() + " perimetr =" + perimetr);
            }
           iterator = trianglesHardcore.iterator();
            while (iterator.hasNext()) {
                Triangle triangle = iterator.next();
                double area = triangle.calculateArea();
                BaseBuilder.LOGGER.log(Level.DEBUG, triangle.toString()  +" area = " + area);
            }
        }catch (NotATriangleExeption e) {
                BaseBuilder.LOGGER.log(Level.ERROR, "Such tringle can't exist");
            }
    }
}
