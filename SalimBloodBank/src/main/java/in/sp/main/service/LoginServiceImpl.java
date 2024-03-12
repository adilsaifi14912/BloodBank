package in.sp.main.service;

import in.sp.main.dao.UserRepository;
import in.sp.main.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.beans.UserModel;

@Service
public class LoginServiceImpl implements LoginService
{
//	@Autowired
//	LoginDao loginDao;

	@Autowired
	UserRepository userRepository;


	@Override
	public boolean loginService(LoginDTO loginDTO)
	{

		Iterable<UserModel> users = userRepository.findAll();
		for(UserModel user:users)
		{
			if(user.getEmail().equals(loginDTO.getUserEmail()) &&
					user.getPassword().equals(loginDTO.getPassword()))
			{
				return true;
			}
		}
		return false;
	}
}
