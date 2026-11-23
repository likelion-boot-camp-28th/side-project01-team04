package com.fourTeam.bank.basic.transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//거래 내역

public class Transaction {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final LocalDateTime dateTime;
    private final String trade;
    private final int amount;
    private final int afterBalance;


    public Transaction(String trade, int amount, int afterBalance) {
        this.trade = trade;
        this.amount = amount;
        this.afterBalance = afterBalance;
        this.dateTime = LocalDateTime.now();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTrade() {
        return trade;
    }

    public int getAmount() {
        return amount;
    }

    public int getAfterBalance() {
        return afterBalance;
    }
    @Override
    public String toString() {
        return "[" + dateTime.format(FORMATTER) + "] "
                + trade + " | "
                + amount + "원 | "
                + "잔액 " + afterBalance + "원";
    }
}
