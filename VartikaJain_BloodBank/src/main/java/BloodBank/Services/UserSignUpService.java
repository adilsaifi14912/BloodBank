package BloodBank.Services;

import BloodBank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BloodBank.Entity.UserModel;
import BloodBank.Entity.UserSignUpDetailsDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserSignUpService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserSignUpDetailsDTO userSignUpDetailsDTO) {
        UserModel user = new UserModel();
        user.setUsername(userSignUpDetailsDTO.getUsername());
        user.setDob(userSignUpDetailsDTO.getDob());
        user.setContactNumber(userSignUpDetailsDTO.getContactNumber());
        user.setAddress(userSignUpDetailsDTO.getAddress());
        user.setPassword(userSignUpDetailsDTO.getPassword());
        user.setCreatedOn(Date.valueOf(LocalDate.now()));
        user.setBloodGroup(userSignUpDetailsDTO.getBloodGroup());
        user.setRole("EU");
        user.setCreatedby("AUTO");
        user.setFirstTimeLogin(true);
        userRepository.save(user);
    }
}
