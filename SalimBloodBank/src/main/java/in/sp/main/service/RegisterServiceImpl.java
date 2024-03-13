package in.sp.main.service;

import in.sp.main.beans.UserModel;
import in.sp.main.dao.UserRepository;
import in.sp.main.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

<<<<<<< HEAD:MdSalimBloodBank/src/main/java/in/sp/main/service/RegisterServiceImpl.java
=======
import in.sp.main.beans.UserModel;

>>>>>>> 7924172da61526155fa97a2c46105d829955dc17:SalimBloodBank/src/main/java/in/sp/main/service/RegisterServiceImpl.java
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
<<<<<<< HEAD:MdSalimBloodBank/src/main/java/in/sp/main/service/RegisterServiceImpl.java
		user.setUserEmail(registerDTO.getUserEmail());
		user.setCity(registerDTO.getCity());
		user.setRole("user");
		user.setDateOfBirth(registerDTO.getDateOfBirth());
		user.setGender(registerDTO.getGender());
		user.setPhoneNumber(registerDTO.getPhoneNumber());
=======
		user.setEmail(registerDTO.getUserEmail());
		user.setAddress(registerDTO.getCity());
		user.setRole("user");
		user.setDateOfBirth(LocalDate.of(2020, 04, 04));
		user.setGender(registerDTO.getGender());
		user.setPhoneNumber(9999988888l);
>>>>>>> 7924172da61526155fa97a2c46105d829955dc17:SalimBloodBank/src/main/java/in/sp/main/service/RegisterServiceImpl.java
		user.setPassword(registerDTO.getPassword());

		userRepository.save(user);
	}
}
