package edu.psu.se411.lab11_threads.model;

import edu.psu.se411.lab11_threads.exceptions.InsufficientBalanceException;

public class Transaction {
	private String id;
	private CryptoAccount sender;
	private CryptoAccount receiver;
	private Double amount;
	
	public Transaction(String id, CryptoAccount sender, CryptoAccount receiver, Double amount) {
		super();
		if(id == null || sender == null || receiver == null || amount < 0) throw new IllegalArgumentException();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.amount = amount;
	}
	
	public void execute() {
		if (sender.getId().equals(receiver.getId())) {
			return; 
		}
		processTransaction(sender, receiver, amount);
	}
	
	private static synchronized void processTransaction(CryptoAccount sender, CryptoAccount receiver, Double amount) {
		try {
			sender.retrieveFromBalance(amount);
			receiver.addToBalance(amount);
		} catch (InsufficientBalanceException e) {
			System.err.println("Transaction failed: " + e.getMessage());
		}
	}
	
	public CryptoAccount getSender() {
		return sender;
	}
	public void setSender(CryptoAccount sender) {
		this.sender = sender;
	}
	public CryptoAccount getReceiver() {
		return receiver;
	}
	public void setReceiver(CryptoAccount receiver) {
		this.receiver = receiver;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getId() {
		return id;
	}
}
