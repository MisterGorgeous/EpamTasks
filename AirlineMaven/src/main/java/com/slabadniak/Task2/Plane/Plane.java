package com.slabadniak.task2.plane;

import com.slabadniak.task2.engine.Engine;
import com.slabadniak.task2.identifier.Identifier;
import com.slabadniak.task2.planebehavior.PlaneBehavior;
import com.slabadniak.task2.planename.PlaneName;

public abstract class Plane implements PlaneBehavior {
    private static Identifier identifier = new Identifier();
    private long id;
    private PlaneName name;
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
     public void setPlane(PlaneName name, int capacity, float tonnage, float length, float wingspan, int maxSpeed, int rangeOfFlight, int fuel) {
         this.id = identifier.getNextId();
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
        return id;
    }

    public PlaneName getName() {
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
        System.out.println("Plane " + name + " has took off.");
    }

    public void landOn() {
        engine.stopEngine();
        System.out.println("Plane " + name + " has landed on.");
    }
}