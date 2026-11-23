package com.fourTeam.bank.loan.view;

import com.fourTeam.bank.loan.model.dto.LoanDto;
import com.fourTeam.bank.loan.model.dto.LoanProductDto;

import java.util.List;
import java.util.Scanner;

public class LoanMenu {

    private Scanner sc = new Scanner(System.in);

    // 대출 메뉴를 출력하고 선택한 번호를 문자열로 반환
    public String showMenu() {
        System.out.println("===== 대출 메뉴 =====");
        System.out.println("1. 대출 상품 목록");
        System.out.println("2. 대출 신청");
        System.out.println("3. 내 대출 조회");
        System.out.println("4. 이번 달 상환");
        System.out.println("9. 이전 메뉴");
        System.out.print("선택 : ");
        String n = sc.nextLine();

        return n;
    }

    // 질문을 출력하고 입력값을 반환, 모든 입력은 이것 하나로 함

    /**
     * 이 메소드의 역할 _ 예시
     * 직원(Controller): "LoanMenu야, 손님한테 금액 좀 물어봐 줘."
     * → loanMenu.input("대출 금액: ")
     * <p>
     * LoanMenu:  ① 손님에게 말한다      "대출 금액: "
     * ② 손님이 쪽지에 적는다   " 1000000 "
     * ③ 쪽지의 빈칸을 정리한다  "1000000"
     * ④ 직원에게 쪽지를 건넨다  return "1000000"
     * <p>
     * 직원(Controller): 쪽지를 받아서 숫자로 바꾸고 다음 일을 한다
     */
    public String input(String question) {
        System.out.print(question);
        String answer = sc.nextLine();
        return answer.trim();   // 입력받은 말의 앞뒤 공백 지우고 반환함
    }

    // 상품 목록 출력
    public void printProducts(List<LoanProductDto> lpd) {
        System.out.println("대출 상품 목록");
        for (int i = 0; i < lpd.size(); i++) {
            System.out.print(lpd.get(i).getProductCode() + " | ");
            System.out.print(lpd.get(i).getProductName() + " | ");
            System.out.print("연 " + lpd.get(i).getRate() * 100 + "% | ");
            System.out.print("최대 " + String.format("%,d", lpd.get(i).getMaxLimit()) + "원 | ");
            System.out.print(lpd.get(i).getMinMonths() + "~" + lpd.get(i).getMaxMonths() + "개월 | ");
            System.out.print(lpd.get(i).getRepaymentType() + " | ");
            System.out.println(lpd.get(i).getRequiredGrade() + "등급 이내");   // ← 마지막만 println
        }
    }

    // 내 대출 목록을 출력, 비어있으면 "보유한 대출이 없습니다"
    public void printLoans(List<LoanDto> ld) {
        System.out.println("내 대출 목록");
        if (ld.isEmpty()) {
            System.out.println("보유한 대출이 없습니다.");
        } else {
            for (int i = 0; i < ld.size(); i++) {
                System.out.print(ld.get(i).getLoanNo() + " | ");
                System.out.print("상품 " + ld.get(i).getProductCode() + " | ");
                System.out.print("빌린 돈 " + String.format("%,d", ld.get(i).getPrincipal()) + "원 | ");
                System.out.print("남은 원금 " + String.format("%,d", ld.get(i).getRemainingPrincipal()) + "원 | ");
                System.out.print(ld.get(i).getPaidRounds() + "/" + ld.get(i).getMonths() + "회 | ");

                if (ld.get(i).isPaidOff()) {
                    System.out.println("상환완료");
                } else {
                    System.out.println("다음 납입액 " + String.format("%,d", ld.get(i).getMonthlyPayment()) + "원");
                }
            }
        }
    }

    // Controller가 건네준 문장을 화면에 그대로 출력해 주는 메서드
    // 성공안내나 일반 안내 문구 보여줄 때 사용
    public void printMessage(String str) {
        System.out.println(str);
    }

    // Controller가 건네준 문장을 화면에 그대로 출력해 주는 메서드
    // 오류가 발생했을 때 사용
    public void printError(String str) {
        System.out.println("[오류] "+str);
    }


}
