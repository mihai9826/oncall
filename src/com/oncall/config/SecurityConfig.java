package com.oncall.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("dataSource1")
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource);
		
		
	}

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()
			.loginPage("/showLoginPage")
			.loginProcessingUrl("/authenticateTheUser")
			.permitAll();
		
	}*/
	
	/*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//add our users for in memory authentication
		
		@SuppressWarnings("deprecation")
		UserBuilder users = User.withDefaultPasswordEncoder();
		auth.inMemoryAuthentication().withUser(users.username("mihai").password("test123456").roles("EMPLOYEE"))
		.withUser(users.username("mary").password("test123456").roles("MANAGER"))
		.withUser(users.username("susan").password("test123456").roles("ADMIN"));
	
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/checkOut").hasRole("CLIENT")
			.antMatchers("/cook/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/showLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll();
		
	}
}
