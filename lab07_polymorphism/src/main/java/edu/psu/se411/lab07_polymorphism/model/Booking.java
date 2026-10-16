package edu.psu.se411.lab07_polymorphism.model;

import edu.psu.se411.lab07_polymorphism.exceptions.InvalidArgumentException;
import edu.psu.se411.lab07_polymorphism.exceptions.MissingInformationException;

public abstract class Booking {
    protected String bookingId;
    protected String customerName;
    protected String travelDate;
    protected String destinationCity;

    public Booking(String bookingId, String customerName, String travelDate, String destinationCity) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.travelDate = travelDate;
        this.destinationCity = destinationCity;
    }

    // Polymorphic method signature
    public abstract double computeTotalPrice() throws MissingInformationException, InvalidArgumentException;

    public String getBookingId() { 
        return bookingId; 
    }
}