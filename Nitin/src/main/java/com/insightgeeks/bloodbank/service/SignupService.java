package com.insightgeeks.bloodbank.service;

import com.insightgeeks.bloodbank.dto.SignupDTO;
import com.insightgeeks.bloodbank.entities.UserModel;
import com.insightgeeks.bloodbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


/**
 * SignupService provides functionality for user signup in the Blood Bank application.
 */
@Service
public class SignupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserModel user;


    /**
     * Registers a new user based on the provided signup information.
     *
     * @param userDTO SignupDTO object containing user signup information
     */
    public void signupUser(SignupDTO userDTO)
    {
        // Check if the parent user exists in the repository
        Optional<UserModel> parent = userRepository.findById(userDTO.getParentId());
        if(parent.isEmpty())
        {
            throw new RuntimeException("Parent does not exist");
        }

        // Fetch all users from the repository to check if the provided username and phone number already exists
        Iterable<UserModel> fetchUsers = userRepository.findAll();
        for(UserModel user:fetchUsers)
        {
            if((userDTO.getPhoneNumber() == user.getPhoneNumber()) && userDTO.getUsername().equals(user.getUserName()))
            {
                // If a user with the same phone number and username already exists, throw an exception
                throw new RuntimeException("user already exists");
            }
        }

        // Validate if the username is empty or contains only whitespace
        if(userDTO.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty or contain only whitespace");
        }

        // Validate if the passwords match
        if (!userDTO.getSetpassword().equals(userDTO.getRecheckpassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        // Validate if the phone number is of 10 digits
        if(String.valueOf(userDTO.getPhoneNumber()).length() != 10)
        {
            throw new IllegalArgumentException("Phone number must be of 10 digits");
        }

        // Validate if the address is empty or contains only whitespace
        if(userDTO.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("address cannot be empty or contain only whitespace");
        }

        // Convert date of birth string to LocalDate object
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirthConverted = LocalDate.parse(userDTO.getDateOfBirth(), formatter);

        // Populate UserModel object with user signup information
        user.setUserName(userDTO.getUsername());
        user.setRole("user");
        user.setPassword(userDTO.getSetpassword());
        user.setDob(dateOfBirthConverted);
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAdddress(userDTO.getAddress());

        // Save the user to the repository
        userRepository.save(user);

    }
}
