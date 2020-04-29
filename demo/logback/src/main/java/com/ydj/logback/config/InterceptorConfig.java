package com.ydj.logback.config;

import com.ydj.logback.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(getInterceptor()).addPathPatterns("/**");
	}

	private RequestInterceptor getInterceptor() {
		return new RequestInterceptor();
	}
}
