package com.slabadniak.task5.jorney;

/**
 * Created by Siarhei on 19.11.2016.
 */
public class Rest extends Jorney{
    private String country;
    private Hotel hotel;
    private String food;
    private String room;
    private boolean tv;
    private boolean airCondition;

    public String getCountry() {
        return country;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public String getFood() {
        return food;
    }

    public String getRoom() {
        return room;
    }

    public boolean isTv() {
        return tv;
    }

    public boolean isAirCondition() {
        return airCondition;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAirCondition(boolean airCondition) {
        this.airCondition = airCondition;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
