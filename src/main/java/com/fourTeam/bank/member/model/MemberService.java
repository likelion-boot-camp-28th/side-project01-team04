package com.fourTeam.bank.member.model;

import java.util.ArrayList;

public class MemberService {

    ArrayList<MemberDao> members = new ArrayList<>();

    public boolean login(String id,String pw) {
        boolean loginOk = false;

        for(MemberDao m : members){
            if(m.getId().equals(id) && m.getPwd().equals(pw)){
                loginOk = true;
            }
        }
        return loginOk;
    }

    public int register(String id,String pw, String name, String address) {
        if(id.equals("")){
            return -1;
        }
        for(MemberDao m:members){
            if(id.equals(m.getId())){
                return 0;
            }
        }

        members.add(new MemberDao(id,pw,name,address));
        return 1;
    }
}
