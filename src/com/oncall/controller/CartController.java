package com.oncall.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oncall.entity.Menu;
import com.oncall.entity.OrderInfo;
import com.oncall.service.SomeService;

@Controller
public class CartController {
	
	//injecting the service
	@Autowired
	private SomeService someService;
	
	@RequestMapping("/showCart")
	public String showCart(HttpServletRequest request, Model theModel) {
		
		HttpSession theSession = request.getSession();
		
		String theSessionId = theSession.getId();
		float total = 0;
		ArrayList<Menu> theList1 = (ArrayList<Menu>) theSession.getAttribute("array");
		if(request.getParameter("deleteId") != null) {
			int index = Integer.parseInt(request.getParameter("deleteId"));
			theList1.remove(index);
		}
		if(request.getParameterValues("servings") != null) {
			theSession.setAttribute("req", request.getParameterValues("servings"));
			String[] values = request.getParameterValues("servings");
			for(int i = 0; i < theList1.size(); i ++) {
				total += theList1.get(i).getPrice() * Integer.parseInt(values[i]);
			}
			
		}
		else {
			theSession.setAttribute("req", null);
			for(int i = 0; i < theList1.size(); i ++) {
				total += theList1.get(i).getPrice() * 1;
			}
		}
		theSession.setAttribute("total", total);
		return "cart";
	}
	
	@GetMapping("/checkOut")
	public String checkOut(
			HttpServletRequest request, Model theModel) {
		
		HttpSession theSession = request.getSession();
		
		String userName = request.getRemoteUser();
		
		ArrayList<Menu> theList1 = (ArrayList<Menu>) theSession.getAttribute("array");
		
		int ord = (int) theSession.getAttribute("orders");
		ord ++;
		theSession.setAttribute("orders", ord);
		
		String order = theSession.getId() + Integer.toString(ord);
		
		someService.createTable(order, theList1, theSession, userName);
		
		theList1.clear();
		
		
		return "redirect:/";
	}
}
