package in.sp.main.service;

import in.sp.main.dao.UserRepository;
import in.sp.main.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.beans.UserModel;

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
			if((registerDTO.getUserEmail().equals(user.getEmail())) &&
					(registerDTO.getPassword().equals(user.getPassword())))
			{
				throw new RuntimeException("user already exists");
			}
		}

		user.setName(registerDTO.getUsername());
		user.setEmail(registerDTO.getUserEmail());
		user.setAddress(registerDTO.getCity());
		user.setRole("user");
		user.setDateOfBirth(LocalDate.of(2020, 04, 04));
		user.setGender(registerDTO.getGender());
		user.setPhoneNumber(9999988888l);
		user.setPassword(registerDTO.getPassword());

		userRepository.save(user);
	}
}
