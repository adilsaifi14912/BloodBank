package com.insightgeeks.bloodbank.util;

import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.entities.UserModel;
import org.springframework.stereotype.Component;

@Component
public class LoginResult {
    private LoginStatus status;
    private SignupDTO user;
    private int blockStatus;


    public LoginStatus getStatus() {
        return status;
    }

    public void setStatus(LoginStatus status) {
        this.status = status;
    }

    public SignupDTO getUser() {
        return user;
    }

    public void setUser(SignupDTO user) {
        this.user = user;
    }

    public int getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(int blockStatus) {
        this.blockStatus = blockStatus;
    }
}