package com.loginproject.repository;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.loginproject.model.Users;
import com.loginproject.util.AESUtil;

public class LoginRepository implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	public Users findUserByNameAndPassword(String name, String password) {
		
		try {
			password = AESUtil.encrypt(password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT u FROM Users u ");
			sql.append(" WHERE u.name = :name AND u.password = :password ");
			
			Query query = this.manager.createQuery(sql.toString());
			
			query.setParameter("name", name);
			query.setParameter("password", password);
			
			Users user = (Users) query.getSingleResult();
			
			return user;
		} catch (Exception ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return null;
		} finally {
//			DataConnect.close(con);
		}
		
	}
	
}
