package edu.psu.se411.lab07_polymorphism.model;

import edu.psu.se411.lab07_polymorphism.config.GlobalConfig;
import edu.psu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public class CarRentalBooking extends Booking {
    private double dailyRate;
    private Integer rentalDays;

    public CarRentalBooking(String bookingId, String customerName, String travelDate, String destinationCity, double dailyRate) {
        super(bookingId, customerName, travelDate, destinationCity);
        this.dailyRate = dailyRate;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    @Override
    public double computeTotalPrice() throws MissingInformationException, InvalidArgumentException {
        if (rentalDays == null) {
            throw new MissingInformationException("Rental days are missing for car rental booking " + bookingId);
        }
        if (rentalDays < GlobalConfig.MIN_RENTAL_DAYS || rentalDays > GlobalConfig.MAX_RENTAL_DAYS) {
            throw new InvalidArgumentException("Rental days must be between " + GlobalConfig.MIN_RENTAL_DAYS + " and " + GlobalConfig.MAX_RENTAL_DAYS + ".");
        }
        return dailyRate * rentalDays;
    }
}