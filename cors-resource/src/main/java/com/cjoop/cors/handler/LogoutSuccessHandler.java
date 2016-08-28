package com.cjoop.cors.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.cjoop.cors.vo.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
@Component("logoutSuccessHandler")
public class LogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;
	
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Message message = new Message();
		message.setText("登出成功");
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		try (PrintWriter out = response.getWriter()) {
			out.append(objectMapper.writeValueAsString(message));
		}
	}

}
