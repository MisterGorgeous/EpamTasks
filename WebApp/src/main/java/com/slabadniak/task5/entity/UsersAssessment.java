package com.slabadniak.task5.entity;

public class UsersAssessment {
    private String comment;
    private float rating;
    private String movie;
    private String user;
    private String date;
    private boolean isComment;

    public UsersAssessment(String comment, float rating, String movie, String user, String date) {
        this.comment = comment;
        this.rating = rating;
        this.movie = movie;
        this.user = user;
        this.date = date;
        isComment = false;
    }

    public UsersAssessment(String comment, float rating, String movie, String user) {
        this.comment = comment;
        this.rating = rating;
        this.movie = movie;
        this.user = user;
        isComment = true;
    }

    public UsersAssessment(float rating, String movie, String user) {
        this.rating = rating;
        this.movie = movie;
        this.user = user;
        isComment = false;
    }

    public String getComment() {
        return comment;
    }

    public float getRating() {
        return rating;
    }

    public String getMovie() {
        return movie;
    }

    public String getUser() {
        return user;
    }
    
    public boolean isComment(){
        return isComment;
    }

    public void setCommentStatus(boolean comment) {
        isComment = comment;
    }


}
