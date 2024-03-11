package com.example.Service;
import com.example.Dto.UserLoginDto;
import com.example.Dto.UserSignupDto;
import com.example.Model.UserModel;
import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private UserRepository userRepository;
    //1st Approach
//    public boolean insert(UserSignupDto userDto){
//         check whether username is already exist or not
//        Iterable<UserModel> fetchUsers = userRepository.findAll();
//        for(UserModel userModel:fetchUsers){
//            if(userDto.getUsername().equals(userModel.getUsername()))
//                return false;
//        }

    //2nd Approach
     public boolean insert(UserSignupDto userDto){
         //check whether username is already exist or not
        UserModel user= userRepository.checkIfExist(userDto.getUsername(), userDto.getPassword());
        if(user!=null && (userDto.getUsername().equals(user.getUsername()))) {
            return false;
        }
        UserModel user1=new UserModel();
        user1.setName(userDto.getName());
        user1.setUsername(userDto.getUsername());
        user1.setPassword(userDto.getPassword());
        user1.setDob(userDto.getDob());
        UserModel save = userRepository.save(user1);
        return true;
    }

}
