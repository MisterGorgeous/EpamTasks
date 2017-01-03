package com.slabadniak.task5.entity;


public class Actor {
    private String firstName;
    private String seccondName;
    private String role;
    private String birthday;
    private String birthplace;

    public Actor(String firstName, String seccondName, String role, String birthday, String birthplace) {
        this.firstName = firstName;
        this.seccondName = seccondName;
        this.role = role;
        this.birthday = birthday;
        this.birthplace = birthplace;
    }

    public Actor(String firstName, String seccondName, String birstday) {
        this.firstName = firstName;
        this.seccondName = seccondName;
        this.role = birstday;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSeccondName() {
        return seccondName;
    }

    public String getRole() {
        return role;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBirthplace() {
        return birthplace;
    }
}
