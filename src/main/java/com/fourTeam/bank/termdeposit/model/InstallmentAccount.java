package com.fourTeam.bank.termdeposit.model;

public class InstallmentAccount {

    private String id;
    private long monthlyAmount;        // 매달 넣는 금액
    private SavingProductType type;    // 6개월 / 12개월

    public InstallmentAccount(String id, long monthlyAmount, SavingProductType type) {
        this.id = id;
        this.monthlyAmount = monthlyAmount;
        this.type = type;
    }

    public String getId() {
        return id;
    }
    public long getMonthlyAmount() {
        return monthlyAmount;
    }
    public SavingProductType getType() {
        return type;
    }

    // 만기에 내가 넣은 원금 합계
    public long totalAmount() {
        return monthlyAmount * type.getMonths();
    }

    // 만기 이자
    public double interestCalculation() {
        int n = type.getMonths();
        int sum = n * (n + 1) / 2;   // 6개월이면 21, 12개월이면 78
        double interest = monthlyAmount * sum * type.getRate() / 12;
        return interest;
    }
}