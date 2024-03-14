package in.sp.main.service;

import in.sp.main.dto.LoginDTO;
import in.sp.main.util.LoginResult;

public interface LoginService
{
	public LoginResult loginService(LoginDTO loginDTO);
}
