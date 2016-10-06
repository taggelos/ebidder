package ui;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import db.BidDAO;
import db.ItemDAO;
import entities.Bid;
import entities.Item;

@ManagedBean(name = "item_list")
@SessionScoped
public class ItemListBean {

	private List<Item> items_list;
    @PostConstruct
    public void init(){
    	items_list = new ArrayList<Item>();
    	items_list= itemDAO.getItems("All",0,0);
    	selected_field="all";
    }

	private String search_value;
	private String selected_field;
	private Item item_for_details;
	private float sub_value_bid;

	@ManagedProperty(value = "#{itemDAO}")
	private ItemDAO itemDAO;

	@ManagedProperty(value = "#{user}")
	private UserBean my_user;

	@ManagedProperty(value = "#{bidDAO}")
	private BidDAO bidDAO;
	
	private int first=0;
	
	private int rows=10;
	
	
// Functions
	public String search()
	{
		first=0;
		getItems_list();
		return null;
	}

	public String submit_bid() {
		Date datenow = new Timestamp(System.currentTimeMillis());
		if(datenow.compareTo(item_for_details.getEnds())<=0 && datenow.compareTo(item_for_details.getStarted())>0 ){
			if (item_for_details.getCurrently() == 0 && item_for_details.getFirst_Bid() > sub_value_bid) {
				// message for more money
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successful"));
				return null;
			}
			if (item_for_details.getCurrently() >= sub_value_bid) {
				// message for more money
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Throw More Brah"));
				return null;
			}
	
			List<Bid> temp = item_for_details.getBids();
			boolean flag = false;
			for (int i = 0; i < temp.size(); i++) {
				Bid temp_bid = temp.get(i);
				if (temp_bid.getBidder().getUser() == my_user.getCurrent() ) {////////////////////changes
					temp_bid.setAmount(sub_value_bid);
					temp_bid.setTime(new Timestamp(System.currentTimeMillis()));
					System.out.println(temp_bid.getBidder().getUserUsername());
					bidDAO.update(temp_bid);
					flag = true;
				}
			}
			if (flag == false) {
				Bid new_bid = new Bid();
				new_bid.setItem(item_for_details);
				new_bid.setBidder(my_user.getCurrent().getBidder());////////////////////changes
				new_bid.setAmount(sub_value_bid);
				new_bid.setTime(new Timestamp(System.currentTimeMillis()));
				temp.add(new_bid);
				item_for_details.setBids(temp);
			}
			
			item_for_details.setNumber_of_Bids(item_for_details.getNumber_of_Bids() + 1);
			item_for_details.setCurrently(sub_value_bid);
			if (item_for_details.getBuy_Price() != 0.0f && sub_value_bid >= item_for_details.getBuy_Price()) { // anti !=null otan htan float
				// Item sold;
				item_for_details.setEnds(new Timestamp(System.currentTimeMillis()));  //end time will be the current time 
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Auction just finished, no need for bids"));
				//message ?
				//TODO
			}
			itemDAO.update(item_for_details);
			return null;
		}
		if (datenow.compareTo(item_for_details.getEnds())<=0 )	
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Auction is finished, no need for bids"));
		if (datenow.compareTo(item_for_details.getStarted())>0 )
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Auction hasn't started yet, no need for bids"));
		return null;
	}

// Getters and Setters
	
	
	public String getSearch_value() {
		return search_value;
	}

	public void setSearch_value(String search_value) {
		this.search_value = search_value;
	}
	
	public String getSelected_field() {
		return selected_field;
	}

	public void setSelected_field(String selected_field) {
		this.selected_field = selected_field;
	}
	
	public List<Item> getItems_list() {
		items_list=itemDAO.search(selected_field,search_value,first,rows+1);
		//items_list = itemDAO.getItems("All",first,rows+1);
		return items_list;
	}

	public void setItems_list(List<Item> all_my_items) {
		
		this.items_list = all_my_items;
	}

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	public BidDAO getBidDAO() {
		return bidDAO;
	}

	public void setBidDAO(BidDAO bidDAO) {
		this.bidDAO = bidDAO;
	}

	public String details() {
		return "/html/details";
	}

	public UserBean getMy_user() {
		return my_user;
	}

	public void setMy_user(UserBean my_user) {
		this.my_user = my_user;
	}

	public Item getItem_for_details() {
		return item_for_details;
	}

	public void setItem_for_details(Item item_for_details) {
		this.item_for_details = item_for_details;
	}

	public float getSub_value_bid() {
		return sub_value_bid;
	}

	public void setSub_value_bid(float sub_value_bid) {
		this.sub_value_bid = sub_value_bid;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public String display(String action){
		if(action.equals("next") ){
			first += rows;
		}
		else{
			first -= rows;
		}
		getItems_list();
		return null;
	}
	
	public boolean getNext(){
		//return (userList.size() <= (first + rows));
		return items_list.size() <= rows;
	}
	
	public boolean getPrev(){
		return first==0;
	}
	
	
}
