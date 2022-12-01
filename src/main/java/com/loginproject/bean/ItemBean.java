package com.loginproject.bean;

import static javax.faces.context.FacesContext.getCurrentInstance;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import com.loginproject.model.Item;
import com.loginproject.service.ItemService;

@Named
@RequestScoped
public class ItemBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ItemService itemService;
	
	@Inject
	private Item item;
	
	private List<Item> items;
	
	private Long selectedId;
	
	public String save() {
		
		try {
			itemService.save(item);
		} catch(Exception ex) {
			addMessage(getMessageFromI18N("msg.error.save.item"), ex.getMessage());
			return "";
		}
		
		return "listItems";
	}
	
	public String delete() {
		
		try {
			itemService.remove(item);
		} catch(Exception ex) {
			addMessage(getMessageFromI18N("msg.error.delete.item"), ex.getMessage());
			return "";
		}
		
		return "listItems";
	}
	
	public void edit() {
		
		if (selectedId == null) {
			return;
		}
		
		item = itemService.find(selectedId);
	}
	
	private String getMessageFromI18N(String key) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages_labels", getCurrentInstance().getViewRoot().getLocale());
		return bundle.getString(key);
	}
	
	private void addMessage(String summary, String detail) {
		getCurrentInstance().addMessage(null, new FacesMessage(summary, summary.concat("<br/>").concat(detail)));
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItems() {
		
		if (items == null) {
			items = itemService.findAll();
		}
		
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Long getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(Long selectedId) {
		this.selectedId = selectedId;
	}
	
	
	

}
