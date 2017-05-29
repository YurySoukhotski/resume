package com.senla.hotel.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "my_rest_api";
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		/***
		 * ----/edit : Operation for Admin ( put delete post ) 
		 * ----/list : for Admin and User
		 * --- online/ - for all
		 * -- order/ preorder - User and Admin
		 */
		http.
		anonymous().disable()
		.requestMatchers().and()
		.authorizeRequests()
		.antMatchers("/guesttoken/").access("hasRole('admin')")
		.antMatchers("/authorization/").access("hasRole('admin') or hasRole('user')")
		/*
		.antMatchers("/service/**").access("hasRole('admin')")
		.antMatchers("/service/list/").access("hasRole('admin') or hasRole('user')")
		
		.antMatchers("/room/**").access("hasRole('admin')")
		.antMatchers("/room/list/").access("hasRole('admin') or hasRole('user')")
		
		.antMatchers("/preorder/**").access("hasRole('admin') or hasRole('user')")
		
		.antMatchers("/onlinebooking").permitAll()*/
		
		.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

}