package edu.psu.se411.lab08.observers;

import edu.psu.se411.lab08.interfaces.Observer;
import edu.psu.se411.lab08.interfaces.Subject;
import edu.psu.se411.lab08.model.Sensor;

public class DashboardObserver implements Observer {
    @Override
    public void update(Subject subject) {
        if (subject instanceof Sensor) {
            Sensor sensor = (Sensor) subject;
            System.out.printf("[Dashboard] %s reading changed to: %.2f%n", sensor.getName(), sensor.getReading());
        }
    }
}