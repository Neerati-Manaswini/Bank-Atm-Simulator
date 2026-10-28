package com.manaswini.bank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account {
    private final String accountNumber;
    private final String holderName;
    private final String pin;
    private double balance;
    private final List<Transaction> history = new ArrayList<>();

    public Account(String accountNumber, String holderName, String pin, double openingBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.pin = pin;
        this.balance = openingBalance;
        record("OPENING", openingBalance);
    }

    public boolean checkPin(String input) {
        return pin.equals(input);
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than zero.");
        balance += amount;
        record("DEPOSIT", amount);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be greater than zero.");
        if (amount > balance) {
            throw new InsufficientFundsException(
                    String.format("Insufficient balance. Available: %.2f", balance));
        }
        balance -= amount;
        record("WITHDRAW", amount);
    }

    public void transferTo(Account target, double amount) throws InsufficientFundsException {
        if (target == this) throw new IllegalArgumentException("Cannot transfer to the same account.");
        withdraw(amount);
        history.remove(history.size() - 1);          // replace the WITHDRAW entry with a TRANSFER entry
        record("TRANSFER OUT", amount);
        target.balance += amount;
        target.record("TRANSFER IN", amount);
    }

    private void record(String type, double amount) {
        history.add(new Transaction(type, amount, balance, LocalDateTime.now()));
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }
    public List<Transaction> getHistory() { return Collections.unmodifiableList(history); }
}
