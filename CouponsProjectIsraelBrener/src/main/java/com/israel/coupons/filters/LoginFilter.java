package com.israel.coupons.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import com.israel.coupons.cache.ICacheController;
import com.israel.coupons.consts.Constants;
import com.israel.coupons.data.LoggedInUserData;


@Component
@Order(2)
public class LoginFilter implements Filter{

	@Autowired
	private ICacheController cacheController;

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

//		due to this class is @Order(2) - this "options" condition is redundant 
//		if (httpRequest.getMethod().equals("OPTIONS")) {
//			chain.doFilter(httpRequest, response);
//			return;
//		}

		String url = httpRequest.getRequestURL().toString();

		// Exclude : "Register" & "Login" 
		if(url.endsWith("/register")) {
			chain.doFilter(httpRequest, response);
			return;
		}	
		if(url.endsWith("/login")) {
			chain.doFilter(httpRequest, response);
			return;
		}

		//String token = request.getParameter("token");
		String token = httpRequest.getHeader("Authorization");

		LoggedInUserData loggedInUserData = (LoggedInUserData) cacheController.get(token);
		//		if (cacheController.get(token)!=null) {
		if(loggedInUserData != null) {
			// U're logged in - all is good
			// Move forward to the next filter in chain
			request.setAttribute(Constants.USER_DATA_KEY, loggedInUserData);
			chain.doFilter(request, response);
			return;
		}

		//token is not in cache or token is null - u are not log in
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		int unAuthorizedError = 401;
		httpResponse.setStatus(unAuthorizedError);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
