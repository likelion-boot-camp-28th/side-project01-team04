package com.fourTeam.bank.basic.dao;

import java.util.ArrayList;
import java.util.List;

public class CheckingAccount {

    //입출금 통장

    private final String owner;
    private final String accountNumber;
    private int balance;
    private final List<TransactionDao> transactions = new ArrayList<>();

    public CheckingAccount(String owner, String accountNumber, int balance) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(int amount) {
        if (amount <= 0) {
            System.out.println("유효하지 않은 입금 금액 입니다.");
            return;
        }
        balance = balance + amount;
        transactions.add(new TransactionDao("입금", amount, balance));
        System.out.println("계좌 잔액 : " + balance);
    }

    public void withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("유효하지 않은 출금액 입니다.");
            return;
        }
        if (balance < amount) {
            System.out.println("계좌 잔액이 부족합니다.");
            return;
        }
        balance = balance - amount;
        transactions.add(new TransactionDao("출금", amount, balance));
        System.out.println("계좌 잔액 : " + balance);
    }

    public void transfer(CheckingAccount target, int amount) {
        if (amount <= 0) {
            System.out.println("유효하지 않은 금액 입니다.");
            return;
        }
        if (balance < amount) {
            System.out.println("계좌 잔액이 부족합니다.");
            return;
        }
        withdraw(amount);
        target.deposit(amount);
    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public List<TransactionDao> getTransactions() {
        return new ArrayList<>(transactions);
    }
}

