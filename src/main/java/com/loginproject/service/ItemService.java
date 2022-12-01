package com.loginproject.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import com.loginproject.model.Item;
import com.loginproject.repository.ItemRepository;

public class ItemService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ItemRepository itemRepository;

	public Item save(Item Item) {
		return this.itemRepository.save(Item);
	}

	public List<Item> findAll() {
		return this.itemRepository.findAll();
	}

	public void remove(Item Item) {
		this.itemRepository.remove(Item);
	}

	public Item find(Long id) {
		return this.itemRepository.find(id);
	}

}
