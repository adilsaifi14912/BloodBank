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

    /**
     * Signs up a new user.
     */
    public void signupUser(SignupDTO userDTO) {

        UserModel user = new UserModel();

        // check if user with same name exists
        Optional<UserModel> getUser = userRepository.findByUsername(userDTO.getUsername());
        if(!getUser.isEmpty())
        {
            throw new RuntimeException("user by this name already exists");
        }

        // Check if parent user exists
        Optional<UserModel> parent = userRepository.findById(userDTO.getParentId());
        if (parent.isEmpty()) {
            throw new RuntimeException("Parent does not exist");
        }

        // Check if user with the same phone number or username already exists
        Iterable<UserModel> fetchUsers = userRepository.findAll();
        for (UserModel fetchedUser : fetchUsers) {
            if ((userDTO.getPhoneNumber() == fetchedUser.getPhoneNumber()) && userDTO.getUsername().equals(fetchedUser.getUsername())) {
                throw new RuntimeException("User already exists");
            }
        }

        // Validate username
        if (userDTO.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty or contain only whitespace");
        }

        // Validate password match
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        // Validate phone number length
        if (String.valueOf(userDTO.getPhoneNumber()).length() != 10) {
            throw new IllegalArgumentException("Phone number must be of 10 digits");
        }

        // Validate address
        if (userDTO.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty or contain only whitespace");
        }

        // Parse date of birth
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirthConverted = LocalDate.parse(userDTO.getDateOfBirth(), formatter);
        LocalDate userCreationDate = LocalDate.now();

        // Set user properties
        user.setUsername(userDTO.getUsername());
        user.setRole("user");
        user.setPassword(userDTO.getPassword());
        user.setDateOfBirth(dateOfBirthConverted);
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAdddress(userDTO.getAddress());
        user.setBloodGroup(userDTO.getBloodGroup());
        user.setCreatedOn(userCreationDate);
        user.setCreatedBy("admin");
        user.setParentId(userDTO.getParentId());
        user.setBlockStatus("unblocked");

        // Save user
        userRepository.save(user);
    }
}
