package com.senla.hotel.configuration;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {
	 public CorsFilter (){
	 }

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req; 
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, HEAD, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "36000");
		response.setHeader("Access-Control-Allow-Headers","Origin, Accept, X-Requested-With, User-Agent, Authorization, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
		   if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
	             response.setStatus(HttpServletResponse.SC_OK);
	         } else {
	             chain.doFilter(req, res);
	         }
	}
	
	
	public void init(FilterConfig filterConfig) {}

	public void destroy() {}

}