package com.slabadniak.Task2.Plane;

import com.slabadniak.Task2.Engine.Engine;
import com.slabadniak.Task2.PlaneBehavior.PlaneBehavior;

public abstract class Plane implements PlaneBehavior {
    private static long INCR;
    private long Id = ++INCR;
    private int capacity; //people
    private float tonnage; //kg
    private float length; //m
    private float wingspan; //m
    private int maxSpeed; //km/h
    private int rangeOfFlight; //km
    private int fuel; //kg
    private Engine engine;

     /*public Plane(int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
        this.capacity = capacity;
        this.tonnage = tonnage;
        this.length = length;
        this.wingspan = wingspan;
        this.maxSpeed = maxSpeed;
        this.rangeOfFlight = rangeOfFlight;
        this.fuel = fuel;
    }*/
     public void setPlane(int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
         this.capacity = capacity;
         this.tonnage = tonnage;
         this.length = length;
         this.wingspan = wingspan;
         this.maxSpeed = maxSpeed;
         this.rangeOfFlight = rangeOfFlight;
         this.fuel = fuel;
     }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getCapacity() {
        return capacity;
    }

    public float getTonnage() {
        return tonnage;
    }

    public int getRangeOfFlight() {
        return rangeOfFlight;
    }

    public int getFuel() {
        return fuel;
    }

    public float getLength() {
        return length;
    }

    public double getConsumtionOfFuel(){
        return (double) rangeOfFlight / (double) fuel;
    }

    public long getId() {
        return Id;
    }

    public void fly() {
        System.out.println("Plane with id:" + Long.toString(Id) + " is flying.");
    }

    public void takeOff() {
        System.out.println("Plane with id:" + Long.toString(Id) + " has took off.");
    }

    public void landOn() {
        System.out.println("Plane with id:" + Long.toString(Id) + " has landed off.");
    }
}
