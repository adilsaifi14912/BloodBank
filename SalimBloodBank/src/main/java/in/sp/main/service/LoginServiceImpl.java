package in.sp.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import in.sp.main.dto.PasswordResetDTO;
import in.sp.main.dto.RegisterDTO;
import in.sp.main.entities.UserModel;
import in.sp.main.dao.UserRepository;
import in.sp.main.dto.LoginDTO;
import in.sp.main.util.LoginResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoginServiceImpl implements LoginService
{
	@Autowired
	UserRepository userRepository;
	@Autowired
	LoginResult loginResult;
	@Autowired
	ModelMapper modelMapper;

	public static int loginAttempts = 0;
	public static int blockResult;

//
//	@Override
//	public boolean loginService(LoginDTO loginDTO)
//	{
//
//		Iterable<UserModel> users = userRepository.findAll();
//		for(UserModel user:users)
//		{
//			if(user.getUserEmail().equals(loginDTO.getUserEmail()) &&
//					user.getPassword().equals(loginDTO.getPassword()))
//			{
//				return true;
//			}
//		}
//		return false;
//	}

	public LoginResult loginService(LoginDTO userLoginDTO) {
		Iterable<UserModel> users = userRepository.findAll();

		for (UserModel user : users) {
			if ((userLoginDTO.getUserEmail().equals(user.getUserEmail())) &&
					userLoginDTO.getPassword().equals(user.getPassword())) {
				if (user.getPassword().equals("default")) {
					loginResult.setUser(convertToRegisterDTO(user));
					loginResult.setStatus("reset");
					return loginResult;
				} else {
					loginResult.setUser(convertToRegisterDTO(user));
					loginResult.setStatus("success");
					return loginResult;
				}
			}
		}

		// Increment login attempts and handle block
		loginAttempts++;
		if (loginAttempts > 3) {
			if (blockUser(userLoginDTO.getUserEmail())) {
				blockResult = 1;
			} else {
				blockResult = 2;
				loginAttempts--;
			}
			loginAttempts = 0;
			loginResult.setBlockStatus(blockResult);
		}

		// Set invalid credentials status
		loginResult.setUser(null);
		loginResult.setStatus("invalid");
		return loginResult;
	}

	public RegisterDTO convertToRegisterDTO(UserModel userModel)
	{
		return modelMapper.map(userModel,RegisterDTO.class);
	}

	public boolean blockUser(String userEmail) {
		Iterable<UserModel> users = userRepository.findAll();
		for (UserModel user : users) {
			if (user.getUserEmail().equals(userEmail)) {
				user.setBlockStatus("blocked");
				userRepository.save(user);
				return true;
			}
		}
		return false;
	}

	public String updatePassword(RegisterDTO signupUser, PasswordResetDTO passwordResetDTO) {
		Optional<UserModel> getUser = userRepository.findByUserEmail(signupUser.getUserEmail());
		UserModel user = getUser.get();
		if (user.getUserEmail().equals(passwordResetDTO.getUserEmail())) {
			if (passwordResetDTO.getPassword().equals(passwordResetDTO.getConfirmedPassword())) {
				user.setPassword(passwordResetDTO.getPassword());
				userRepository.save(user);
				return "resetSuccess";
			} else {
				return "unmatchedPassword";
			}
		} else {
			return "missingUser";
		}
	}

	public List<RegisterDTO> fetchSignedupUsers() {
		List<RegisterDTO> signedupUsers = new ArrayList<>();
		Iterable<UserModel> users = userRepository.findAll();
		for (UserModel user : users) {
			if (user.getRole().equalsIgnoreCase("user")) {
				signedupUsers.add(convertToRegisterDTO(user));
			}
		}
		return signedupUsers;
	}

}

