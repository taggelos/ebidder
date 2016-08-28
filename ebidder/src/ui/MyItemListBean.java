package ui;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import db.ItemDAO;
import db.UserDAO;
import entities.Item;

@ManagedBean(name="my_item_list")
@ViewScoped
public class MyItemListBean {
	
    @ManagedProperty(value="#{user}")
    private UserBean my_user;

	private  List<Item> all_my_items = new ArrayList<Item>();;
	
	private Item item_for_edit;
	private Item item_for_delete;


	@ManagedProperty(value="#{itemDAO}")
    private ItemDAO itemDAO;
	
    @ManagedProperty(value="#{userDAO}")
    private UserDAO userDAO;

	public List<Item> getAll_my_items() {
		
		all_my_items= itemDAO.getItems("All");  //=userDAO.getItems(my_user.getCurrent());
		return all_my_items;
	}

	public void setAll_my_items(List<Item> all_my_items) {
		this.all_my_items = all_my_items;
	}
	   
    public String edit_item()
    {    	
    	//all_my_items=itemDAO.getItems(A) ;
    	return null;
    }
	
    public String delete_item()
    {
    	itemDAO.remove(item_for_delete);
    	return null;
    }

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
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
	
	public UserBean getMy_user() {
		return my_user;
	}

	public void setMy_user(UserBean my_user) {
		this.my_user = my_user;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
}
