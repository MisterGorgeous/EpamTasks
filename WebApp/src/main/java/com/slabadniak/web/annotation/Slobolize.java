package com.slabadniak.web.annotation;

import com.slabadniak.web.configuration.ConfigurationManager;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;


/**
 * This class is doing all work concerning serialization.
 * @author Slabadniak Sergei
 * @version 1.0
 */
public class Slobolize {
    private static ReentrantLock lock = new ReentrantLock();
    private static final String LETTRERS = ConfigurationManager.getProperty("path.file.letters");
    private static final String COUNTER = ConfigurationManager.getProperty("path.file.counter");


    /**
     * This method save letter in the file.
     * @param letter wasn't been send to the user.
     */
    public static void writeLetter(UserLetter letter) {
        if (Objects.isNull(letter)) {
            return;
        }
        Class<?> cl = letter.getClass();
        Slobolizable sl = cl.getAnnotation(Slobolizable.class);
        if (Objects.isNull(sl)) {
            return;
        }
        int numLetters = -1;
        List<UserLetter> letters = null;

        lock.lock();
        try {
            //lettres qamtity
            try (Scanner scanner = new Scanner(Paths.get(COUNTER))) {
                numLetters = scanner.nextInt();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //take lettres from file
            if (numLetters > 0) {
                try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(LETTRERS)))) {
                    letters = (ArrayList<UserLetter>) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                letters = new ArrayList<>();
            }

            letters.add(letter); //adding new letter

            //save lettres
            try (ObjectOutputStream outstream = new ObjectOutputStream(Files.newOutputStream(Paths.get(LETTRERS)))) {
                outstream.writeObject(letters); //write letters
            } catch (IOException e) {
                e.printStackTrace();
            }

            //renew leters size
            try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(COUNTER), StandardCharsets.UTF_8))) {
                writer.print(letters.size());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } finally {
            lock.unlock();
        }
    }


    /**
     * @return list of the letters, that are going to be sent to the recipients.
     */
    public static List<UserLetter> readLetters() {
        List<UserLetter> letters = null;
        lock.lock();
        try {
            try (Scanner scanner = new Scanner(Paths.get(COUNTER))) {
                int numLetters = scanner.nextInt();
                if (numLetters == 0) {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(LETTRERS)));) {
                letters = (ArrayList<UserLetter>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        } finally {
            lock.unlock();
        }

        return letters;
    }


    /**
     * This method is to make counter of letters, that hasn't been sent, equal to 0.
     */
    public static void cleanFile() {
        lock.lock();
        try {
            try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(COUNTER), StandardCharsets.UTF_8))) {
                writer.print(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

}
