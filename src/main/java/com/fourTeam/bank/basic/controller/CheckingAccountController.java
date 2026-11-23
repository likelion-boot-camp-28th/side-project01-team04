package com.fourTeam.bank.basic.controller;

import com.fourTeam.bank.basic.dao.CheckingAccount;
import com.fourTeam.bank.basic.dao.TransactionDao;
import com.fourTeam.bank.member.controller.MemberContoller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckingAccountController {

    public class Application {

        static Scanner sc = new Scanner(System.in);
        static MemberContoller mc = new MemberContoller();

        // 입출금 계좌 목록 (메뉴를 나갔다 들어와도 잔액 유지)
        static List<CheckingAccount> accounts = new ArrayList<>();

        static {
            accounts.add(new CheckingAccount("김민준", "110-110", 6000));
            accounts.add(new CheckingAccount("박성주", "110-111", 7000));
            accounts.add(new CheckingAccount("이승진", "110-112", 8000));
        }

        // 입출금 메뉴 화면
        public static void printCheckingMenu() {
            while (true) {

                System.out.println("1. 입금");
                System.out.println("2. 출금");
                System.out.println("3. 이체");
                System.out.println("4. 잔액 조회");
                System.out.println("5. 거래 내역 조회");
                System.out.println("0. 종료");
                System.out.print("메뉴 선택 : ");

                int menu = sc.nextInt();
                if (menu == 0) {
                    System.out.println("입출금 메뉴 종료");
                    break;

                }
                if (menu == 1) {
                    System.out.print("입금할 계좌번호 : ");
                    String accountNumber = sc.next();
                    for (CheckingAccount account : accounts) {
                        if (account.getAccountNumber().equals(accountNumber)) {
                            System.out.println("찾았습니다. : " + account.getOwner());
                            System.out.print("입금 할 금액 : ");
                            int amount = sc.nextInt();
                            account.deposit(amount);
                        }
                    }

                } else if (menu == 2) {
                    System.out.println("출금 메뉴 입니다");
                    System.out.print("출금할 계좌번호 : ");
                    String accountNumber = sc.next();
                    for (CheckingAccount account : accounts) {
                        if (account.getAccountNumber().equals(accountNumber)) {
                            System.out.println("찾았습니다. : " + account.getOwner());
                            System.out.print("출금 할 금액 : ");
                            int amount = sc.nextInt();
                            account.withdraw(amount);
                        }
                    }

                } else if (menu == 3) {
                    System.out.print("이체 할 계좌번호 : ");
                    String personalAccountNumber = sc.next();

                    for (CheckingAccount account : accounts) {

                        if (account.getAccountNumber().equals(personalAccountNumber)) {
                            System.out.println("찾았습니다. : " + account.getOwner());

                            System.out.println("받는 계좌 번호");
                            String targetNumber = sc.next();

                            for (CheckingAccount target : accounts) {

                                if (target.getAccountNumber().equals(targetNumber)) {
                                    System.out.println("받는 분 : " + target.getOwner());

                                    System.out.print("이체 할 금액 : ");
                                    int amount = sc.nextInt();

                                    account.transfer(target, amount);
                                }
                            }
                        }
                    }

                } else if (menu == 4) {
                    System.out.print("조회할 계좌번호 : ");
                    String accountNumber = sc.next();
                    for (CheckingAccount account : accounts) {
                        if (account.getAccountNumber().equals(accountNumber)) {
                            System.out.println("찾았습니다. : " + account.getOwner());
                            System.out.println(account.getOwner() + "님의 잔액" + account.getBalance() + "원");
                        }
                    }

                } else if (menu == 5) {
                    System.out.println("거래 내역 조회 입니다.");
                    String accountNumber = sc.next();
                    for (CheckingAccount account : accounts) {
                        if (account.getAccountNumber().equals(accountNumber)) {
                            System.out.println("찾았습니다. : " + account.getOwner());
                            for (TransactionDao t : account.getTransactions()) {
                                System.out.println(t);
                            }
                        }
                    }

                } else {
                    System.out.println("잘못된 메뉴 입니다.");
                }
            }
        }

    }


}
