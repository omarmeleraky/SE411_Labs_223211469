package edu.psu.se411.lab07_polymorphism.model;

import edu.psu.se411.lab07_polymorphism.config.GlobalConfig;
import edu.psu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public class FlightBooking extends Booking {
    private double basePrice;
    private Double luggageWeight;

    public FlightBooking(String bookingId, String customerName, String travelDate, String destinationCity, double basePrice) {
        super(bookingId, customerName, travelDate, destinationCity);
        this.basePrice = basePrice;
    }

    public void setLuggageWeight(Double luggageWeight) {
        this.luggageWeight = luggageWeight;
    }

    @Override
    public double computeTotalPrice() throws MissingInformationException, InvalidArgumentException {
        if (luggageWeight == null) {
            throw new MissingInformationException("Luggage weight is missing for flight booking " + bookingId);
        }
        if (luggageWeight < GlobalConfig.MIN_LUGGAGE_WEIGHT || luggageWeight > GlobalConfig.MAX_LUGGAGE_WEIGHT) {
            throw new InvalidArgumentException("Luggage weight must be between " + GlobalConfig.MIN_LUGGAGE_WEIGHT + " and " + GlobalConfig.MAX_LUGGAGE_WEIGHT + " kg.");
        }
        return basePrice + (luggageWeight * GlobalConfig.EXTRA_LUGGAGE_RATE);
    }
}