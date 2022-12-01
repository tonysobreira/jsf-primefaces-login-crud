package com.loginproject.repository;

import javax.inject.Inject;
import javax.persistence.EntityManager;

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

}
