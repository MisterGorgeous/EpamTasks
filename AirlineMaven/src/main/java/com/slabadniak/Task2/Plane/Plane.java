package com.slabadniak.Task2.Plane;

import com.slabadniak.Task2.Engine.Engine;
import com.slabadniak.Task2.Exeption.InvalidArgumentExeption;
import com.slabadniak.Task2.PlaneBehavior.PlaneBehavior;
import com.slabadniak.Task2.Plane.PlaneAtribute.planeAtribute;

public abstract class Plane implements PlaneBehavior {
    private static long INCR;
    private long Id = ++INCR;
    private String name;
    private int capacity;      //people
    private float tonnage;     //kg
    private float length;      //m
    private float wingspan;    //m
    private int maxSpeed;      //km/h
    private int rangeOfFlight; //km
    private int fuel;          //kg
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
     public void setPlane(String name,int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
         this.name = name;
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
      public int getMaxSpeed() {
        return maxSpeed;
    }

    public double getConsumtionOfFuel(){
        return (double) rangeOfFlight / (double) fuel;
    }

    public long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

/* public <T extends Number> T getAtribute (planeAtribute atribute){
       if(atribute ==  planeAtribute.CAPACITY) {
          return (T) new Integer(capacity);
       } else if(atribute ==  planeAtribute.TONNAGE){
           return(T) new Float(tonnage);
       } else if(atribute ==  planeAtribute.LENGTH){
           return (T) new Float(length);
       } else if(atribute ==  planeAtribute.WINGSPAN){
           return (T) new Float(wingspan);
       } else if(atribute ==  planeAtribute.MAXSPEED){
           return (T) new Integer(maxSpeed);
       } else if(atribute ==  planeAtribute.RANGEOFFLIGHT){
           return (T) new Integer(rangeOfFlight);
       } else{
           return (T) new Double((double) rangeOfFlight / (double) fuel);
       }
    }
*/

    public boolean isPlaneFlying() {
        return engine.isEngineWorking();
    }

    public float fly(int distance) {
        return (float)distance / (float) maxSpeed;
    }

    public void takeOff() {
        engine.startEngine();
        System.out.println("Plane with id:" + Long.toString(Id) + " has took off.");
    }

    public void landOn() {
        engine.stopEngine();
        System.out.println("Plane with id:" + Long.toString(Id) + " has landed off.");
    }
}