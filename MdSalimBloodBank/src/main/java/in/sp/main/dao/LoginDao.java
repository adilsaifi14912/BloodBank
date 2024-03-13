package in.sp.main.dao;

import in.sp.main.beans.UserModel;

import java.util.List;

public interface LoginDao
{
	public List<UserModel> loginDao(String email, String password);
}
