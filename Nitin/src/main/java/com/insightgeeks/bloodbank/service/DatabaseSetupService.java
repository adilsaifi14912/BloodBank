package com.insightgeeks.bloodbank.service;

import com.insightgeeks.bloodbank.entities.BloodStock;
import com.insightgeeks.bloodbank.entities.UserModel;
import com.insightgeeks.bloodbank.repository.BloodStockRepository;
import com.insightgeeks.bloodbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * DatabaseSetupService initializes the database for the Blood Bank application by adding an admin user if none exists.
 */
@Service
public class DatabaseSetupService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BloodStockRepository bloodStockRepository;

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
        user.setCommision(0);
        user.setCoins(0);
        user.setNextRequestEligibleDate(LocalDate.now());
        // Check if an admin user already exists in the database
        Optional<UserModel> usr= userRepository.findByRole("admin");

        // If no admin user exists, save the admin user to the database
        if(usr.isEmpty())
        {
            userRepository.save(user);
        }


    }

    public void setupBloodStock() {

        List<BloodStock> existingEntries = (List<BloodStock>)bloodStockRepository.findAll();
        if (existingEntries.isEmpty()) {
            addDefaultBloodStockEntries();
        }
    }

    private void addDefaultBloodStockEntries() {

        bloodStockRepository.save(new BloodStock("A+", 10, LocalDateTime.now()));
        bloodStockRepository.save(new BloodStock("A-", 10, LocalDateTime.now()));
        bloodStockRepository.save(new BloodStock("B+", 10, LocalDateTime.now()));
        bloodStockRepository.save(new BloodStock("B-", 10, LocalDateTime.now()));
        bloodStockRepository.save(new BloodStock("AB+", 10, LocalDateTime.now()));
        bloodStockRepository.save(new BloodStock("AB-", 10, LocalDateTime.now()));
        bloodStockRepository.save(new BloodStock("O+", 10, LocalDateTime.now()));
        bloodStockRepository.save(new BloodStock("O-", 10, LocalDateTime.now()));
    }
}
