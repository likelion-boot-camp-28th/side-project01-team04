package com.fourTeam.bank.member.controller;

import com.fourTeam.bank.member.model.MemberService;

import java.util.Scanner;

public class MemberContoller {
    MemberService ms = new MemberService();
    Scanner sc = new Scanner(System.in);

    public String login(){
        System.out.print("id : ");
        String id = sc.next();
        System.out.print("pw : ");
        String pw = sc.next();
        boolean isLogin = ms.login(id,pw);

        if(isLogin){
            System.out.println("로그인 성공");
            return id;
        }else{
            System.out.println("로그인 실패");
            return "";
        }
    }

    public void register(){
        System.out.print("id : ");
        String id = sc.next();
        System.out.print("pw : ");
        String pw = sc.next();
        System.out.print("name : ");
        String name = sc.next();
        System.out.print("address : ");
        String address = sc.next();

        int result = ms.register(id,pw,name,address);
        if(result == 0){
            System.out.println("이미 존재하는 아이디로 가입 불가 합니다.");
        } else if (result ==-1) {
            System.out.println("아이디가 빈값이면 안됩니다.");
        } else{
            System.out.println("가입 성공! ");
        }
    }
}
