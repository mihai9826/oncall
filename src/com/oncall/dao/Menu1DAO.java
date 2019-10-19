package com.oncall.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.oncall.entity.Menu;
import com.oncall.entity.Menu1;
import com.oncall.entity.Menu2;
import com.oncall.entity.OrderInfo;
import com.oncall.entity.UserDetails;
import com.oncall.entity.UserRegistration;

public interface Menu1DAO {
	
	public List<Menu1> getMenu1();
	
	public List<Menu2> getMenu2();

	public Menu getItem(int theId, String name);

	public void createTable(String sessionId, List<Menu> theList, HttpSession theSession,
			String userName);

	public void getOrder(String orderName);

	public void deleteOrder(String orderId);

	public void saveUser(UserRegistration theUser);

	public void updateAuthorities(UserRegistration theUser);

	public UserRegistration verifyUserExistence(UserRegistration theUser);

	public UserDetails getUserDetails(String userName);

	public ArrayList<OrderInfo> getOrdersList();

}
