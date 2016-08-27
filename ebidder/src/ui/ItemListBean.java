package ui;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import db.BidDAO;
import db.ItemDAO;
import entities.Bid;
import entities.Item;

@ManagedBean(name="item_list")
@SessionScoped
public class ItemListBean {
	
	private  List<Item> items_list = new ArrayList<Item>();;
	
	private Item item_for_details;
	private float sub_value_bid;

	@ManagedProperty(value="#{itemDAO}")
    private ItemDAO itemDAO;
	
    @ManagedProperty(value="#{user}")
    private UserBean my_user;
    
	@ManagedProperty(value="#{bidDAO}")
    private BidDAO bidDAO;	
	
    public String submit_bid()
    {
    	if ( item_for_details.getCurrently()==0 && item_for_details.getFirst_Bid()>sub_value_bid )
    	{
    		// message for more money
    		return null;
    	}
    	if ( item_for_details.getCurrently()>= sub_value_bid )
    	{
    		// message for more money
    		return null;
    	}
    	
		List<Bid> temp=item_for_details.getBids();
		boolean flag=false;
		for (int i=0; i<temp.size();i++)
		{
			Bid temp_bid=temp.get(i);
			if (temp_bid.getUser()==my_user.getCurrent())
			{		
				temp_bid.setAmount(sub_value_bid);
				temp_bid.setTime(new Timestamp(System.currentTimeMillis()));
				bidDAO.update(temp_bid);
				flag=true;
			}
		}
		if(flag==false)
		{
	    	Bid new_bid=new Bid();    	
	    	new_bid.setItem(item_for_details);
	    	new_bid.setUser(my_user.getCurrent()); 
	    	new_bid.setAmount(sub_value_bid);
	    	new_bid.setTime(new Timestamp(System.currentTimeMillis()));
	    	temp.add(new_bid);
	    	item_for_details.setBids(temp);
		}
    	
    	item_for_details.setNumber_of_Bids( item_for_details.getNumber_of_Bids()+1 );
    	item_for_details.setCurrently(sub_value_bid);    	
    	itemDAO.update(item_for_details);
    	
    	if( item_for_details.getBuy_Price()!= 0 && sub_value_bid>=item_for_details.getBuy_Price())
    	{
    		// Item sold;
    	}
    	
    	return null;
    }
	
	public List<Item> getItems_list() {
		items_list=itemDAO.getItems("All");
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

	public String details()
	{
		return "/restricted/details";
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
		
	  public String foo(){
		    System.out.println(sub_value_bid);
		    return "SUCCESS";
		  }
	
	
}


