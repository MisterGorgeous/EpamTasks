package com.slabadniak.task5.entity;

public class Movie {
    private String title;
    private float rating;
    private String icon;
    private String year;
    private String country;
    private String description;


    public Movie(String title, float rating, String icon, String year, String country, String description) {
        this.title = title;
        this.rating = rating;
        this.icon = icon ;
        this.year = year;
        this.country = country;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {

        return country;
    }

    public float getRating() {
        return rating;
    }

    public String getIcon() {
        return icon;
    }

    public String getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
