package in.sp.main.service;

import in.sp.main.dao.UserRepository;
import in.sp.main.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.beans.User;
import in.sp.main.dao.RegisterDao;

import java.time.LocalDate;

@Service
public class RegisterServiceImpl implements RegisterService
{
//	@Autowired
//	RegisterDao registerDao;

	@Autowired
	UserRepository userRepository;

	@Autowired
	User user;


	@Override
	public void registerService(RegisterDTO registerDTO)
	{
		Iterable<User> users = userRepository.findAll();
		for(User user:users)
		{
			if((registerDTO.getUserEmail().equals(user.getEmail())) &&
					(registerDTO.getPassword().equals(user.getPassword())))
			{
				throw new RuntimeException("user already exists");
			}
		}

		user.setName(registerDTO.getUsername());
		user.setAddress(registerDTO.getCity());
		user.setRole("user");
		user.setDateOfBirth(LocalDate.of(2020, 04, 04));
		user.setPhoneNumber(9999988888l);
		user.setPassword(registerDTO.getPassword());

		userRepository.save(user);

//		boolean status = registerDao.registerDao(std);
//		return status;

	}
}
