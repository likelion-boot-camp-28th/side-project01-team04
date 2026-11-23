package com.fourTeam.bank.main;

import com.fourTeam.bank.basic.controller.CheckingAccountController;
import com.fourTeam.bank.loan.controller.LoanController;
import com.fourTeam.bank.member.controller.MemberContoller;

import java.util.Scanner;

import static com.fourTeam.bank.basic.controller.CheckingAccountController.Application.printCheckingMenu;

public class Application {

    static Scanner sc = new Scanner(System.in);
    static MemberContoller mc = new MemberContoller();
    static LoanController lc = new LoanController();
    static CheckingAccountController cc = new CheckingAccountController();


    public static void main(String[] args) {
        String loginId = printLoginMenu();

        // 로그인 아이디가 들어왔다면? (로그인 성공한 것 - 타메뉴 노출)
        if (!(loginId.equals(""))) {
            pringBankMenu(loginId);
        }
    }


    public static String printLoginMenu() {
        boolean isWorking = true;
        String result = "";

        while (isWorking) {
            System.out.println("""
                    1. 로그인
                    2. 회원가입
                    9. 나가기
                    """);
            int userInput = sc.nextInt();
            switch (userInput) {
                case 1:
                    String value = mc.login();
                    if (!(value.equals(""))) {
                        return value;
                    }
                    break;
                case 2:
                    mc.register();
                    break;
                case 9:
                    isWorking = false;
                    break;
                default:
                    System.out.println("잘못된 값을 입력하셨습니다.");
                    break;
            }
        }

        return result;
    }

    public static void pringBankMenu(String loginId) {
        boolean isWorking = true;

        while (isWorking) {
            System.out.println("""
                    1. 입출금 바로가기
                    2. 예적금 바로가기
                    3. 대출 바로가기
                    9. 나가기
                    """);
            int userInput = sc.nextInt();
            switch (userInput) {
                case 1:
                    printCheckingMenu();
                    break;
                case 2:
                    System.out.println("예적금 메뉴는 준비 중입니다.");
                    break;
                case 3:
                    lc.run(loginId);
                    break;
                case 9:
                    isWorking = false;
                    break;
                default:
                    System.out.println("잘못된 값을 입력하셨습니다.");
                    break;
            }
        }
    }
}


