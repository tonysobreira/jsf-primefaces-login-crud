package com.loginproject.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.loginproject.model.Users;

public class UserRepository extends AbstractPersistence<Users, Long> {

	@Inject
	private EntityManager manager;
	
	public UserRepository() {
		super(Users.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return manager;
	}
	
	public Users findByUsername(String username) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT u FROM Users u ");
		sql.append(" WHERE u.name = :username");
		
		Query query = manager.createQuery(sql.toString());
		
		query.setParameter("username", username);
		
		return (Users) query.getSingleResult();
	}

	public Users findUserById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT u FROM Users u ");
		sql.append(" LEFT JOIN FETCH u.items i ");
		sql.append(" WHERE u.id = :id");
		
		Query query = manager.createQuery(sql.toString());
		
		query.setParameter("id", id);
		
		return (Users) query.getSingleResult();
	}

}
