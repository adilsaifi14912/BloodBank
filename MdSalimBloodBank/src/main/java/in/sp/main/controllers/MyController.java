package in.sp.main.controllers;

import java.util.List;

import in.sp.main.dto.RegisterDTO;
import in.sp.main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.sp.main.beans.User;
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
	public String login(@RequestParam("email1") String email, @RequestParam("pass1") String password, HttpSession session, Model model)
	{
//		String page = "error-page";
//
//		List<User> students_list = loginService.loginService(email, password);
//		if(students_list.size() != 0)
//		{
//			session.setAttribute("session_name", students_list.get(0).getName());
//			session.setAttribute("session_email", students_list.get(0).getEmail());
//			session.setAttribute("session_gender", students_list.get(0).getGender());
//			session.setAttribute("session_city", students_list.get(0).getCity());
//
//			page = "profile-page";
//		}
//		else
//		{
//			model.addAttribute("model_message", "Email id and password didnt matched....");
//			model.addAttribute("model_pagename", "login");
//
//			page = "error-page";
//		}
//		return page;
		if(loginService.loginService(email,password))
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
//							@RequestParam("name1") String name,
//							@RequestParam("email1") String email,
//							@RequestParam("pass1") String password,
//							@RequestParam(name = "gender1", required = false) String gender,
//							@RequestParam("city1") String city,
			Model model,
			@ModelAttribute @Validated RegisterDTO registerDTO
			)
	{
		try{
			registerService.registerService(registerDTO);
			return "error-page";
		}
		catch (Exception e)
		{
			model.addAttribute("error", e.getMessage());
			return "register-page";
		}

//		String page = "error-page";
//
//		User std = new User();
//		std.setName(name);
//		std.setEmail(email);
//		std.setPassword(password);
//		std.setGender(gender);
//		std.setCity(city);
//
//		boolean status = registerService.registerService(std);
//		if(status)
//		{
//			model.addAttribute("model_message", "Student Registered Successfully");
//			model.addAttribute("model_pagename", "register");
//
//			page = "success-page";
//		}
//		else
//		{
//			model.addAttribute("model_message", "Student not registered due to some error");
//			model.addAttribute("model_pagename", "register");
//
//			page = "error-page";
//		}
//
//		return page;
	}
}
