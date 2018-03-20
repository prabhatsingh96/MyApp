package com.example.fluper.myapp.appUtil;

/**
 * Created by fluper on 12/3/18.
 */

public class SignUpDetail {

    private String name;
    private String email;

    public SignUpDetail(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
