package in.sp.main.service;

import in.sp.main.entities.UserModel;
import in.sp.main.dao.UserRepository;
import in.sp.main.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class RegisterServiceImpl implements RegisterService
{
	@Autowired
	UserRepository userRepository;

	@Override
	public void registerService(RegisterDTO registerDTO, RegisterDTO usr)
	{

		UserModel user = new UserModel();
		// check if user with same name exists
		Optional<UserModel> getUser = userRepository.findByUserEmail(registerDTO.getUserEmail());
		if(!getUser.isEmpty())
		{
			throw new RuntimeException("user by this email already exists");
		}

		// Check if user with the same phone number or username already exists
		Iterable<UserModel> fetchUsers = userRepository.findAll();
		for (UserModel fetchedUser : fetchUsers) {
			if ((registerDTO.getPhoneNumber() == fetchedUser.getPhoneNumber()) && registerDTO.getUsername().equals(fetchedUser.getUsername())) {
				throw new RuntimeException("User already exists");
			}
		}

		// Validate username
		if (registerDTO.getUsername().trim().isEmpty()) {
			throw new IllegalArgumentException("Username cannot be empty or contain only whitespace");
		}


		// Validate phone number length
		if (String.valueOf(registerDTO.getPhoneNumber()).length() != 10) {
			throw new IllegalArgumentException("Phone number must be of 10 digits");
		}

		// Validate address
		if (registerDTO.getCity().trim().isEmpty()) {
			throw new IllegalArgumentException("Address cannot be empty or contain only whitespace");
		}

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dateOfBirthConverted = LocalDate.parse(registerDTO.getDateOfBirth(), formatter);

		user.setUsername(registerDTO.getUsername());
		user.setUserEmail(registerDTO.getUserEmail());
		user.setCity(registerDTO.getCity());
		user.setDateOfBirth(dateOfBirthConverted);
		user.setGender(registerDTO.getGender());
		user.setPhoneNumber(registerDTO.getPhoneNumber());
		user.setGender(registerDTO.getGender());
		user.setPassword(registerDTO.getPassword());
        user.setBloodGroup(registerDTO.getBloodGroup());
        user.setCreatedOn(LocalDate.now());
        user.setBlockStatus("unblocked");

		if(usr!=null ){
			user.setRole("agent");
			user.setCreatedBy("admin");
			user.setPassword(registerDTO.getDateOfBirth());
		}
		else {user.setRole("user");
			user.setCreatedBy("self");
		}

		userRepository.save(user);

	}



	public void signupAgentCreatedUser(RegisterDTO registerDTO, RegisterDTO user)
	{

		UserModel userModel = new UserModel();
		Optional<UserModel> getUser = userRepository.findByUserEmail(registerDTO.getUserEmail());
		if(!getUser.isEmpty())
		{
			throw new RuntimeException("user by this email already exists");
		}

		if (registerDTO.getUsername().trim().isEmpty()) {
			throw new IllegalArgumentException("Username cannot be empty or contain only whitespace");
		}

		if (String.valueOf(registerDTO.getPhoneNumber()).length() != 10) {
			throw new IllegalArgumentException("Phone number must be of 10 digits");
		}
		if (registerDTO.getCity().trim().isEmpty()) {
			throw new IllegalArgumentException("Address cannot be empty or contain only whitespace");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateOfBirthConverted = LocalDate.parse(registerDTO.getDateOfBirth(), formatter);

		userModel.setUserEmail(registerDTO.getUserEmail());
		userModel.setDateOfBirth(dateOfBirthConverted);
		userModel.setPhoneNumber(registerDTO.getPhoneNumber());
		userModel.setCity(registerDTO.getCity());
		userModel.setBloodGroup(registerDTO.getBloodGroup());
		userModel.setCreatedOn(LocalDate.now());
		userModel.setBlockStatus("unblocked");
		userModel.setRole("user");
		userModel.setCommission(0f);
		userModel.setPassword(registerDTO.getDateOfBirth());
		userModel.setCreatedBy(user.getUsername());
		userModel.setGender(registerDTO.getGender());
		userModel.setUsername(registerDTO.getUsername());

		userRepository.save(userModel);

	}
}
