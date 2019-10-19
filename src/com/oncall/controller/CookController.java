package com.oncall.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oncall.entity.*;
import com.oncall.service.SomeService;


@Controller
@RequestMapping("/cook")
public class CookController {
	
	@Autowired
	private SomeService someService;
	
	public static ArrayList<OrderInfo> theOrders;
	public static ArrayList<OrderedItem> theOrder;
	private static ArrayList<ArrayList<OrderedItem>> theList;
	private String ordername;
	
	@GetMapping("/")
	public String showTheOrders(Model theModel) {
		
		theList = new ArrayList<ArrayList<OrderedItem>>();
		
		theOrders = someService.getOrdersList();
		
		theModel.addAttribute("orderList", theOrders);
		
		for(int i = 0; i < theOrders.size(); i ++) {
				
			theOrder = new ArrayList<>();
			
			ordername = theOrders.get(i).getOrderId();
			
			someService.getOrder(ordername);
			
			theList.add(i, theOrder);
			
			OrderedItem item = theOrder.get(0);
			
			UserDetails userDetails = someService.getUserDetails(item.getUserName());
			
			theOrders.get(i).setDetails(userDetails);
			
			
		} 
		theModel.addAttribute("orders", theList);
		return "cook";
	}
	
	@GetMapping("/delivered")
	public String deleteOrder(@RequestParam("orderId") String orderId, @RequestParam("orderIndex") int index) {
		
		UserDetails userDetails = theOrders.get(index).getDetails();
		
		//someService.sendMail(theList.get(index), userDetails);
		
		someService.deleteOrder(orderId);
		
		theOrders.remove(index);
		
		
		
		return "redirect:/cook/";
	}
	
	

}
