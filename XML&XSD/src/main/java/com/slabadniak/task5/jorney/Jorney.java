package com.slabadniak.task5.jorney;


public abstract class Jorney {
    private int id;
    private int days;
    private float cost;
    private Transport transport;

    public void setId(int id) {
        this.id = id;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public int getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public float getCost() {
        return cost;
    }

    public Transport getTransport() {
        return transport;
    }
}
