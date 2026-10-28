package com.manaswini.bank;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner in = new Scanner(System.in);

        System.out.println("=== Console ATM ===");
        System.out.println("Demo logins -> 1001 / 1234   and   1002 / 4321");

        Account user = null;
        for (int attempt = 1; attempt <= 3 && user == null; attempt++) {
            System.out.print("Account number: ");
            String number = in.nextLine().trim();
            System.out.print("PIN: ");
            String pin = in.nextLine().trim();
            user = bank.login(number, pin);
            if (user == null) System.out.println("Wrong account number or PIN (" + attempt + "/3).");
        }
        if (user == null) {
            System.out.println("Card blocked. Goodbye.");
            return;
        }

        System.out.println("Welcome, " + user.getHolderName() + "!");
        boolean running = true;
        while (running) {
            System.out.println("\n1. Balance  2. Deposit  3. Withdraw  4. Transfer  5. Statement  6. Exit");
            System.out.print("Choose: ");
            String choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> System.out.printf("Balance: %.2f%n", user.getBalance());
                    case "2" -> {
                        user.deposit(readAmount(in));
                        System.out.println("Deposited. New balance: " + String.format("%.2f", user.getBalance()));
                    }
                    case "3" -> {
                        user.withdraw(readAmount(in));
                        System.out.println("Please collect your cash. Balance: " + String.format("%.2f", user.getBalance()));
                    }
                    case "4" -> {
                        System.out.print("Target account number: ");
                        Account target = bank.find(in.nextLine().trim());
                        if (target == null) {
                            System.out.println("Target account not found.");
                            break;
                        }
                        user.transferTo(target, readAmount(in));
                        System.out.println("Transferred to " + target.getHolderName() + ".");
                    }
                    case "5" -> {
                        System.out.printf("%-20s %-12s %10s %12s%n", "Time", "Type", "Amount", "Balance");
                        user.getHistory().forEach(System.out::println);
                    }
                    case "6" -> running = false;
                    default -> System.out.println("Invalid option.");
                }
            } catch (InsufficientFundsException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Thank you for banking with us.");
    }

    private static double readAmount(Scanner in) {
        System.out.print("Amount: ");
        try {
            return Double.parseDouble(in.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number.");
        }
    }
}
