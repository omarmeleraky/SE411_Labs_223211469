package edu.psu.se411.lab08.model;

import java.util.ArrayList;
import java.util.List;
import edu.psu.se411.lab08.interfaces.Observer;
import edu.psu.se411.lab08.interfaces.Subject;

public class Sensor implements Subject, Cloneable {
    private String name;
    private double reading;
    private List<Observer> observers;

    public Sensor(String name) {
        this.name = name;
        this.observers = new ArrayList<>();
    }

    public void setReading(double reading) {
        this.reading = reading;
        notifyObservers(); 
    }

    public double getReading() { return reading; }
    public String getName() { return name; }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this);
        }
    }

    @Override
    public Sensor clone() {
        try {
            Sensor cloned = (Sensor) super.clone();
            cloned.observers = new ArrayList<>(); 
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Cloning failed", e);
        }
    }
}