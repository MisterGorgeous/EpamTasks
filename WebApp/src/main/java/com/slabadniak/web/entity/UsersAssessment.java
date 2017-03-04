package com.slabadniak.web.entity;

import java.sql.Timestamp;


public class UsersAssessment {
    private String comment;
    private float rating;
    private Movie movie;
    private String user;
    private Timestamp date;
    private boolean isMarkandText;

    public UsersAssessment(String comment, float rating, Movie movie, String user, Timestamp date) {
        this.comment = comment;
        this.rating = rating;
        this.movie = movie;
        this.user = user;
        this.date = date;
        isMarkandText = false;
    }

    public UsersAssessment(String comment, float rating, Movie movie, String user) {
        this.comment = comment;
        this.rating = rating;
        this.movie = movie;
        this.user = user;
        isMarkandText = true;
    }

    public UsersAssessment(float rating, Movie movie, String user) {
        this.rating = rating;
        this.movie = movie;
        this.user = user;
        isMarkandText = false;
    }

    public String getComment() {
        return comment;
    }

    public float getRating() {
        return rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getUser() {
        return user;
    }
    
    public boolean isMarkandText(){
        return isMarkandText;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setCommentStatus(boolean comment) {
        isMarkandText = comment;
    }


}
