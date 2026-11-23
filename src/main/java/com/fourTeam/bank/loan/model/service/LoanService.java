package com.fourTeam.bank.loan.model.service;

import com.fourTeam.bank.loan.dao.LoanDao;
import com.fourTeam.bank.loan.model.dto.LoanDto;
import com.fourTeam.bank.loan.model.dto.LoanProductDto;

import java.util.List;

// Service에서는 "대출상품을 빌려줘" 했을때 정말 빌려줘도 되는지, 기준에 부합하는지 판단하는 것
// 일의 순서도 service가 정함
// 대출 실행: 검사 → 대출 만들기 → 통장에 입금 → 저장
//     상환:  검사 → 통장에서 출금 → 상환 처리
public class LoanService {
    private LoanDao loanDao = new LoanDao();

    // 상품 목록
    public List<LoanProductDto> getProducts() {
        return loanDao.findAllProducts();
    }

    // 내 대출 목록
    public List<LoanDto> getMyLoans(String memberId) {
        return loanDao.findByMemberId(memberId);
    }

    // 상품찾기
    private LoanProductDto findProduct(String productCode) {
        if (loanDao.findProductByCode(productCode) == null) {
            throw new IllegalArgumentException("없는 상품입니다.");
        } else {
            return loanDao.findProductByCode(productCode);
        }
    }

    // 신용등급 정하기
    private int getCreditGrade(String memberId) {
        return 2;
        // 모든 회원 같은 등급
        // 임의로 부여, 수정 필요
    }

    // 한도
    public long getLimit(String memberId, String productCode) {
        LoanProductDto lpd = findProduct(productCode);
        int grade = getCreditGrade(memberId);

        if (grade > lpd.getRequiredGrade()) {
            throw new IllegalArgumentException("가입 조건이 맞지 않습니다.");  // 대출불가
        }
        if (grade <= 3) {
            return lpd.getMaxLimit();   // 최대 한도까지 대출가능
        }
        return lpd.getMaxLimit() / 2;     // 최대한도/2 금액까지 대출가능
    }

    // 금리
    public double getRate(String memberId, String productCode) {
        LoanProductDto product = findProduct(productCode);
        int grade = getCreditGrade(memberId);

        if (grade <= 3) {
            return product.getRate();   // 기본 금리
        }
        return product.getRate() + 0.02;  //기본금리 +2%
    }

    // 대출 신청
    public LoanDto apply(String memberId, String productCode, long amount, int months, String accountNo) {
        LoanProductDto lpd = findProduct(productCode);

        // 1. 금액 검사
        long limit = getLimit(memberId, productCode);
        if (amount <= 0 || amount > limit) {
            throw new IllegalArgumentException("대출금액이 한도를 벗어났습니다.");
        }

        // 2. 기간 검사
        if (months < lpd.getMinMonths() || months > lpd.getMaxMonths()) {
            throw new IllegalArgumentException("대출기간이 맞지 않습니다.");
        }

        //3. 금리정하기
        double rate = getRate(memberId, productCode);

        //4. 대출 만들기
        LoanDto loanDto = new LoanDto(loanDao.nextLoanNo(),
                memberId, productCode, accountNo, amount, rate, months, lpd.getRepaymentType());

        //5. 통장에 입금

        //6. 저장
        loanDao.save(loanDto);
        return loanDto;

    }

    // 이번 달 상환
    public long repay(String memberId, String loanNo) {
        // 대출번호로 찾기
        LoanDto ld = loanDao.findByLoanNo(loanNo);

        if (ld == null) {
            throw new IllegalArgumentException("없는 대출입니다.");
        } else if (!ld.getMemberId().equals(memberId)) {
            throw new IllegalArgumentException("본인 대출이 아닙니다.");
        } else if (ld.getRemainingPrincipal() <= 0) {
            throw new IllegalArgumentException("이미 상환이 완료된 대출입니다.");
        }

        long payment = ld.getMonthlyPayment();

        // 출금 - 통장에서 payment출금

        ld.payMonth();
        return payment;

    }


}
