package com.example.Dto;

import com.sun.istack.NotNull;
import org.springframework.context.annotation.Scope;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class UserLoginDto {

    @NotEmpty
//    @Size(min = 5,max = 10)
    private String username;
    @NotEmpty
//    @Size(min = 4,max = 10)
    private String password;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
