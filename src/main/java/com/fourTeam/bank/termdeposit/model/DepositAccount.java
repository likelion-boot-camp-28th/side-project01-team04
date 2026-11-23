package com.fourTeam.bank.termdeposit.model;

public class DepositAccount {

    private String id;
    private long amount; // 금액인데 int가 담을 수 있는 정수크기가 21억으로 작기에 long을 사용
    private SavingProductType type;


    public DepositAccount(String id, long amount, SavingProductType type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public SavingProductType getType() {
        return type;
    }

    /* 이자계산기 */
    public double interestCalculation() {
        double rate = type.getRate();
        int months = type.getMonths();
        double interest = amount * rate * months / 12;
        return interest;
    }
}
