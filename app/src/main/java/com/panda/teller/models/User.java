package com.panda.teller.models;

/**
 * Created by root on 16-10-31.
 */

public class User {

    private int id = 0;

    private String name = "";

    private String pwd = "";

    private String mobile = "";

    private String email = "";

    private String intro = "";

    private String head = "";

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getIntro() {
        return intro;
    }

    public String getHead() {
        return head;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public boolean isValid() {
        if(id == 0) {
            return false;
        } else {
            return true;
        }
    }
}
