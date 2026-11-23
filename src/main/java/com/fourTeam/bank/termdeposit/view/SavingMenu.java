package com.fourTeam.bank.termdeposit.view;

import com.fourTeam.bank.termdeposit.model.DepositAccount;
import com.fourTeam.bank.termdeposit.model.SavingProductType;
import com.fourTeam.bank.termdeposit.service.SavingService;

import java.util.ArrayList;
import java.util.Scanner;

public class SavingMenu {


    private SavingService ss = new SavingService(); // 가입, 조회를 처리하는 서비스
    Scanner sc = new Scanner(System.in);

    // 예적금 메뉴 (로그인한 아이디를 받아서 사용)
    public void menu(String id) {
        while (true) {
            System.out.println("===== 정기예금 메뉴 =====");
            System.out.println("1. 정기예금 가입");
            System.out.println("2. 내 정기예금 조회");
            System.out.println("0. 뒤로가기");
            System.out.print("메뉴 번호 선택 : ");

            int no = sc.nextInt();

            switch (no) {
                case 1:
                    join(id);
                    break;
                case 2:
                    showMyAccounts(id);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    // 가입하기
    private void join(String id) {
        System.out.print("가입 금액 : ");
        long amount = sc.nextLong();

        System.out.println("1. 6개월 / 2. 12개월");
        System.out.print("상품 선택 : ");
        int select = sc.nextInt();

        SavingProductType type;
        if (select == 1) {
            type = SavingProductType.SIX_MONTH;
        } else if (select == 2) {
            type = SavingProductType.TWELVE_MONTH;
        } else {
            System.out.println("잘못 입력하셨습니다.");
            return;
        }

        ss.join(id, amount, type); // 서비스에 가입 요청
        System.out.println("가입이 완료되었습니다.");
    }

    // 내 계좌 조회
    private void showMyAccounts(String id) {
        ArrayList<DepositAccount> list = ss.getMyAccounts(id); // 내 계좌 목록 받기

        if (list.isEmpty()) {
            System.out.println("가입한 정기예금이 없습니다.");
        } else {
            for (DepositAccount account : list) {
                System.out.println("상품 : " + account.getType().getName()
                        + " / 가입 금액 : " + account.getAmount()
                        + " / 만기 이자 : " + account.interestCalculation());
            }
        }
    }
}
