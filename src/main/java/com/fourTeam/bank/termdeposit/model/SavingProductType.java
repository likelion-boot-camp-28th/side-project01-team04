package com.fourTeam.bank.termdeposit.model;

public enum SavingProductType {

    /* 6개월, 12개월 정기예금 상품 값 */
    SIX_MONTH("6개월", 6, 0.03),
    TWELVE_MONTH("12개월", 12, 0.035);

    /* 현재 클래스 내에서만 사용 가능하게 private 사용, final은 최초로 담은 값이 바뀌지 않게 하는 역할*/
    private final String name;
    private final int months;
    private final double rate;

    /* 생성자 */
    SavingProductType(String name, int months, double rate) {
        this.name = name;
        this.months = months;
        this.rate = rate;
    }

    /* 필드가 private라서 get으로만 꺼낼 수 있기에 getter 사용 */
    public String getName() {
        return name;
    }

    public int getMonths() {
        return months;
    }

    public double getRate() {
        return rate;
    }
}
