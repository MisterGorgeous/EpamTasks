package com.slabadniak.Task1.Builder;


import com.slabadniak.Task1.Builder.BaseBuilder;
import com.slabadniak.Task1.Exeption.NotATriangleExeption;
import com.slabadniak.Task1.Point.Point;
import com.slabadniak.Task1.Triangle.Triangle;
import org.apache.logging.log4j.Level;

import java.util.ArrayList;

public class HardcodeBuilder extends BaseBuilder {
    @Override
    public void buildTriangles() {
        ArrayList<Triangle> triangles = new ArrayList<Triangle>();
        try {
            Triangle triangle = new Triangle(new Point(12, 1), new Point(1, 13), new Point(15, 16));
            triangle.checkTringle();
            triangles.add(triangle);
            triangle = new Triangle(new Point(15, 41), new Point(1, 13), new Point(155, 16));
            triangle.checkTringle();
            triangles.add(triangle);
            triangle = new Triangle(new Point(12, 51), new Point(11, 13), new Point(15, 16));
            triangle.checkTringle();
            triangles.add(triangle);
            setTriangles(triangles);
        } catch (NotATriangleExeption e) {
            LOGGER.log(Level.ERROR, "Such tringle can't exist", e);
        }
    }
}
