package com.cjoop.cors.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import com.cjoop.cors.vo.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("authenticationFailureHandler")
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler{
	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		WebUtils.setSessionAttribute(request, WebAttributes.AUTHENTICATION_EXCEPTION, exception);
		Message message = new Message();
		message.setCode(-500);
		message.setText(exception.getMessage());
		response.setCharacterEncoding("UTF-8");
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		try (PrintWriter out = response.getWriter()) {
			out.append(objectMapper.writeValueAsString(message));
		}
	}

}
