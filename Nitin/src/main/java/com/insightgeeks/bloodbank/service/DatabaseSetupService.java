package com.insightgeeks.bloodbank.service;

import com.insightgeeks.bloodbank.entities.UserModel;
import com.insightgeeks.bloodbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * DatabaseSetupService initializes the database for the Blood Bank application by adding an admin user if none exists.
 */
@Service
public class DatabaseSetupService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserModel user;


    /**
     * Sets up the database by adding an admin user if no admin user exists.
     * The admin user is added only during the first setup of the database.
     */
    public void setupDatabase()
    {
        // Create a default admin user
        user.setUsername("admin1");
        user.setRole("admin");
        user.setPassword("sample");
        user.setDateOfBirth(LocalDate.of(1990, 03, 06));
        user.setPhoneNumber(8899287645l);
        user.setAdddress("Pinnacle Bussiness Park, InsightGeeksSolutions Pvt.Ltd");
        user.setCreatedBy("self");
        user.setBloodGroup("B+");
        user.setCreatedOn(LocalDate.now());
        user.setBlockStatus("unblocked");

        // Check if an admin user already exists in the database
        Optional<UserModel> usr= userRepository.findByRole("admin");

        // If no admin user exists, save the admin user to the database
        if(usr.isEmpty())
        {
            userRepository.save(user);
        }
    }
}
