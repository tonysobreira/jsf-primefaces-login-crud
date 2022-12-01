package com.loginproject.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.loginproject.model.Users;
import com.loginproject.service.LoginService;
import com.loginproject.service.UserService;

@Named
@ApplicationScoped
public class SessionBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
//	@Inject
//	private EntityManager manager;
	
	@Inject
	private UserService userService;
	
	public void start() {
		System.out.println("Started....");
	}

	@PostConstruct
	private void init() {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("LoginPU");
//		EntityManager manager = factory.createEntityManager();
		
//		Connection con = DataConnect.getConnection();
//		PreparedStatement ps;
//		
//		try {
//			ps = con.prepareStatement("CREATE TABLE Users(" 
//					+ "id int(20) NOT NULL, " 
//					+ "name VARCHAR(60) NOT NULL, "
//					+ "email VARCHAR(60) NOT NULL, "
//					+ "password VARCHAR(60) NOT NULL, " 
//					+ "PRIMARY KEY(id))");
//			ps.execute();
//
//			ps = con.prepareStatement("INSERT INTO Users VALUES(2,'admin2','admin2@admin.com','admin2')");
//			ps.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
//		Users user = new Users(null, "admin", "admin@admin", "admin");
//		this.userService.save(user);
//		
//		List<Users> list = this.userService.findAll();
//		
//		System.out.println(list);
//		
//		Users u = this.userService.find(user);
//		System.out.println(u);
		
	}
	

}
