package com.cjoop.cors.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcAutoConfiguration extends WebMvcConfigurerAdapter{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedHeaders("x-requested-with","x-auth-token","content-type")
		.maxAge(3600)
		.allowedOrigins("*")
		.allowCredentials(true);
	}
}
