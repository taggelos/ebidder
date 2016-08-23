package ui;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import db.ItemDAO;
import entities.Item;

@ManagedBean(name="item_list")
@RequestScoped
public class ItemListBean {
	
	private  List<Item> items_list = new ArrayList<Item>();;
	
	private Item item_for_details;

	@ManagedProperty(value="#{itemDAO}")
    private ItemDAO itemDAO;
	
	public String details()
	{
		return "/restricted/details";
	}
	
	public List<Item> getItems_list() {
		items_list=itemDAO.getItems("All");
		return items_list;
	}

	public void setItems_list(List<Item> all_my_items) {
		this.items_list = all_my_items;
	}
	
	/*   
    public String edit_item()
    {
    	items_list=itemDAO.getItems("All");
    	return null;
    }
	
    /*
    public String delete_item()
    {
    	System.out.println(itemDAO);
    	System.out.println(item_for_delete);

    	itemDAO.remove(item_for_delete);
    	return null;
    }
	*/
	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	/*
	public Item getItem_for_edit() {
		return item_for_edit;
	}

	public void setItem_for_edit(Item item_for_edit) {
		this.item_for_edit = item_for_edit;
	}

	public Item getItem_for_delete() {
		return item_for_delete;
	}

	public void setItem_for_delete(Item item_for_delete) {
		this.item_for_delete = item_for_delete;
	}
	*/
    

	public Item getItem_for_details() {
		return item_for_details;
	}

	public void setItem_for_details(Item item_for_details) {
		this.item_for_details = item_for_details;
	}

}