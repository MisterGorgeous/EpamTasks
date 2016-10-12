package com.slabadniak.task2.planefactory;

import com.slabadniak.task2.constant.Constant;
import com.slabadniak.task2.datachecker.DataChecker;
import com.slabadniak.task2.plane.Plane;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class PlaneFactory<T extends Plane> {
    private static final Logger LOGGER = LogManager.getLogger(PlaneFactory.class);

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(true);
        context.setConfigLocation(new File(Constant.LOG_PATH).toURI());
    }

    public abstract T createPlane(String model);

    public List<String> getAtributes(String model) {
        return makeAtributes(readPlanes(model));
    }

    private String readPlanes(String model) {
        Stream<String> stream;
        try {
            stream = Files.lines(Paths.get(Constant.AIRPORT_PATH));
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, e);
            throw new RuntimeException();
        }

        String textPlane = stream
                .filter(p -> DataChecker.checkPlane(p))
                .filter(p -> p.startsWith(model.toUpperCase()))
                .findFirst().orElse(Constant.DEFAULT_PLANE);
        return textPlane;
    }

    private List<String> makeAtributes(String textPlane) {
        List<String> atributes = Stream
                .of(textPlane)
                .map(p -> p.split(" "))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        return atributes;
    }
}
