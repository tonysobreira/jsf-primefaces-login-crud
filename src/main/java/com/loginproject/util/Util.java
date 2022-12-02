package com.loginproject.util;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.loginproject.model.Users;
import com.loginproject.repository.UserRepository;

public class Util {

	@Inject
	private static UserRepository userRepository;

	public static Users getLoggedUser() {
		HttpSession session = SessionUtils.getSession();
		Users user = (Users) session.getAttribute("user");
		user = userRepository.findUserById(user.getId());
		return user;
	}

}
