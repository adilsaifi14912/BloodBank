package in.sp.main.service;

import in.sp.main.dto.BloodBankDTO;
import in.sp.main.dto.LoginDTO;
import in.sp.main.dto.RegisterDTO;
import in.sp.main.util.LoginResult;

import java.util.List;

public interface LoginService
{
	public LoginResult loginService(LoginDTO loginDTO);

}
