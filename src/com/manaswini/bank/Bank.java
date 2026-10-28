package com.manaswini.bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, Account> accounts = new HashMap<>();

    public Bank() {
        // Demo accounts so you can log in straight away
        addAccount(new Account("1001", "Asha Rao", "1234", 5000));
        addAccount(new Account("1002", "Ravi Kumar", "4321", 2500));
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account find(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public Account login(String accountNumber, String pin) {
        Account account = find(accountNumber);
        return (account != null && account.checkPin(pin)) ? account : null;
    }
}
