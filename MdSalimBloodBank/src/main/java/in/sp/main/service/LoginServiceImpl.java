package in.sp.main.service;

import in.sp.main.beans.UserModel;
import in.sp.main.dao.LoginDao;
import in.sp.main.dao.UserRepository;
import in.sp.main.dto.LoginDTO;
import in.sp.main.dto.RegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao loginDao;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<UserModel> loginService(LoginDTO loginDTO) {

		List<UserModel> loggedinUser = new ArrayList<>();
		Iterable<UserModel> users = userRepository.findAll();
		System.out.println(users);
		System.out.println(loginDTO.getPassword());
		System.out.println(loginDTO.getUserEmail());
		for(UserModel user:users)
		{
			if((loginDTO.getUserEmail().equals(user.getUserEmail())) && (loginDTO.getPassword().equals(user.getPassword())))
			{
				System.out.println("success");
				loggedinUser.add(user);
				return loggedinUser;
			}
		}
		return loggedinUser;
	}

	public List<RegisterDTO> fetchSignupUsers()
	{
		ArrayList<RegisterDTO> users = new ArrayList<>();
		Iterable<UserModel> fetchUsers = userRepository.findAll();
		for(UserModel user:fetchUsers)
		{
			if(user.getRole().equals("user"))
			{
				users.add(convertToRegisterDTO(user));
			}
		}
		return users;
	}


	public RegisterDTO convertToRegisterDTO(UserModel user)
	{
		return modelMapper.map(user, RegisterDTO.class);
	}
}