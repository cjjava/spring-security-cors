package com.cjoop.cors;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.cjoop.cors.session.CorsHttpSessionStrategy;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ResourceApplication {
	
	@Bean(name="objectMapper")
	public ObjectMapper objectMapper(){
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setTimeZone(TimeZone.getDefault());
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		return objectMapper;
	}
	
	@Bean(name="httpSessionStrategy")
	public HttpSessionStrategy sessionStrategy(){
		return new CorsHttpSessionStrategy();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
	}
	
}
