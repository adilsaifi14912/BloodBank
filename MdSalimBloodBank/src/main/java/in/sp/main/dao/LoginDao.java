package in.sp.main.dao;

import java.util.List;

import in.sp.main.beans.User;

public interface LoginDao
{
	public List<User> loginDao(String email, String password);
}
