package com.fourTeam.bank.loan.controller;

import com.fourTeam.bank.loan.model.dto.LoanDto;
import com.fourTeam.bank.loan.model.service.LoanService;
import com.fourTeam.bank.loan.view.LoanMenu;

public class LoanController {

    private LoanService ls = new LoanService();
    private LoanMenu lm = new LoanMenu();

    // 메뉴를 반복함, 번호에 따라 해당 메소드 호출함, 9를 받으면 끝
    public void run(String memberId) {
        while (true) {
            String num = lm.showMenu();
            switch (num) {
                case "1":
                    showProducts();
                    break;
                case "2":
                    applyLoan(memberId);
                    break;
                case "3":
                    showMyLoans(memberId);
                    break;
                case "4":
                    repay(memberId);
                    break;
                case "9":
                    return;
                default:
                    lm.printError("잘못된 메뉴입니다.");
            }
        }

    }

    // 1번 - 상품목록
    private void showProducts() {
        lm.printProducts(ls.getProducts());
    }

    // 2번 - 대출신청
    private void applyLoan(String memberId) {
        try {
            // 1. 상품 보여주고 상품코드 받기
            lm.printProducts(ls.getProducts());
            String productCode = lm.input("상품코드 : ");

            // 2. 나머지 입력받기
            String amount = lm.input("금액 : ");
            String month = lm.input("기간 : ");
            String accountNo = lm.input("계좌번호 : ");

            // 3. 숫자로 바꾸기
            long amount2 = Long.parseLong(amount);
            int month2 = Integer.parseInt(month);

            // 4. service에게 대출 신청 맡기기
            LoanDto loan = ls.apply(memberId, productCode, amount2, month2, accountNo);

            // 5. 결과 보여주기
            lm.printMessage("대출이 완료되었습니다. 대출번호: " + loan.getLoanNo());

        } catch (NumberFormatException e) {
            // 숫자가 아닌 값을 입력했을 때
            lm.printError("숫자로 입력해 주세요.");
        } catch (IllegalArgumentException e) {
            // service가 던진 오류 (한도 초과, 없는 상품 등)
            lm.printError(e.getMessage());
        }
    }

    // 3번 - 내 대출 조회
    private void showMyLoans(String memberId) {
        lm.printLoans(ls.getMyLoans(memberId));
    }

    // 4번 - 이번 달 상환
    private void repay(String memberId) {
        try {
            // 1. 내 대출 목록 보여주기
            lm.printLoans(ls.getMyLoans(memberId));

            // 2. 상환할 대출번호 입력받기
            String loanNo = lm.input("상환할 대출번호 : ");

            // 3. service에게 상환 맡기기 (낸 금액을 돌려받음)
            long payment = ls.repay(memberId,loanNo);

            // 4. 결과 보여주기
            lm.printMessage(String.format("%,d원 상환이 완료되었습니다.", payment));

        } catch (IllegalArgumentException e) {
            lm.printError(e.getMessage());
        }
    }
}
