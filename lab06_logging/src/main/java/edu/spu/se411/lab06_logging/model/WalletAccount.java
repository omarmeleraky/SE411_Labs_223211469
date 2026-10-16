package edu.spu.se411.lab06_logging.model;

import edu.spu.se411.lab06_logging.exceptions.InsufficientFundsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WalletAccount {
    private static final Logger logger = LoggerFactory.getLogger(WalletAccount.class);
    private static final Logger auditLogger = LoggerFactory.getLogger("audit.transactions");

    private final String accountId; // optional identifier used for log context
    private double balance;

    public WalletAccount(double balance) {
        this(null, balance);
    }

    public WalletAccount(String accountId, double balance) {
        this.accountId = accountId;
        setBalance(balance);
        logger.debug("WalletAccount created for accountId={}", accountIdOrUnknown());
        // Audit creation with the initial balance (sensitive) to a dedicated audit logger
        auditLogger.info("account.create accountId={} initialBalance={}", accountIdOrUnknown(), balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        logger.debug("withdraw called: amount={} accountId={}", amount, accountIdOrUnknown());

        if (amount < 0) {
            IllegalArgumentException ex = new IllegalArgumentException("Cannot withdraw negative number: " + amount);
            // Log the invalid request with context and include the exception so callers/ops see stack if needed
            logger.warn("Invalid withdrawal request: negative amount={} accountId={}", amount, accountIdOrUnknown(), ex);
            throw ex;
        }

        if (amount > balance) {
            InsufficientFundsException ex = new InsufficientFundsException("Insufficient funds");
            // Record an audit entry (sensitive details) and a warning for operational awareness
            auditLogger.info("account.withdraw attempt=insufficient accountId={} requested={} balance={}", accountIdOrUnknown(), amount, balance);
            logger.warn("Insufficient funds for withdrawal: requested={} accountId={}", amount, accountIdOrUnknown(), ex);
            throw ex;
        }

        // successful withdrawal: update balance and write an audit record with full details
        balance -= amount;
        auditLogger.info("account.withdraw success accountId={} amount={} remainingBalance={}", accountIdOrUnknown(), amount, balance);
        logger.info("Withdrawal successful for accountId={} amount={}", accountIdOrUnknown(), amount);
    }
    
    public void deposit(double amount) throws IllegalArgumentException {
        logger.debug("deposit called: amount={} accountId={}", amount, accountIdOrUnknown());

        if (amount < 0) {
            IllegalArgumentException ex = new IllegalArgumentException("Cannot deposit negative number: " + amount);
            logger.warn("Invalid deposit request: negative amount={} accountId={}", amount, accountIdOrUnknown(), ex);
            throw ex;
        }

        balance += amount;
        auditLogger.info("account.deposit accountId={} amount={} newBalance={}", accountIdOrUnknown(), amount, balance);
        logger.info("Deposit successful for accountId={} amount={}", accountIdOrUnknown(), amount);
    }

    public void setBalance(double balance) {
        if (balance < 0) {
            IllegalArgumentException ex = new IllegalArgumentException("Balance cannot be negative: " + balance);
            // This indicates a potentially serious bug or corrupted input; record error and throw
            logger.error("Attempted to set a negative balance: {} accountId={}", balance, accountIdOrUnknown(), ex);
            throw ex;
        }
        this.balance = balance;
        logger.debug("Balance set for accountId={}", accountIdOrUnknown());
        auditLogger.debug("account.balance set accountId={} balance={}", accountIdOrUnknown(), balance);
    }

    private String accountIdOrUnknown() {
        return accountId == null ? "unknown" : accountId;
    }
}