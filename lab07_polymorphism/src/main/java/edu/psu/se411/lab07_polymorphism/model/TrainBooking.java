package edu.psu.se411.lab07_polymorphism.model;

import edu.psu.se411.lab07_polymorphism.config.GlobalConfig;
import edu.psu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public class TrainBooking extends Booking {
    private TrainSeatClass seatClass;
    private Double distance;

    public TrainBooking(String bookingId, String customerName, String travelDate, String destinationCity, TrainSeatClass seatClass) {
        super(bookingId, customerName, travelDate, destinationCity);
        this.seatClass = seatClass;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public double computeTotalPrice() throws MissingInformationException, InvalidArgumentException {
        if (distance == null) {
            throw new MissingInformationException("Distance is missing for train booking " + bookingId);
        }
        if (distance < GlobalConfig.MIN_TRAIN_DISTANCE || distance > GlobalConfig.MAX_TRAIN_DISTANCE) {
            throw new InvalidArgumentException("Distance must be between " + GlobalConfig.MIN_TRAIN_DISTANCE + " and " + GlobalConfig.MAX_TRAIN_DISTANCE + " km.");
        }
        
        if (seatClass == TrainSeatClass.FIRST_CLASS) {
            return distance * GlobalConfig.TRAIN_FIRST_CLASS_RATE;
        } else {
            return distance * GlobalConfig.TRAIN_STANDARD_RATE;
        }
    }
}