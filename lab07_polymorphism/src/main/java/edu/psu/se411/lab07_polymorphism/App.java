package edu.psu.se411.lab07_polymorphism;

import edu.psu.se411.lab07_polymorphism.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Application is starting...");

        FlightBooking flight = new FlightBooking("FL-101", "John Doe", "2026-11-01", "Paris", 300.0);
        flight.setLuggageWeight(25.0); // Valid

        TrainBooking train = new TrainBooking("TR-202", "Jane Smith", "2026-11-02", "Berlin", TrainSeatClass.FIRST_CLASS);
        // Distance missing to trigger MissingInformationException

        CarRentalBooking car = new CarRentalBooking("CR-303", "Alice Jones", "2026-11-03", "Rome", 45.0);
        car.setRentalDays(35); // Invalid to trigger InvalidArgumentException

        // Polymorphism array calling computeTotalPrice() identically
        Booking[] bookings = { flight, train, car };

        for (Booking booking : bookings) {
            try {
                double price = booking.computeTotalPrice();
                logger.info("Total price for booking {} is ${}", booking.getBookingId(), price);
            } catch (Exception e) {
                logger.error("Exception raised for booking {}: {}", booking.getBookingId(), e.getMessage(), e);
            }
        }

        logger.info("Application is stopping...");
    }
}