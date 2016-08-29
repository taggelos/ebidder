package jaxb;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import entities.Bid;
import entities.Category;
import entities.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Item")
public class ItemXml implements Serializable {
	private static final long serialVersionUID = 1L;

	private Item item = new Item();
	
	private String name;
	
	private String description;
	
	//category 
	private List<Category> categories = new ArrayList<>();
	
	private BidsXml bids;

	private int currently;


	private int buy_Price;
	
	private int first_Bid;
	
	private int number_of_Bids;
	
	//bids

	//private int sellerID;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date ends;

	
	@Temporal(TemporalType.TIMESTAMP)
	private Date started;
	

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemID;

	//bid //bidder //userID //rating //location - country
	
	//seller //userID -rating
	private SellerXml seller = new SellerXml();
	
	
	
	///////////////////////////////////////////////////////////////////////setters getters 
	

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name="Name")
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}


	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name="Description")
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name="Currently")
	public int getCurrently() {
		return currently;
	}


	public void setCurrently(int currently) {
		this.currently = currently;
	}

	@XmlElement(name="Buy_Price")
	public int getBuy_Price() {
		return buy_Price;
	}


	public void setBuy_Price(int buy_Price) {
		this.buy_Price = buy_Price;
	}

	@XmlElement(name="First_Bid")
	public int getFirst_Bid() {
		return first_Bid;
	}


	public void setFirst_Bid(int first_Bid) {
		this.first_Bid = first_Bid;
	}

	@XmlElement(name="Number_of_Bids")
	public int getNumber_of_Bids() {
		return number_of_Bids;
	}


	public void setNumber_of_Bids(int number_of_Bids) {
		this.number_of_Bids = number_of_Bids;
	}


	@XmlElement(name="Ends")
	public Date getEnds() {
		return ends;
	}


	public void setEnds(Date ends) {
		this.ends = ends;
	}


	@XmlElement(name="Started")
	public Date getStarted() {
		return started;
	}


	public void setStarted(Date started) {
		this.started = started;
	}

	@XmlAttribute(name="ItemID")
	public int getItemID() {
		return itemID;
	}


	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	@XmlElement(name="Bids")
	public BidsXml getBids() {
		return bids;
	}


	public void setBids(BidsXml bids) {
		this.bids = bids;
	}

	@XmlElement(name="Seller")
	public SellerXml getSeller() {
		return seller;
	}


	public void setSeller(SellerXml seller) {
		this.seller = seller;
	}


	/*@Override
	public String toString() {
		return "Item \n" + (name != null ? "name=" + name + ", " : "") + (description != null ? "description=" + description : "") + "]";
	}*/
	
	@XmlElement(name= "Category")
	public List<String> getCategories() {
		List<String> category = new ArrayList<>();
		for (Category c: categories)
        {            
            category.add(c.getName());
        }
 		return category;
	}


	public void setCategories(List<String> category) {
		List<Category> categories = new ArrayList<>();
		for (String s : category){
			Category cat = new Category();
			cat.setName(s);
			categories.add(cat);
		}
		this.categories = categories;
	}
	
	public void setItem(){
		item.setName(name);
		item.setDescription(description);
		item.setItemID(itemID);
		item.setCategories(categories);
		item.setBids(bidXmlToBid(bids.getBids()));
		item.setUser(seller.getSeller());
		
		item.setAmount(0);
		item.setBuy_Price(0);
		item.setCountry("");
		item.setEnds(ends);
		item.setLatitude(0);
		item.setLongitude(0);
		item.setStarted(started);
		item.setNumber_of_Bids(0);

		
		System.out.println("ropalooooo");
		System.out.println(item.getName());
	}
	
	public Item getItem(){
		return item;
	}
	
	public List<Bid> bidXmlToBid(List<BidXml> bidxml){
		List<Bid> bids = new ArrayList<>();
		for (BidXml b: bidxml)
        {            
            bids.add(b.getBid());
        }
 		return bids;
	}
	
	@Override
	public String toString() {
		String str;
		str ="User ["
				+ "\n" + (name != null ? "name=" + name + ", " : "")
				+ "\n" + (description != null ? "description=" + description
						+  ", " : "")
				+ "\n" + (itemID != 0 ? "ItemID=" + itemID + ", " : "") + "]";
		for(Category c : categories){
			str += "\n" + c.getName() + "\n";
		}
		for (BidXml bid : bids.getBids()){
			str += "\n" + bid.getAmount() + "\n";
			str += "\n" + bid.getTime() + "\n";
			str += "\n" + bid.getBidder().getUserID() + "\n";
			str += "\n" + bid.getBidder().getRating() + "\n";
			str += "\n" + bid.getBidder().getLocation() + "\n";
			str += "\n" + bid.getBidder().getCountry() + "\n";
		}
		return str;
	}
}