package ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entities.BidPK;

@ManagedBean(name="bid")
@RequestScoped
public class BidBean {
    
	private BidPK id;

	private String name;
	private String category; // Tha allaksei se lista
	private int buy_price;
	private int first_bid;
	private String location;	// Tha allaksei se latitude kai ...
	private String country;
	private int started;  // Tha allaksei se time
	private int ends;  // Tha allaksei se time
	private String  description;
	private String images; //tha allaksei se lista apo fotos

	private int amount;  
    
	/* Pages */
    public String manage(){
    	return "/html/manage";
    }
    
    public String create(){
    	return "/html/create_bid";
    }
    
    public String edit(){
    	return "/html/edit_bid";
    }
    
    /* Operations */
    public String create_bid(){
    	/*
    	if ( apetuxe )
    	{
    		return ".";  mhnuma sto create page oti den mpike stin vasi
    	}
    	*/
    	
    	return "/html/manage";
    }
    
    
    /* Getters and Setters */
    public BidPK getId() {
		return this.id;
	}

	public void setId(BidPK id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBuy_price() {
		return buy_price;
	}

	public void setBuy_price(int buy_price) {
		this.buy_price = buy_price;
	}

	public int getFirst_bid() {
		return first_bid;
	}

	public void setFirst_bid(int first_bid) {
		this.first_bid = first_bid;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getStarted() {
		return started;
	}

	public void setStarted(int started) {
		this.started = started;
	}

	public int getEnds() {
		return ends;
	}

	public void setEnds(int ends) {
		this.ends = ends;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}