package com.oncall.dao;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.oncall.entity.Menu;
import com.oncall.entity.Menu1;
import com.oncall.entity.Menu2;
import com.oncall.entity.OrderInfo;
import com.oncall.entity.OrderedItem;
import com.oncall.entity.UserDetails;
import com.oncall.entity.UserRegistration;

@Repository
public class Menu1DAOImpl implements Menu1DAO {
	
	//injecting the session factory
	@Autowired
	@Qualifier("sessionFactory1")
	private SessionFactory sessionFactory1;
	
	@Autowired
	@Qualifier("sessionFactory2")
	private SessionFactory sessionFactory2;
	
	private String[] re;
	private int x;
	
	@Override
	public List<Menu1> getMenu1() {
		
		//geting the current hibernate session
		Session currentSession = sessionFactory1.getCurrentSession();

		//create a query..sort by name
		Query<Menu1> theQuery = 
				currentSession.createQuery("from Menu1 order by name",
						Menu1.class);
		
		//execute query and get result list
		List<Menu1> menu1 = theQuery.getResultList();
		
		return menu1;
	}

	@Override
	public List<Menu2> getMenu2() {
		
		//geting the current hibernate session
		Session currentSession = sessionFactory1.getCurrentSession();
		
		//create a query..sort by name
		Query<Menu2> theQuery = 
			currentSession.createQuery("from Menu2 order by name",
						Menu2.class);
				
		//execute query and get result list
		List<Menu2> menu2 = theQuery.getResultList();	
			
		return menu2;
		
	}
	
	@Override
	public Menu getItem(int theId, String name) {
		
		//getting the current hibernate session
		Session currentSession = sessionFactory1.getCurrentSession();
		
		//read the object from the DB
		
		if(name.contentEquals("ciorbe_supe")) {
			
			Menu item = (Menu1) currentSession.get(Menu1.class, theId);

			return item;
		}
		if(name.contentEquals("specialitati")) {
			
			Menu item = (Menu2) currentSession.get(Menu2.class, theId);
		
			return item;
		}
		
		return null;
	}

	
	@Override
	public void createTable(String sessionId, List<Menu> theList, HttpSession theSession,
			String userName) {
		
		//geting the current hibernate session
		Session currentSession = sessionFactory2.getCurrentSession();
	
		re = (java.lang.String[]) theSession.getAttribute("req");
	
		//create the Sqlquery
		
		NativeQuery<String> theQuery = currentSession.createNativeQuery("CREATE TABLE "+sessionId+" SELECT * FROM checkout_mockup LIMIT 0");
		
		theQuery.executeUpdate();
		
		//insert the list elements into the table
		
		for(int i = 0; i < theList.size(); i ++) {
			
			Menu item = theList.get(i);
			
			if(re != null) {
				x = Integer.parseInt(re[i]);
			}
			else
				x = 1;
			
			NativeQuery<String> query = currentSession.createNativeQuery("INSERT INTO "+sessionId+" (id, nume, cantitate, pret, portii, username) VALUES (?1, ?2, ?3, ?4, ?5, ?6)");
			
			query.setParameter(1, i + 1);
			query.setParameter(2, item.getName());
			query.setParameter(3, item.getWeight());
			query.setParameter(4, item.getPrice());
			query.setParameter(5, x);
			query.setParameter(6, userName);
			
			query.executeUpdate();
		}
		//get totalPrice
		float totalPrice = (float) theSession.getAttribute("total");
		NativeQuery<String> query2 = currentSession.createNativeQuery("INSERT INTO "+sessionId+" (id, nume, cantitate, pret, portii, username) VALUES (?1, ?2, ?3, ?4, ?5, ?6)");
		
		query2.setParameter(1, theList.size() + 1);
		query2.setParameter(2, "total price");
		query2.setParameter(3, 0);
		query2.setParameter(4, totalPrice);
		query2.setParameter(5, 0);
		query2.setParameter(6, userName);
		
		query2.executeUpdate();
	
	}

	@Override
	public void getOrder(String orderName) {
		
		Session currentSession = sessionFactory2.getCurrentSession();
		
		String theQuery = "SELECT * FROM users_orders." + orderName;
		
		Query q = currentSession.createNativeQuery(theQuery, OrderedItem.class);
		
		com.oncall.controller.CookController.theOrder = (ArrayList<OrderedItem>) q.getResultList();
		
	}

	@Override
	public void deleteOrder(String orderId) {
		
		Session currentSession = sessionFactory2.getCurrentSession();
		
		String theQuery = "DROP TABLE users_orders." + orderId;
		
		Query q = currentSession.createNativeQuery(theQuery);
		
		q.executeUpdate();
		
		
	}

	@Override
	public void saveUser(UserRegistration theUser) {
		
		Session currentSession = sessionFactory1.getCurrentSession();
		
		currentSession.saveOrUpdate(theUser);
		
	}

	@Override
	public void updateAuthorities(UserRegistration theUser) {
		
		Session currentSession = sessionFactory1.getCurrentSession();
		
		NativeQuery<String> query = currentSession.createNativeQuery("INSERT INTO authorities (username, authority) VALUES (?1, ?2)");
		
		query.setParameter(1, theUser.getUserName());
		query.setParameter(2, "ROLE_CLIENT");
		
		query.executeUpdate();
		
	}

	@Override
	public UserRegistration verifyUserExistence(UserRegistration theUser) {
		
		UserRegistration check = new UserRegistration();
		
		Session currentSession = sessionFactory1.getCurrentSession();
		
		check = currentSession.get(UserRegistration.class, theUser.getUserName());
		
		return check;
		
	}

	@Override
	public UserDetails getUserDetails(String userName) {
		
		Session currentSession = sessionFactory1.getCurrentSession();
		
		UserDetails details = currentSession.get(UserDetails.class, userName);
		
		return details;
	}

	@Override
	public ArrayList<OrderInfo> getOrdersList() {
		
		Session currentSession = sessionFactory2.getCurrentSession();
		
		Query q = currentSession.createNativeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='users_orders';");
		ArrayList<String> theList = (ArrayList<String>) q.getResultList();
		
		
		ArrayList<OrderInfo> requestedList = new ArrayList<>();
		
		for(int i = 0; i < theList.size(); i ++) {
			if(!(theList.get(i).contains("checkout_mockup"))) {
				OrderInfo orderInfo = new OrderInfo();
				orderInfo.setOrderId(theList.get(i));
				requestedList.add(orderInfo);
			}
		}
		
		return requestedList;
	}
}
