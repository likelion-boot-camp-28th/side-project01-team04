package com.fourTeam.bank.loan.model.dto;

// 회원 한명이 받은 대출 하나
public class LoanDto {
    private String loanNo;           // 대출번호
    private String memberId;         // 회원매핑 키
    private String productCode;      // 대출상품 코드
    private String accountNo;        // 상환할 입출금 계좌
    private long principal;          // 처음 빌린 금액
    private double rate;             // 금리
    private int months;              // 총 기간
    private String repaymentType;    // 상환방식
    private long remainingPrincipal; // 남은 원금
    private int paidRounds;          // 납입한 횟수
    private boolean paidOff;         // 다 갚았는지

    public LoanDto() {
    }

    public LoanDto(String loanNo, String memberId, String productCode, String accountNo, long principal, double rate, int months, String repaymentType) {
        this.loanNo = loanNo;
        this.memberId = memberId;
        this.productCode = productCode;
        this.accountNo = accountNo;
        this.principal = principal;
        this.rate = rate;
        this.months = months;
        this.repaymentType = repaymentType;
        this.remainingPrincipal = principal;
    }

    // 이번달 이자
    public long getInterest() {
        return (long) (remainingPrincipal * rate / 12);
    }

    //이번 달 갚을 원금 - 상환방식 별 갚을 금액 다름
    private long getPrincipalPart() {
        int remainMonth = months - paidRounds;   // 남은 개월수
        if (remainMonth == 1) {    // 남은 개월수가 1이면 남은 금액 전부 상환
            return remainingPrincipal;
        } else {
            switch (repaymentType) {
                case "만기일시":    // 매달 이자만 내고 원금은 마지막달 한번에 갚음
                    return 0;
                case "원금균등":   // 처음빌린돈을 기간으로 똑같이 나눠 매달 같은 원금 갚음, 이번 달 원금 = 처음 빌린 돈 ÷ 총 개월 수
                    return principal / months;
                case "원리금균등":    // 월 납입액 = P × r × (1+r)ⁿ ÷ ((1+r)ⁿ − 1) / 이번 달 원금 = 월 납입액 − 이자
                    double r = rate / 12;                                   // 월 이율
                    double pow = Math.pow(1 + r, remainMonth);              // (1+r)^n
                    long monthly = (long) (remainingPrincipal * r * pow / (pow - 1));  // 월 납입액
                    return monthly - getInterest();                         // 이번 달 원금
                default:
                    throw new IllegalStateException("알 수 없는 상환 방식: " + repaymentType);
            }
        }

    }

    // 이번 달 낼 돈 = 원금 + 이자
    public long getMonthlyPayment() {
        return getPrincipalPart() + getInterest();
    }

    // 남은 원금 감소, 회차 +1, 다 갚으면 paidOff = true
    public void payMonth() {
        long principalPart = getPrincipalPart();  // 이번달 원금
        remainingPrincipal -= principalPart;      // 남은 원금 - 이번달 원금
        paidRounds++;   //회차+1
        if (remainingPrincipal <= 0) {
            paidOff = true;
        }
    }

    public String getLoanNo() {
        return loanNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public long getPrincipal() {
        return principal;
    }

    public double getRate() {
        return rate;
    }

    public int getMonths() {
        return months;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public long getRemainingPrincipal() {
        return remainingPrincipal;
    }

    public int getPaidRounds() {
        return paidRounds;
    }

    public boolean isPaidOff() {
        return paidOff;
    }

    @Override
    public String toString() {
        return "LoanDto{" +
                "loanNo='" + loanNo + '\'' +
                ", memberId='" + memberId + '\'' +
                ", productCode='" + productCode + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", principal=" + principal +
                ", rate=" + rate +
                ", months=" + months +
                ", repaymentType='" + repaymentType + '\'' +
                ", remainingPrincipal=" + remainingPrincipal +
                ", paidRounds=" + paidRounds +
                ", paidOff=" + paidOff +
                '}';
    }
}
