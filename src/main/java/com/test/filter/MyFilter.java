package com.test.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.springframework.stereotype.Component;

@Component
@WebFilter(urlPatterns = "/login/*",filterName = "myFilter")
public class MyFilter implements Filter{

	@Override
	public void destroy() {
		System.out.println("----- destroy filter ------");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("----- doFilter ------");
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("----init filter-----");
		
	}

}
