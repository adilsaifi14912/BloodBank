package com.insightgeeks.bloodbank.util;

import com.insightgeeks.bloodbank.dto.SignupDTO;
import org.springframework.stereotype.Component;

@Component
public class LoginResult {
    private String status;
    private SignupDTO user;
    private int blockStatus;


    public String getStatus() {
        return status;
    }

    public void setStatus(String  status) {
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