package edu.psu.se411;

import edu.psu.se411.exceptions.InvalidAgeException;
import edu.psu.se411.exceptions.InsufficientFundsException;
import edu.psu.se411.model.Wallet;

public class App {

    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative, but was: " + age);
        }
        if (age < 18) {
            throw new InvalidAgeException("Age must be 18 or older, but was: " + age);
        }
        System.out.println("Age valid message.");
    }

    public static void main(String[] args) {
        try {
            validateAge(20);
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
            return; // Stop execution if age validation fails
        }

        Wallet wallet = new Wallet(100.0);

        try {
            wallet.withdraw(50.0);   
            System.out.println("Current balance: " + wallet.getBalance());
            
            wallet.withdraw(100.0);  // This will throw InsufficientFundsException
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Final balance: " + wallet.getBalance());
        }
    }
}