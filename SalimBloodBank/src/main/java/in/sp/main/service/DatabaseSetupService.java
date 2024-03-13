package in.sp.main.service;

import in.sp.main.beans.UserModel;
import in.sp.main.dao.UserRepository;
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
        user.setName("admin1");
        user.setGender("male");
<<<<<<< HEAD:MdSalimBloodBank/src/main/java/in/sp/main/service/DatabaseSetupService.java
        user.setUserEmail("admin123@gmail.com");
=======
        user.setEmail("admin123@gmail.com");
>>>>>>> 7924172da61526155fa97a2c46105d829955dc17:SalimBloodBank/src/main/java/in/sp/main/service/DatabaseSetupService.java
        user.setRole("admin");
        user.setPassword("sample");
        user.setDateOfBirth(LocalDate.of(1990, 03, 06));
        user.setPhoneNumber(8899287645l);
        user.setCity("Pinnacle Bussiness Park, InsightGeeksSolutions Pvt.Ltd");

        // Check if an admin user already exists in the database
        Optional<UserModel> usr= userRepository.findById(1);

        // If no admin user exists, save the admin user to the database
        if(usr.isEmpty())
        {
            userRepository.save(user);
        }
    }
}
