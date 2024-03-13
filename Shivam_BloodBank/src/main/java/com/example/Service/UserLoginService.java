package com.example.Service;

import com.example.Dto.UserLoginDto;
import com.example.Dto.UserSignupDto;
import com.example.Model.UserModel;
import com.example.Repository.BloodBankRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserLoginService {
    @Autowired
    private BloodBankRepository bloodBankRepository;
    @Autowired
    private ModelMapper modelMapper;

    //check for user exist or not
    public UserSignupDto check(UserLoginDto userLoginDto) {
        System.out.println("inside check");
        UserModel user=bloodBankRepository.findByUsername(userLoginDto.getUsername());
            if(user!=null && userLoginDto.getPassword().equals(user.getPassword())){
                user.setLoginAttempt(0);
                bloodBankRepository.save(user);
                //we use ModelMapper for setting values in Dto class
                return convertToDto(user);

            }
            else {
                if(user!=null)
                    incrementLoginAttempt(user);
                return null;
            }

    }
    public UserSignupDto convertToDto(UserModel userModel){
        return modelMapper.map(userModel, UserSignupDto.class);
    }
    public void incrementLoginAttempt(UserModel user){
        //increment login count
        user.setLoginAttempt(user.getLoginAttempt()+1);

        //check for 3 times wrong attempt
        if(user.getLoginAttempt()>3){
            user.setLockStatus(true);
        }
        bloodBankRepository.save(user);

    }

    //Adding signup users to list to show on admin dashboard
    public List<UserSignupDto> signupUsers(){
        Iterable<UserModel> allFetchUser = bloodBankRepository.findAll();
        List<UserSignupDto> usersList=new ArrayList<>();
        for(UserModel user:allFetchUser){
            if(!user.getRole().equalsIgnoreCase("admin")){
                usersList.add(convertToDto(user));
            }
        }
        return usersList;
    }

    public void updatePassword(UserLoginDto userLoginDto){
        UserModel user = bloodBankRepository.findByUsername(userLoginDto.getUsername());
        user.setPassword(userLoginDto.getPassword());
        user.setFirstLogin(false);  //now setting first login false
        bloodBankRepository.save(user);

    }
}
