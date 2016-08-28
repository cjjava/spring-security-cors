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
		String loginProcessingUrl = "/j_spring_security_check";
		http.cors();//主要是这行代码完成了跨域认证的支持
		http.csrf().disable()
		.authorizeRequests()
			.antMatchers("/index.html", "/index.js","/","/favicon.ico").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.loginProcessingUrl(loginProcessingUrl)
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler)
			.permitAll()
			.and()
		.logout().logoutSuccessHandler(logoutSuccessHandler)
			.permitAll();
	}
	
}
