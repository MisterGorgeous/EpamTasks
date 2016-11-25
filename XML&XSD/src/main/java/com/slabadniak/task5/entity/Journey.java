package com.slabadniak.task5.entity;


public abstract class Journey {
    private String id;
    private int days;
    private float cost;
    private Transport transport;

    public void setId(String id) {
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

    public String getId() {
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

    @Override
    public String toString() {
        return "Journey{"  + "id='" + id + '\'' + ", days=" + days + ", cost=" + cost + ", transport=" + transport + '}';
    }
}
