package com.twinkle.JakSim.account;

import lombok.Data;

@Data
public class UserDO {
    private String id;
    private String pw;
    private String name;
    private int gender;
    private String tel;
    private int question;
    private String answer;
    private String birth;
    private String c_dt;
    private int role;
}