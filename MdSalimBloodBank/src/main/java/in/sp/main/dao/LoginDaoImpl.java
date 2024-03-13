package in.sp.main.dao;

import in.sp.main.beans.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public class LoginDaoImpl implements LoginDao
{
//	@Autowired
//	JdbcTemplate jdbcTemplate;

	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserModel> loginDao(String email, String password)
	{
		List<UserModel> s=null;
		try
		{
//			String mysql_query = "select * from register where email=? and password=?";
//			students_list = jdbcTemplate.query(mysql_query, new StudentMapper(), new Object[] {email, password});

//			Iterable<UserModel> users = userRepository.findAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
