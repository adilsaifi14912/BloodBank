package in.sp.main.controllers;

import in.sp.main.dto.LoginDTO;
import in.sp.main.dto.RegisterDTO;
import in.sp.main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyController
{

	@Autowired
	LoginServiceImpl loginService;

	@Autowired
	DatabaseSetupService databaseSetupService;

	@Autowired
	RegisterServiceImpl registerService;

	@GetMapping("/")
	public String openHomePage()
	{
		databaseSetupService.setupDatabase();
		return "home-page";
	}
	
	@GetMapping("/homePage")
	public String openHomePagee()
	{
		return "home-page";
	}
	
	@GetMapping("/aboutUsPage")
	public String openAboutUsPage()
	{
		return "about-us-page";
	}
	
	@GetMapping("/contactUsPage")
	public String openContactUsPage()
	{
		return "contact-us-page";
	}
	
	@GetMapping("/loginPage")
	public String openLoginPage()
	{
		return "login-page";
	}
	
	@GetMapping("/registerPage")
	public String openRegisterPage()
	{
		return "register-page";
	}

	
	@PostMapping("/loginForm")
	public String login(
			@ModelAttribute @Validated LoginDTO loginDTO,BindingResult bindingResult,Model model
			)
	{

		if(bindingResult.hasErrors())
		{
			model.addAttribute("formatError",bindingResult.getFieldError().getDefaultMessage());
			return "login-page";
		}


		if(loginService.loginService(loginDTO))
		{
			return "profile-page";
		}
		else{
			model.addAttribute("status","Invalid credentials");
			return "login-page";
		}
	}
	
	@GetMapping("/profilePage")
	public String openProfilePage()
	{
		return "profile-page";
	}
	
	@GetMapping("/logoutPage")
	public String logout(HttpSession session)
	{
		session.invalidate();
		
		return "login-page";
	}

	
	@PostMapping("/regForm")
	public String register(
			Model model,
			@ModelAttribute @Validated RegisterDTO registerDTO, BindingResult bindingResult
			)
	{
		if(bindingResult.hasErrors())
		{
			model.addAttribute("formatError",bindingResult.getFieldError().getDefaultMessage());
			return "login-page";
		}
		try{
			registerService.registerService(registerDTO);
			return "login";
		}
		catch (Exception e)
		{
			model.addAttribute("errorr", e.getMessage());
			return "register-page";
		}
	}
}
