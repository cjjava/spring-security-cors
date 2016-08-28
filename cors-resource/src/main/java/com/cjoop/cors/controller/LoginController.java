package com.cjoop.cors.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjoop.cors.vo.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class LoginController {
	@Autowired
	@Qualifier("objectMapper")
	private ObjectMapper objectMapper;
	
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String xmlHttpRequest = request.getHeader("x-requested-with");
		if(xmlHttpRequest!=null){
			Message message = new Message();
			message.setCode(-500);
			message.setText("需要登陆才能够访问资源");
			response.setCharacterEncoding("UTF-8");
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			try (PrintWriter out = response.getWriter()) {
				out.append(objectMapper.writeValueAsString(message));
			}
			return null;
		}
		return "redirect:index.html";
	}
	
}
