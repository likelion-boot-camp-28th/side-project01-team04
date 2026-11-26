package com.fourTeam.bank.termdeposit.service;

import com.fourTeam.bank.termdeposit.model.DepositAccount;
import com.fourTeam.bank.termdeposit.model.InstallmentAccount;
import com.fourTeam.bank.termdeposit.model.SavingProductType;

import java.util.ArrayList;

public class SavingService {

    // 정기예금
    private ArrayList<DepositAccount> accounts = new ArrayList<>(); // 정기 예금 몇 개를 만들지 모르니까 배열 대신 Arraylist 사용

    public void join(String id, long amount, SavingProductType type) {

        DepositAccount depositAccount = new DepositAccount(id, amount, type);
        accounts.add(depositAccount);

    }

    // 로그인한 회원의 계좌만 돌려주기 위해 만듬
    public ArrayList<DepositAccount> getMyAccounts(String id) {
        ArrayList<DepositAccount> myAccounts = new ArrayList<>(); // 내 계좌가 여기에 저장됨
        for (DepositAccount depositAccount : accounts) { // 계좌를 하나씩 확인
            if (depositAccount.getId().equals(id)) { // 아이디가 같은지 확인
                myAccounts.add(depositAccount); // 같으면 목록에 담음
            }
        }
        return myAccounts;
    }

    // 적금
    private ArrayList<InstallmentAccount> installments = new ArrayList<>(); // 적금도 몇 개 가입할지 모르니까 ArrayList 사용

    public void joinInstallment(String id, long monthlyAmount, SavingProductType type) {

        InstallmentAccount installmentAccount = new InstallmentAccount(id, monthlyAmount, type);
        installments.add(installmentAccount);

    }

    // 로그인한 회원의 적금만 돌려주기 위해 만듬
    public ArrayList<InstallmentAccount> getMyInstallments(String id) {
        ArrayList<InstallmentAccount> myInstallments = new ArrayList<>(); // 내 적금이 여기에 저장됨
        for (InstallmentAccount installmentAccount : installments) { // 적금을 하나씩 확인
            if (installmentAccount.getId().equals(id)) { // 아이디가 같은지 확인
                myInstallments.add(installmentAccount); // 같으면 목록에 담음
            }
        }
        return myInstallments;
    }
}