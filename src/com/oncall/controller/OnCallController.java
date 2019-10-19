package com.oncall.controller;

import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.*;
import javax.servlet.http.*;
import java.time.Instant;

import com.oncall.entity.*;

import com.oncall.service.SomeService;

@Controller
public class OnCallController extends HttpServlet {
	
	
	//injecting the service
	@Autowired
	private SomeService someService;
	
	@GetMapping("/")
	public String showMenus(HttpServletRequest request, HttpServletResponse response, Model theModel) {
		
		//open or get current HttpSession
		HttpSession theSession = request.getSession();

		if(theSession.isNew()) {
			
			ArrayList<Menu> theList = new ArrayList<Menu>();
			int ord = 0;
			String theSessionId = theSession.getId();
			theSession.setAttribute("sessionId", theSessionId);
			theSession.setAttribute("array", theList);
			theSession.setAttribute("orders", ord);
		}
		
		//get the menu1 from the service
		List<Menu1> theMenu = someService.getMenu1();
		
		List<Menu2> theMenu2 = someService.getMenu2();
		
		//add data to the model
		theModel.addAttribute("menu1", theMenu);
		theModel.addAttribute("menu2", theMenu2);
		
		return "main-page";
		
	}
	@RequestMapping("/addToCart")
	public String addToCart(@RequestParam("itemId") int theId,
			@RequestParam("theTable") String name,
			HttpServletRequest request) {
		
		//get the current session
		HttpSession theSession = request.getSession();
		
		//read theItem added to cart
		Menu theItem = someService.getItem(theId, name);
		System.out.println(theItem.getName() + " " + theItem.getId());
		//get the list from session and add theItem to it
		
		ArrayList<Menu> theList1 = (ArrayList<Menu>) theSession.getAttribute("array");
		theList1.add(theItem);
		
		return "redirect:/";
	}
	
}
