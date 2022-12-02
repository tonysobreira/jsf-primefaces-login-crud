package com.loginproject.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.loginproject.service.UserService;

@Named
@ApplicationScoped
public class SessionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private UserService userService;
	
	public void start() {
		System.out.println("Started....");
	}

	@PostConstruct
	private void init() {
		System.out.println(userService.findAll());
	}
	

}
