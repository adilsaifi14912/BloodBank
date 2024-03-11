package com.example.Service;

import com.example.Dto.UserLoginDto;
import com.example.Dto.UserSignupDto;

public interface ServiceInterface {
    boolean insert(UserSignupDto userSignupDto);
    boolean check(UserLoginDto userLoginDto);
}
