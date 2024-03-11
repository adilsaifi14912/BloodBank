package in.sp.main.service;

import java.util.List;

import in.sp.main.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.sp.main.beans.User;
import in.sp.main.dao.LoginDao;

@Service
public class LoginServiceImpl implements LoginService
{
//	@Autowired
//	LoginDao loginDao;

	@Autowired
	UserRepository userRepository;


	@Override
	public boolean loginService(String email, String password)
	{

		Iterable<User> users = userRepository.findAll();
		for(User user:users)
		{
			if(user.getEmail().equals(email) && user.getPassword().equals(password))
			{
				return true;
			}
		}
		return false;
//		LoginDaoImpl loginDaoImpl = new LoginDaoImpl();
//		List<Student> students_list = loginDaoImpl.loginDao(email, password);
//		return students_list;
		
//		LoginDao loginDao = new LoginDaoImpl();
//		List<Student> students_list = loginDao.loginDao(email, password);
//		return students_list;
		

//		List<User> students_list = loginDao.loginDao(email, password);
//		return students_list;
	}
}
