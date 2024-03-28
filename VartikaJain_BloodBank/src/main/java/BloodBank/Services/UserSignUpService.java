package BloodBank.Services;

import BloodBank.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import BloodBank.Entity.UserModel;
import BloodBank.Entity.UserSignUpDetailsDTO;
import java.time.LocalDateTime;

@Service
public class UserSignUpService {

    @Autowired
    private UserRepository userRepository;

    public void addUser(UserSignUpDetailsDTO userSignUpDetailsDTO,String role,String createdBy) {
        UserModel user = new UserModel();
        user.setUsername(userSignUpDetailsDTO.getUsername());
        user.setDob(userSignUpDetailsDTO.getDob());
        user.setContactNumber(userSignUpDetailsDTO.getContactNumber());
        user.setAddress(userSignUpDetailsDTO.getAddress());
        if(userSignUpDetailsDTO.getPassword()==null||userSignUpDetailsDTO.getPassword().isEmpty())
            user.setPassword(String.valueOf(userSignUpDetailsDTO.getDob()));
        else
            user.setPassword((userSignUpDetailsDTO.getPassword()));
        user.setCreatedOn(LocalDateTime.now());
        user.setBloodGroup(userSignUpDetailsDTO.getBloodGroup());
        user.setRole(role);
        user.setCreatedby(createdBy);
        user.setFirstTimeLogin(!createdBy.equals("AUTO"));
        user.setCoin(0);
        userRepository.save(user);
    }

}
