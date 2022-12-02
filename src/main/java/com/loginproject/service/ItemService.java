package com.loginproject.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import com.loginproject.model.Item;
import com.loginproject.model.Users;
import com.loginproject.repository.ItemRepository;
import com.loginproject.repository.UserRepository;
import com.loginproject.util.SessionUtils;
import com.loginproject.util.jpa.Transactional;

public class ItemService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private ItemRepository itemRepository;
	
	@Inject
	private UserRepository userRepository;

	@Transactional
	public Item save(Item Item) {
		return this.itemRepository.save(Item);
	}
	
	public List<Item> findAll() {
		return this.itemRepository.findAll();
	}

	@Transactional
	public void remove(Item Item) {
		this.itemRepository.remove(Item);
	}

	public Item find(Long id) {
		return this.itemRepository.find(id);
	}
	
	public List<Item> findItemsByUser() {
		HttpSession session = SessionUtils.getSession();
		Users user = (Users) session.getAttribute("user");
		user = userRepository.findUserById(user.getId());
		
		return this.itemRepository.findItemsByUserId(user.getId());
	}
	
	
	@Transactional
	public void saveItemUser(Item item) {
		HttpSession session = SessionUtils.getSession();
		Users user = (Users) session.getAttribute("user");
//		user = userRepository.find(user.getId());
		user = userRepository.findUserById(user.getId());
		
		item.setUser(user);
//		Item i = itemRepository.save(item);
		
//		user.getItems().add(i);
		user.getItems().add(item);
		userRepository.save(user);
	}

	public List<Item> findAllByUser() {
		HttpSession session = SessionUtils.getSession();
		Users user = (Users) session.getAttribute("user");
//		user = userRepository.find(user.getId());
		user = userRepository.findUserById(user.getId());
		
		return user.getItems();
	}

}
