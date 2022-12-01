package com.loginproject.bean;

import java.io.Serializable;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import com.loginproject.model.Users;
import com.loginproject.service.LoginService;
import com.loginproject.service.UserService;
import com.loginproject.util.SessionUtils;

@Named
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LoginService loginService;

	@Inject
	private UserService userService;

	private String name;
	private String email;
	private String password;
	
	private String name2;
	private String email2;
	private String password2;

	private String message;
	
	@PostConstruct
	private void init() {
		setName2("admin");
		setEmail2("admin@admin.com");
		setPassword2("admin");
	}

	// validate login
	public String validateUsernamePassword() {
		Users user = loginService.validate(name, password);
		if (Objects.nonNull(user)) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", user.getName());
			session.setAttribute("user", user);
			return "admin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Password", "Please enter correct Username and Password"));
			return "login";
		}
	}

	// logout event, invalidate session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}

	public String registerUsernamePassword() {
		Users user = new Users();
		user.setName(this.name2);
		user.setEmail(this.email2);
		user.setPassword(this.password2);
		userService.save(user);
		return "login";
	}
	
	public Users getLoggedUser() {
		HttpSession session = SessionUtils.getSession();
		Users user = (Users) session.getAttribute("user");
		return user;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}