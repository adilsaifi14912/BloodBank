package com.insightgeeks.bloodbank.service;

import com.insightgeeks.bloodbank.dto.LoginDTO;
import com.insightgeeks.bloodbank.entities.UserModel;
import com.insightgeeks.bloodbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * LoginService provides functionality for user login in the Blood Bank application.
 */
@Service
public class LoginService{

    @Autowired
    UserRepository userRepository;

    /**
     * Performs user login based on the provided login credentials.
     *
     * @param userLoginDTO LoginDTO object containing user login credentials
     * @return true if the user login is successful; otherwise, false
     */
    public boolean performUserLogin(LoginDTO userLoginDTO)
    {
        // Retrieve all users from the repository
        Iterable<UserModel> users = userRepository.findAll();

        // Iterate through each user to check if the provided login credentials match
        for(UserModel user : users)
        {
            if((userLoginDTO.getUsername().equals(user.getUserName())) &&
                    userLoginDTO.getPassword().equals(user.getPassword()))
            {

                return true;
            }
        }

        return false;
    }
}
