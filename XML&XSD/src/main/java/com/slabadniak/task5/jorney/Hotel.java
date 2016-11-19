package com.slabadniak.task5.jorney;

public class Hotel {
    private String name;
    private Star stars;

    public String getName() {
        return name;
    }

    public Star getStars() {
        return stars;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStars(Star stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "name='" + name + '\'' +
                ", stars=" + stars +
                '}';
    }
}
