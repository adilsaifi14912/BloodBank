package com.example.dto;

import javax.validation.constraints.NotNull;

public class UserLoginDto {
    @NotNull
    private String userName;
    @NotNull
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
