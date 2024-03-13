package in.sp.main.service;

import in.sp.main.beans.UserModel;
import in.sp.main.dto.LoginDTO;

import java.util.List;

public interface LoginService
{
	public List<UserModel> loginService(LoginDTO loginDTO);
}
