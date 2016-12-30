package com.slabadniak.task5.entity;

/**
 * Created by Siarhei on 30.12.2016.
 */
public class Actor {
    private String firstName;
    private String seccondName;
    private String birstday;

    public Actor(String firstName, String seccondName, String birstday) {
        this.firstName = firstName;
        this.seccondName = seccondName;
        this.birstday = birstday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSeccondName() {
        return seccondName;
    }

    public String getBirstday() {
        return birstday;
    }

}
