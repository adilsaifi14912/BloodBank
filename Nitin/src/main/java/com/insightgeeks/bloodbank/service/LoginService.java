package com.insightgeeks.bloodbank.service;

import com.insightgeeks.bloodbank.dto.LoginDTO;
import com.insightgeeks.bloodbank.dto.PasswordResetDTO;
import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.entities.UserModel;
import com.insightgeeks.bloodbank.repository.UserRepository;
import com.insightgeeks.bloodbank.util.LoginResult;
import com.insightgeeks.bloodbank.util.LoginStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * LoginService provides functionality for user login in the Blood Bank application.
 */
@Service
public class LoginService {

    // Static variables to track login attempts and block result
    public static int loginAttempts = 0;
    public static int blockResult;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginResult loginResult;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Performs user login based on the provided credentials.
     */
    public LoginResult performUserLogin(LoginDTO userLoginDTO) {
        Iterable<UserModel> users = userRepository.findAll();

        for (UserModel user : users) {
            if ((userLoginDTO.getUsername().equals(user.getUsername())) &&
                    userLoginDTO.getPassword().equals(user.getPassword())) {
                if (user.getPassword().equals("default")) {
                    loginResult.setUser(convertToSignupDTO(user));
                    loginResult.setStatus(LoginStatus.RESET_PASSWORD_REQUIRED);
                    return loginResult;
                } else {
                    loginResult.setUser(convertToSignupDTO(user));
                    loginResult.setStatus(LoginStatus.SUCCESSFUL_LOGIN);
                    return loginResult;
                }
            }
        }

        // Increment login attempts and handle block
        loginAttempts++;
        if (loginAttempts > 3) {
            if (blockUser(userLoginDTO.getUsername())) {
                blockResult = 1;
            } else {
                blockResult = 2;
                loginAttempts--;
            }
            loginAttempts = 0;
            loginResult.setBlockStatus(blockResult);
        }

        // Set invalid credentials status
        loginResult.setUser(null);
        loginResult.setStatus(LoginStatus.INVALID_CREDENTIALS);
        return loginResult;
    }

    /**
     * Fetches signed up users.
     */
    public List<SignupDTO> fetchSignedupUsers() {
        List<SignupDTO> signedupUsers = new ArrayList<>();
        Iterable<UserModel> users = userRepository.findAll();
        for (UserModel user : users) {
            if (user.getRole().equalsIgnoreCase("user")) {
                signedupUsers.add(convertToSignupDTO(user));
            }
        }
        return signedupUsers;
    }

    /**
     * Converts UserModel to SignupDTO.
     */
    public SignupDTO convertToSignupDTO(UserModel userModel) {
        return modelMapper.map(userModel, SignupDTO.class);
    }

    /**
     * Converts SignupDTO to UserModel.
     */
    public UserModel convertToUserModel(SignupDTO signupDTO) {
        return modelMapper.map(signupDTO, UserModel.class);
    }

    /**
     * Blocks a user based on username.
     */
    public boolean blockUser(String username) {
        Iterable<UserModel> users = userRepository.findAll();
        for (UserModel user : users) {
            if (user.getUsername().equals(username)) {
                user.setBlockStatus("blocked");
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    /**
     * Updates user password.
     */
    public String updatePassword(SignupDTO signupUser, PasswordResetDTO passwordResetDTO) {
        Optional<UserModel> getUser = userRepository.findByUsername(signupUser.getUsername());
        UserModel user = getUser.get();
        if (user.getUsername().equals(passwordResetDTO.getUsername())) {
            if (passwordResetDTO.getPassword().equals(passwordResetDTO.getConfirmedPassword())) {
                user.setPassword(passwordResetDTO.getPassword());
                userRepository.save(user);
                return "resetSuccess";
            } else {
                return "unmatchedPassword";
            }
        } else {
            return "missingUser";
        }
    }
}
