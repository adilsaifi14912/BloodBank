package in.sp.main.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import in.sp.main.dao.BloodBankRepository;
import in.sp.main.dto.BloodBankDTO;
import in.sp.main.dto.PasswordResetDTO;
import in.sp.main.dto.RegisterDTO;
import in.sp.main.entities.BloodBankModel;
import in.sp.main.entities.UserModel;
import in.sp.main.dao.UserRepository;
import in.sp.main.dto.LoginDTO;
import in.sp.main.util.LoginResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.OverridesAttribute;


@Service
public class LoginServiceImpl implements LoginService
{
	@Autowired
	UserRepository userRepository;
	@Autowired
	LoginResult loginResult;
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	BloodBankRepository bloodBankRepository;

	public static int loginAttempts = 0;
	public static int blockResult;

	public LoginResult loginService(LoginDTO userLoginDTO) {
		Iterable<UserModel> users = userRepository.findAll();

		for (UserModel user : users) {

			if ((userLoginDTO.getUserEmail().equals(user.getUserEmail())) &&
					userLoginDTO.getPassword().equals(user.getPassword())) {

				if (user.getPassword().equals(String.valueOf(user.getDateOfBirth()))) {
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


	public List<RegisterDTO> fetchAgentData()
	{
		List<RegisterDTO> signedUpAgent=new ArrayList<>();
		Iterable<UserModel> agents=userRepository.findAll();
		for(UserModel agent:agents)
		{
			if(agent.getRole().equalsIgnoreCase("agent"))
			{
				signedUpAgent.add(convertToRegisterDTO(agent));
			}
		}
		return signedUpAgent;
	}


	public List<RegisterDTO> getAgentUsers(String username) {
		List<RegisterDTO>agentCreatedUsers=new ArrayList<>();
		List<UserModel> agentUsers = userRepository.findAllByCreatedBy(username);
//		System.out.println(agentUsers);
		for(UserModel user:agentUsers)
		{

			agentCreatedUsers.add(convertToRegisterDTO(user));
		}
		return agentCreatedUsers;
	}



	public RegisterDTO convertToSignupDTO(UserModel userModel) {
		return modelMapper.map(userModel, RegisterDTO.class);
	}

	private BloodBankDTO convertToBloodBankDTO(BloodBankModel bloodRequest) {
		return modelMapper.map(bloodRequest, BloodBankDTO.class);
	}

}



