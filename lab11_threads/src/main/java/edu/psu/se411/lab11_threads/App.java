package edu.psu.se411.lab11_threads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import edu.psu.se411.lab11_threads.model.CryptoAccount;
import edu.psu.se411.lab11_threads.model.Transaction;
import java.util.ArrayList;
import java.util.List;

public class App {

	static Logger logger = LoggerFactory.getLogger(App.class);

	public static Integer nbAccounts = 10;
	public static Double initialBalance = 1000.0; 
	public static Integer nbTransactions = 10000; 

	public static void main(String[] args) {
		logger.info("Application is starting...");
		
		CryptoAccount[] accounts = new CryptoAccount[nbAccounts];
		for (Integer i = 0; i < nbAccounts; i++) {
			accounts[i] = new CryptoAccount("acc_" + i.toString(), initialBalance);
		}
		
		List<Thread> threads = new ArrayList<>();

		for (int i = 0; i < nbTransactions; i++) {
			Thread t = new Thread(() -> {
				CryptoAccount randomA = accounts[(int)(Math.random() * nbAccounts)];
				CryptoAccount randomB = accounts[(int)(Math.random() * nbAccounts)];
				Double randomAmount = (double)(Math.random() * 10 + 1);
				
				Transaction transaction = new Transaction("tx_" + Math.random(), randomA, randomB, randomAmount);
				transaction.execute();
			});
			threads.add(t);
			t.start();
		}

		for (Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				logger.error("Thread interrupted", e);
			}
		}

		double totalBalance = 0.0;
		for (CryptoAccount acc : accounts) {
			totalBalance += acc.getBalance();
		}

		logger.info("Expected system balance: {}", nbAccounts * initialBalance);
		logger.info("Actual system balance: {}", totalBalance);
		logger.info("Application is closing...");
	}
}