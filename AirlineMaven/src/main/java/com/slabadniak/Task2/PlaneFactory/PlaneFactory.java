package com.slabadniak.task2.planefactory;

import com.slabadniak.task2.datachecker.DataChecker;
        import com.slabadniak.task2.plane.Plane;
        import org.apache.logging.log4j.Level;
        import org.apache.logging.log4j.LogManager;
        import org.apache.logging.log4j.Logger;
        import org.apache.logging.log4j.core.LoggerContext;

        import javax.xml.crypto.Data;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.nio.file.Paths;
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Collectors;
        import java.util.stream.Stream;

public abstract class PlaneFactory<T extends Plane> {
    private static final String AIRPORT_PATH = "src\\main\\resources\\Planes";
    private static final String LOG_PATH = "src\\main\\resources\\log4j2";
    private static final Logger LOGGER = LogManager.getLogger(PlaneFactory.class);
    private static final String DEFAULT_PLANE = "BOEING747 366 69.20f  17.30f  57.90f  1024  9900  27562  JT9D 213.07f  4010";

    static {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.setConfigLocation(new File(LOG_PATH).toURI());
    }

    public abstract T createPlane(String model);

    public List<String> getAtributes(String model){
        return makeAtributes(readPlanes(model));
    }

    private String readPlanes(String model) {
        Stream<String> stream = null;
        try {
            stream = Files.lines(Paths.get(AIRPORT_PATH));
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e);
        }

        String textPlane = stream
                .filter(p -> DataChecker.checkPlane(p))
                .filter(p -> p.startsWith(model.toUpperCase()))
                .findFirst().orElse(DEFAULT_PLANE);
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
