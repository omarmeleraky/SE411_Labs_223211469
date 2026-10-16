package edu.psu.se411.lab08.observers;

import edu.psu.se411.lab08.interfaces.Observer;
import edu.psu.se411.lab08.interfaces.Subject;
import edu.psu.se411.lab08.model.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerObserver implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(LoggerObserver.class);

    @Override
    public void update(Subject subject) {
        if (subject instanceof Sensor) {
            Sensor sensor = (Sensor) subject;
            logger.info("[LoggerObserver] {} reading updated to: {}", sensor.getName(), sensor.getReading());
        }
    }
}