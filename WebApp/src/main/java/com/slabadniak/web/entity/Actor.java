package com.slabadniak.web.entity;


public class Actor {
    private String firstName;
    private String seccondName;
    private String birthday;
    private String birthplace;
    private String role;
    private String profession;

    public Actor(String firstName, String seccondName,String birthday, String birthplace, String role,  String profession) {
        this.firstName = firstName;
        this.seccondName = seccondName;
        this.birthday = birthday;
        this.birthplace = birthplace;
        this.role = role;
        this.profession = profession;
    }

    public Actor(String firstName, String seccondName, String role, String birthday, String birthplace) {
        this.firstName = firstName;
        this.seccondName = seccondName;
        this.role = role;
        this.birthday = birthday;
        this.birthplace = birthplace;
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

    public String getProfession() {
        return profession;
    }
}
