package com.oncall.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.oncall.dao.Menu1DAO;
import com.oncall.entity.Menu;
import com.oncall.entity.Menu1;
import com.oncall.entity.Menu2;
import com.oncall.entity.OrderInfo;
import com.oncall.entity.OrderedItem;
import com.oncall.entity.UserDetails;
import com.oncall.entity.UserRegistration;

@Service
public class SomeServiceImpl implements SomeService {
	
	
	//inject the Menu1DAO
	@Autowired
	private Menu1DAO menu1DAO;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@Autowired
	private VelocityEngine velocityEngine;
	

	@Override
	public List<Menu1> getMenu1() {
		return menu1DAO.getMenu1();
	}

	@Override
	public List<Menu2> getMenu2() {
		return menu1DAO.getMenu2();
	}

	@Override
	public Menu getItem(int theId, String name) {
		
		return menu1DAO.getItem(theId, name);
	}

	@Override
	public void createTable(String sessionId, List<Menu> theList, HttpSession theSession,
			String userName) {
		
		menu1DAO.createTable(sessionId, theList, theSession, userName);
		
	}

	@Override
	public void getOrder(String orderName) {
		
		menu1DAO.getOrder(orderName);
		
	}

	@Override
	public void deleteOrder(String orderId) {
		
		menu1DAO.deleteOrder(orderId);
		
	}

	@Override
	public void saveUser(UserRegistration theUser) {
		
		menu1DAO.saveUser(theUser);
		
	}

	@Override
	public void updateAuthorities(UserRegistration theUser) {
		
		menu1DAO.updateAuthorities(theUser);
		
	}

	@Override
	public UserRegistration verifyUserExistence(UserRegistration theUser) {
		
		return menu1DAO.verifyUserExistence(theUser);
	}

	@Override
	public UserDetails getUserDetails(String userName) {
		
		return menu1DAO.getUserDetails(userName);
	}

	@Override
	public ArrayList<OrderInfo> getOrdersList() {
		
		return menu1DAO.getOrdersList();
	}

	@Override
	public void sendMail(ArrayList<OrderedItem> list, UserDetails userDetails) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                message.setTo(userDetails.getUserName());
                message.setFrom("webmaster@oncall.com");
                message.setSubject("Order info");
                Map model = new HashMap();
                model.put("list", list);
                model.put("user", userDetails);
                @SuppressWarnings("deprecation")
				String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, "templates/send.vm", model);
                message.setText(text, true);
            }
        };
       mailSender.send(preparator);
	}

}
