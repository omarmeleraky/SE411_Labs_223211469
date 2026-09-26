package edu.psu.se411.model;

import edu.psu.se411.exceptions.InsufficientFundsException;

public class Wallet {
    private double balance;

    public Wallet(double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative, but was: " + initialBalance);
        }
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive, but was: " + amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(
                "Cannot withdraw " + amount + ": wallet balance is only " + balance);
        }
        balance -= amount;
        System.out.println("Withdrew " + amount + " from bank account. Remaining balance: " + balance);
    }
}