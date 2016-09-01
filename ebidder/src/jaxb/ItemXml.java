package jaxb;



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
public class ItemXml  {

	private Item item = new Item();
	
	private String name;
	
	private String description;
	
	//category 
	private List<Category> categories = new ArrayList<>();
	
	private BidsXml bids;

	private Float currently;

	private Float buy_Price;
	
	private Float first_Bid;
	
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
	@XmlJavaTypeAdapter(PriceAdapterXml.class)
	public Float getCurrently() {
		return currently;
	}


	public void setCurrently(Float currently) {
		this.currently = currently;
	}

	@XmlElement(name="Buy_Price")
	@XmlJavaTypeAdapter(PriceAdapterXml.class)
	public Float getBuy_Price() {
		return buy_Price;
	}


	public void setBuy_Price(Float buy_Price) {
		this.buy_Price = buy_Price;
	}

	@XmlElement(name="First_Bid")
	@XmlJavaTypeAdapter(PriceAdapterXml.class)
	public Float getFirst_Bid() {
		return first_Bid;
	}


	public void setFirst_Bid(Float first_Bid) {
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
	@XmlJavaTypeAdapter(DateAdapterXml.class)
	public Date getEnds() {
		return ends; 
	}


	public void setEnds(Date ends) {
		this.ends = ends;
	}


	@XmlElement(name="Started")
	@XmlJavaTypeAdapter(DateAdapterXml.class)
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
		
		//item.setBuy_Price(Float.valueOf(String.valueOf(buy_Price)));
		item.setBuy_Price(Float.valueOf(buy_Price));
		//item.setCountry(country);
		item.setEnds(ends);
		//item.setLatitude(0);
		//item.setLongitude(0);
		item.setStarted(started);
		item.setNumber_of_Bids(number_of_Bids);
		

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
		str =" Item "
				+ "\n   " + (itemID != 0 ? "ItemID=" + itemID + ", " : "no itemID,") 
				+ "\n   " + (name != null ? "name=" + name + ", " : "no name,")
				+ "\n   " + (description != null ? "description=" + description +  ", " : "no description,")
				+ "\n   " + (started != null ? "started= " + started + ", " : "no started,")
				+ "\n   " + (ends != null ? "ends= " + ends + ", " : "no ends,")
				+ "\n   " + (first_Bid != null ? "first_Bid= " + first_Bid + "$" + ", " : "no first_Bid,")
				+ "\n   " + (currently != null ? "currently= " + currently + "$" + ", " : "no currently,")
				+ "\n   " + (buy_Price != null ? "buy_Price= " + buy_Price + "$" + ", " : "no buy_price,")
				+ "\n   " + (number_of_Bids != 0 ? "number_of_bids = " + number_of_Bids + ", " : "no number_of_Bids," );
		for(Category c : categories){
			str += "\n   " + (c.getName()!=null ? "category =  " + c.getName() + ", " : "no categories,")  ;
		}
		str+= "\n   " + (seller != null ?  seller : "") ;
		str+=(bids != null ?  bids : "") ;
		//alternative printing
		/* 
		for (BidXml bid : bids.getBids()){  
			str += "\n " + "Amount " + bid.getAmount() + "\n";
			str += "\n " + "Time " + bid.getTime() + "\n";
			str += "\n " + "UserID " + bid.getBidder().getUserID() + "\n";
			str += "\n " + "Rating " + bid.getBidder().getRating() + "\n";
			str += "\n " + "Location " + bid.getBidder().getLocation() + "\n";
			str += "\n " + "Country " + bid.getBidder().getCountry() + "\n";
			//str+= bid;
		}*/
		return str;
	}
}