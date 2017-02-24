package com.slabadniak.web.annotation;

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


public class Slobolize {
    private static final String LETTRERS ="S:\\git_rep\\Epam\\WebApp\\src\\main\\webapp\\letters\\userLetters";
    private static final String COUNTER ="S:\\git_rep\\Epam\\WebApp\\src\\main\\webapp\\letters\\lettersCounter";

    public static void writeLetter(UserLetter letter){
        if(Objects.isNull(letter)){
            return;
        }
        Class<?> cl = letter.getClass();
        Slobolizable sl = cl.getAnnotation(Slobolizable.class);
        if(Objects.isNull(sl)){
            return;
        }
        int numLetters = -1;
        List<UserLetter> letters = null;

        //lettres qamtity
        try(Scanner scanner = new Scanner(Paths.get(COUNTER))){
            numLetters = scanner.nextInt();
        } catch (IOException e) {e.printStackTrace();}

        //take lettres from file
        if(numLetters > 0){
            try(ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(LETTRERS)))) {
                letters = (ArrayList<UserLetter>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
        }else {
            letters = new ArrayList<>();
        }

        letters.add(letter); //adding new letter

        //save lettres
        try(ObjectOutputStream outstream = new ObjectOutputStream(Files.newOutputStream(Paths.get(LETTRERS)))) {
            outstream.writeObject(letters); //write letters
        } catch (IOException e) {e.printStackTrace();}

        //renew leters size
        try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(COUNTER), StandardCharsets.UTF_8))) {
            writer.print(letters.size());
        } catch (IOException e) {e.printStackTrace();}
    }


    public static List<UserLetter> readLetters(){
        List<UserLetter> letters = null;

        try(Scanner scanner = new Scanner(Paths.get(COUNTER))) {
            int numLetters = scanner.nextInt();
            if(numLetters == 0) {
                return null;
            }
        } catch (IOException e) {e.printStackTrace();}

        try( ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(LETTRERS)));) {
            letters = (ArrayList<UserLetter>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {e.printStackTrace();}

        return letters;
    }

    public static void cleanFile(){
        try(PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(COUNTER), StandardCharsets.UTF_8))) {
            writer.print(0);
        } catch (IOException e) {e.printStackTrace();}
    }

}
