package com.slabadniak.task4;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextReader {
    private static final Logger LOGGER = LogManager.getLogger(TextReader.class);
    private static final String FILE_PATH = "file/text.txt";

    public static String getText() {
        Stream<String> stream;
        try {
            stream = Files.lines(Paths.get(FILE_PATH));
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, e);
            throw new RuntimeException();
        }

        String text = stream
                .collect(Collectors.joining());
        return text;
    }
}
