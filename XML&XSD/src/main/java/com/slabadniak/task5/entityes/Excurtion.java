package com.slabadniak.task5.entityes;

import java.util.ArrayList;

public class Excurtion extends Jorney {
    private ArrayList<String> contries;

    public Excurtion(){
        contries = new ArrayList<String>();
    }

    public ArrayList<String> getContries() {
        return contries;
    }

    public void setContries(String country) {
        this.contries.add(country);
    }

    @Override
    public String toString() {
        return "Excurtion{" + super.toString() +
                "contries=" + contries +
                '}';
    }
}
