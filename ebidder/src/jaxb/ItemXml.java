package jaxb;

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
@XmlRootElement(name = "Item")
public class ItemXml {

	private Item item = new Item();

	private String name;

	private String description;

	// category
	private List<Category> categories = new ArrayList<>();

	private BidsXml bids;

	private Float currently;

	private Float buy_Price;

	private Float first_Bid;

	private int number_of_Bids;

	private LocationXml location = new LocationXml();;
	
	private Date ends;

	private Date started;

	private int itemID;

	private SellerXml seller = new SellerXml();

	private BidderXml bidder = new BidderXml();

	/////////////////////////////////////////////////////////////////////// setters
	/////////////////////////////////////////////////////////////////////// getters

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@XmlElement(name = "Currently")
	@XmlJavaTypeAdapter(PriceAdapterXml.class)
	public Float getCurrently() {
		return currently;
	}

	public void setCurrently(Float currently) {
		this.currently = currently;
	}

	@XmlElement(name = "Buy_Price")
	@XmlJavaTypeAdapter(PriceAdapterXml.class)
	public Float getBuy_Price() {
		return buy_Price;
	}

	public void setBuy_Price(Float buy_Price) {
		this.buy_Price = buy_Price;
	}

	@XmlElement(name = "First_Bid")
	@XmlJavaTypeAdapter(PriceAdapterXml.class)
	public Float getFirst_Bid() {
		return first_Bid;
	}

	public void setFirst_Bid(Float first_Bid) {
		this.first_Bid = first_Bid;
	}

	@XmlElement(name = "Number_of_Bids")
	public int getNumber_of_Bids() {
		return number_of_Bids;
	}

	public void setNumber_of_Bids(int number_of_Bids) {
		this.number_of_Bids = number_of_Bids;
	}

	@XmlElement(name = "Ends")
	@XmlJavaTypeAdapter(DateAdapterXml.class)
	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	@XmlElement(name = "Started")
	@XmlJavaTypeAdapter(DateAdapterXml.class)
	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	@XmlAttribute(name = "ItemID")
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	@XmlElement(name = "Bids")
	public BidsXml getBids() {
		return bids;
	}

	public void setBids(BidsXml bids) {
		this.bids = bids;
	}

	@XmlElement(name = "Seller")
	public SellerXml getSeller() {
		return seller;
	}

	public void setSeller(SellerXml seller) {
		this.seller = seller;
	}

	@XmlElement(name = "Bidder")
	public BidderXml getBidder() {
		return bidder;
	}

	public void setBidder(BidderXml bidder) {
		this.bidder = bidder;
	}
	
	@XmlElement(name = "Category")
	public List<String> getCategories() {
		List<String> category = new ArrayList<>();
		for (Category c : categories) {
			category.add(c.getName());
		}
		return category;
	}
	
	@XmlElement(name= "Location")
	public LocationXml getLocation() {
		return location;
	}

	public void setLocation(LocationXml location) {
		this.location = location;
	}


	public void setCategories(List<String> category) {
		List<Category> categories = new ArrayList<>();
		for (String s : category) {
			Category cat = new Category();
			cat.setName(s);
			categories.add(cat);
		}
		this.categories = categories;
	}

	public void setItem() {
		item.setName(name);
		item.setDescription(description);
		item.setItemID(itemID);
		item.setCategories(categories);
		item.setBids(bidXmlToBid(bids.getBids()));
		item.setSeller(seller.getSeller());
		for (Bid b : item.getBids()) {
			b.setBidder(bidder.getBidder());
		}
		item.setCurrently(currently);
		item.setBuy_Price(buy_Price);
		item.setEnds(ends);
		item.setStarted(started);
		item.setNumber_of_Bids(number_of_Bids);
		item.setFirst_Bid(first_Bid);
		//item.setLocation(location.getLocation());
		item.setLocation(seller.getSeller().getUser().getLocation());
	}

	public Item getItem() {
		setItem();
		return this.item;
	}

	public List<Bid> bidXmlToBid(List<BidXml> bidxml) {
		List<Bid> bids = new ArrayList<>();
		for (BidXml b : bidxml) {
			bids.add(b.getBid());
		}
		return bids;
	}

	@Override
	public String toString() {
		String str;
		str = "Item:"
		+ "\n	" + "name =" + name
		+ "\n	" + "description =" + description
		+ "\n	" + "started = " + started
		+ "\n   " + "ends = " + ends
		+ "\n   " + "first_Bid = " + first_Bid
		+ "\n   " + "currently = " + currently
		+ "\n   " + "buy_Price= " + buy_Price
		+ "\n   " + "location= " + location
		+ "\n   " + "number_of_bids = " + number_of_Bids;
		for (Category c : categories) {
			str += "\n   " + "category =  " + c.getName();
		}
		str += "\n   " + seller;
		str += "\n   " + bids;
		return str;
	}
}