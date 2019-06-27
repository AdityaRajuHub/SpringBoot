package com.example.rest.jpa.demo.filter;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

@Component
public class CustomFilter implements Filter {
	
	

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		
		HttpServletRequest httpReq= (HttpServletRequest) req;
		AuthenticationPrincipal principal= (AuthenticationPrincipal)httpReq.getUserPrincipal();
		System.out.println("Principal:::::::::"+principal);
		
		filterChain.doFilter(req, res);
	}

}
