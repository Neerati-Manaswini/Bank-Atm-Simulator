package com.manaswini.bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Transaction(String type, double amount, double balanceAfter, LocalDateTime time) {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

    @Override
    public String toString() {
        return String.format("%-20s %-12s %10.2f %12.2f", time.format(FMT), type, amount, balanceAfter);
    }
}
