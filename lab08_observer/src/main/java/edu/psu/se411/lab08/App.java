package edu.psu.se411.lab08;

import edu.psu.se411.lab08.model.Sensor;
import edu.psu.se411.lab08.observers.DashboardObserver;
import edu.psu.se411.lab08.observers.LoggerObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application is starting...");

        Sensor temp = new Sensor("Temperature");
        Sensor humidity = new Sensor("Humidity");

        DashboardObserver dashboard = new DashboardObserver();
        LoggerObserver logObserver = new LoggerObserver();

        temp.register(dashboard);
        temp.register(logObserver);
        humidity.register(dashboard);
        humidity.register(logObserver);

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            temp.setReading(20 + random.nextDouble() * 15);
            humidity.setReading(40 + random.nextDouble() * 20);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error("Simulation interrupted", e);
            }
        }

        logger.info("--- Testing Cloneable Sensor ---");
        Sensor clonedTemp = temp.clone();
        
        clonedTemp.register(dashboard);
        clonedTemp.setReading(99.99); 

        logger.info("Application is stopping...");
    }
}