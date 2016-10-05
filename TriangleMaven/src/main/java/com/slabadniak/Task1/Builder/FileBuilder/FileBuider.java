package com.slabadniak.Task1.Builder.FileBuilder;

import com.slabadniak.Task1.Builder.BaseBuilder;
import com.slabadniak.Task1.Exeption.InvalidInputDataExeption;
import com.slabadniak.Task1.Exeption.NotATriangleExeption;
import com.slabadniak.Task1.Point.Point;
import com.slabadniak.Task1.Triangle.Triangle;
import org.apache.logging.log4j.Level;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileBuider extends BaseBuilder {
    @Override
    public void buildTriangles() {
        try {
            FileReader fr = new FileReader("src\\main\\java\\com\\slabadniak\\Task1\\File\\DATA");
            LOGGER.log(Level.INFO, "DATAFileOpened");
            BufferedReader br = new BufferedReader(fr);
            ArrayList<Triangle> triangles = new ArrayList<Triangle>();
            String str;

            while ((str = br.readLine()) != null) {
                String[] values = str.split(" ");
                if(values.length != 6)
                    throw new InvalidInputDataExeption("It must have 6 arguments");
                Triangle triangle = new Triangle(new Point(Double.parseDouble(values[0]), Double.parseDouble(values[1])),
                        new Point(Double.parseDouble(values[2]), Double.parseDouble(values[3])),
                        new Point(Double.parseDouble(values[4]), Double.parseDouble(values[5])));
                triangle.checkTringle();
                triangles.add(triangle);
            }

            setTriangles(triangles);
            fr.close();
            LOGGER.log(Level.INFO, "DATAFileClosed");

        } catch (InvalidInputDataExeption e) {
            LOGGER.log(Level.ERROR, "Wrong number of arguments", e);
        }catch (NotATriangleExeption e) {
            LOGGER.log(Level.ERROR, "Such tringle can't exist", e);
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.ERROR, "FileNotFound", e);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "FileReadError", e);
        }
    }
}
