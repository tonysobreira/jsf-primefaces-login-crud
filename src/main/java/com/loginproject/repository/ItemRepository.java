package com.loginproject.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.loginproject.model.Item;

public class ItemRepository extends AbstractPersistence<Item, Long> {

	@Inject
	private EntityManager manager;
	
	public ItemRepository() {
		super(Item.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return manager;
	}
	
	public List<Item> findItemsByUserId(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT i FROM Item i ");
		sql.append(" LEFT JOIN FETCH i.user u ");
		sql.append(" WHERE u.id = :id ");
		
		Query query = manager.createQuery(sql.toString());
		
		query.setParameter("id", id);
		
		return query.getResultList();
	}

}
