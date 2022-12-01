package com.loginproject.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.loginproject.model.Users;
import com.loginproject.repository.UserRepository;
import com.loginproject.util.AESUtil;

public class UserService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UserRepository userRepository;

	public Users save(Users user) {
		try {
			user.setPassword(AESUtil.encrypt(user.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.userRepository.save(user);
	}

	public List<Users> findAll() {
		return this.userRepository.findAll();
	}

	public void remove(Users user) {
		this.userRepository.remove(user);
	}

	public Users find(Long id) {
		return this.userRepository.find(id);
	}
	
	public Users findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}

}
