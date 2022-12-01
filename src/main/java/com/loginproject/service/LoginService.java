package com.loginproject.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.loginproject.model.Users;
import com.loginproject.repository.LoginRepository;

public class LoginService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LoginRepository loginRepository;

	public Users validate(String name, String password) {
		return this.loginRepository.findUserByNameAndPassword(name, password);
	}
	
}
