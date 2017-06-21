package com.cjoop.cors.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.cjoop.cors.handler.AuthenticationFailureHandler;
import com.cjoop.cors.handler.AuthenticationSuccessHandler;
import com.cjoop.cors.handler.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SpringSecurityAutoConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	@Qualifier("authenticationSuccessHandler")
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	@Qualifier("authenticationFailureHandler")
	private AuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	@Qualifier("logoutSuccessHandler")
	private LogoutSuccessHandler logoutSuccessHandler;

	/**
	 * 设置表单登陆信息
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors();
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/index.html", "/index.js","/","/favicon.ico").permitAll()
			.anyRequest().authenticated();
		
		http.formLogin()
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
			.permitAll();
		
		http.logout().logoutSuccessHandler(logoutSuccessHandler)
			.permitAll();
	}
	
}
