package in.sp.main.service;

import in.sp.main.beans.UserModel;
import in.sp.main.dao.UserRepository;
import in.sp.main.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegisterServiceImpl implements RegisterService
{
	@Autowired
	UserRepository userRepository;

	@Autowired
	UserModel user;


	@Override
	public void registerService(RegisterDTO registerDTO)
	{
		Iterable<UserModel> users = userRepository.findAll();
		for(UserModel user:users)
		{
			if((registerDTO.getUserEmail().equals(user.getUserEmail())) &&
					(registerDTO.getPassword().equals(user.getPassword())))
			{
				throw new RuntimeException("user already exists");
			}
		}

		user.setName(registerDTO.getUsername());
		user.setUserEmail(registerDTO.getUserEmail());
		user.setCity(registerDTO.getCity());
		user.setRole("user");
		user.setDateOfBirth(registerDTO.getDateOfBirth());
		user.setGender(registerDTO.getGender());
		user.setPhoneNumber(registerDTO.getPhoneNumber());
		user.setPassword(registerDTO.getPassword());

		userRepository.save(user);
	}
}
