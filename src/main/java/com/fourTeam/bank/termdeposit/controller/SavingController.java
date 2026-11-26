package com.fourTeam.bank.termdeposit.controller;

import com.fourTeam.bank.termdeposit.view.SavingMenu;

public class SavingController {

    private SavingMenu sm = new SavingMenu();

    public void run(String memberId) {
        sm.menu(memberId);
    }
}
