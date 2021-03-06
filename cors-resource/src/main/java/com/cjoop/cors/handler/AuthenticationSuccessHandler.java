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
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.cjoop.cors.vo.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		String username = authentication.getName();
		Message message = new Message();
		message.setText("认证成功");
		message.setData(request.getSession().getId());
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		try (PrintWriter out = response.getWriter()) {
			out.append(objectMapper.writeValueAsString(message));
		}
		logger.info("username:" + username + " authentication success");
	}
}
