package in.sp.main.service;

import in.sp.main.dao.UserRepository;
import in.sp.main.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DatabaseSetupService {

    @Autowired
    UserModel user;

    @Autowired
    UserRepository userRepository;

    public void setupDatabase()
    {
        // Create a default admin user
        user.setUsername("admin1");
        user.setGender("male");
        user.setUserEmail("admin123@gmail.com");
        user.setRole("admin");
        user.setPassword("sample");
        user.setDateOfBirth(LocalDate.of(1990, 03, 06));
        user.setPhoneNumber(8899287645l);
        user.setCity("Pinnacle Bussiness Park, InsightGeeksSolutions Pvt.Ltd");
        user.setBloodGroup("B+");
        user.setCreatedOn(LocalDate.now());
        user.setCreatedBy("self");
        user.setBlockStatus("unblocked");
        user.setUserEmail("admin990@gmail.com");
        user.setGender("male");

        // Check if an admin user already exists in the database
        Optional<UserModel> usr= userRepository.findByRole("admin");

        // If no admin user exists, save the admin user to the database
        if(usr.isEmpty())
        {
            userRepository.save(user);
        }
    }
}
