package in.sp.main.dao;

import java.util.List;

import in.sp.main.beans.UserModel;

public interface LoginDao
{
	public List<UserModel> loginDao(String email, String password);
}
