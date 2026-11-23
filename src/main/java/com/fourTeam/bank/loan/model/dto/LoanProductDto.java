package com.fourTeam.bank.loan.model.dto;

public class LoanProductDto {

    private String productCode;     // 대출상품 코드
    private String productName;     // 대출상품명
    private double rate;            // 금리
    private long maxLimit;          // 한도
    private int minMonths;          // 최소 대출기간
    private int maxMonths;          // 최대 대출기간
    private String repaymentType;   // 상환방식-만기일시
    private int requiredGrade;      // 신용등급

    // 기본생성자
    public LoanProductDto() {
    }

    // 생성자
    public LoanProductDto(String productCode, String productName, double rate, long maxLimit, int minMonths, int maxMonths, String repaymentType, int requiredGrade) {
        this.productCode = productCode;
        this.productName = productName;
        this.rate = rate;
        this.maxLimit = maxLimit;
        this.minMonths = minMonths;
        this.maxMonths = maxMonths;
        this.repaymentType = repaymentType;
        this.requiredGrade = requiredGrade;
    }



    @Override
    public String toString() {
        return "LoanProductDto{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", rate=" + rate +
                ", maxLimit=" + maxLimit +
                ", minMonths=" + minMonths +
                ", maxMonths=" + maxMonths +
                ", repaymentType='" + repaymentType + '\'' +
                ", requiredGrade=" + requiredGrade +
                '}';
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public double getRate() {
        return rate;
    }

    public long getMaxLimit() {
        return maxLimit;
    }

    public int getMinMonths() {
        return minMonths;
    }

    public int getMaxMonths() {
        return maxMonths;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public int getRequiredGrade() {
        return requiredGrade;
    }
}
