package com.cjoop.cors.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcAutoConfiguration extends WebMvcConfigurerAdapter{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//判断当前运行模式(开发阶段)添加跨域允许
		registry.addMapping("/**")
		.allowedHeaders("x-requested-with","x-auth-token","content-type")
		.maxAge(3600)
		.allowedOrigins("*")
		.allowCredentials(true);
	}
}
