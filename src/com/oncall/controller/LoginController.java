package com.oncall.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oncall.entity.UserRegistration;
import com.oncall.service.SomeService;

@Controller
public class LoginController {
	
	@Autowired
	private SomeService someService;
	
	public static String theUser;
	
	
	@GetMapping("/showLoginPage")
	public String showLoginPage(HttpServletRequest request) {
		
		System.out.println("**********--- " + request.getServletPath());
		
		return "login";
	}
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationPage(Model theModel,
			HttpServletRequest request) {
		
		UserRegistration newUser = new UserRegistration();
		
		theModel.addAttribute("user", newUser);
		
		return "register";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") UserRegistration theUser,
			HttpServletRequest request) {
		

		 UserRegistration checkUser = someService.verifyUserExistence(theUser);
		 
		 HttpSession session = request.getSession();
		 
		if(checkUser == null) {
			
			someService.saveUser(theUser);
			
			someService.updateAuthorities(theUser);
			
			session.setAttribute("exists", null);
			
			return "redirect:/";
			
		}
		String obj = checkUser.getUserName();
		session.setAttribute("exists", obj);
		
		return "redirect:/showRegistrationForm";
		
	}

}
