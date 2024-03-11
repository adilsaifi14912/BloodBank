package in.sp.main.service;

import in.sp.main.beans.User;
import in.sp.main.dao.UserRepository;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DatabaseSetupService {

    @Autowired
    User user;

    @Autowired
    UserRepository userRepository;

    public void setupDatabase()
    {
        // Create a default admin user
        user.setName("admin1");
        user.setRole("admin");
        user.setPassword("sample");
        user.setDateOfBirth(LocalDate.of(1990, 03, 06));
        user.setPhoneNumber(8899287645l);
        user.setAddress("Pinnacle Bussiness Park, InsightGeeksSolutions Pvt.Ltd");

        // Check if an admin user already exists in the database
        Optional<User> usr= userRepository.findById(1);

        // If no admin user exists, save the admin user to the database
        if(usr.isEmpty())
        {
            userRepository.save(user);
        }
    }
}
