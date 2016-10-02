package jaxb;



import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import entities.Bid;
import entities.Bidder;
import entities.User;


/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Bidder")
public class BidderXml   {
	
	private int rating;
	
	private String userID;
	
	private LocationXml location;
	
	private String country;
	
	private Bidder bidder;
	
	public BidderXml(){
		location = new LocationXml();
		bidder = new Bidder();
		bidder.setUser(new User());
		bidder.getUser().setCountry("default country");
		bidder.getUser().setName("default name");
		bidder.getUser().setEmail("default e_mail");
		bidder.getUser().setPassword("default password");
		bidder.getUser().setSurname("default surname");
		bidder.getUser().setTaxRegistrationNumber("default tax_registration_number");
	}
	
	@XmlAttribute(name="Rating")
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@XmlAttribute(name="UserID")
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	@XmlElement(name="Location")
	public LocationXml getLocation() {
		return location;
	}


	public void setLocation(LocationXml location) {
		this.location = location;
	}


	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name="Country")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	/*public void setBidder(Bidder user){
		userID = user.getUser().getUsername();
		bidder = user;
	}
	
	public Bidder getBidder(){
		bidder.getUser().setUsername(userID);
		bidder.getUser().setCountry(country);
		bidder.setUserUsername(userID);
		bidder.setRating_Bidder(rating);
		bidder.getUser().setLocation(location.getLocation());
		return bidder;
	}*/
	
	
	public void setBidder(){
		bidder.getUser().setUsername(userID);
		bidder.getUser().setCountry(country);
		bidder.setUserUsername(userID);
		bidder.setRating_Bidder(rating);
		bidder.getUser().setLocation(location.getLocation());
		
		System.out.println("AXNEEEEWQEQWEQW");
		System.out.println(bidder.getUserUsername());
	}
	
	public Bidder getBidder(){
		setBidder();
		return bidder;	
	}
	
	
	//item.getBids().sort((o1, o2) -> o1.getTime().compareTo(o2.getTime()));
	
	@Override
	public String toString() {
		return "Bidder:"
	+ "\n        " + (userID != null ? "bidderID=" + userID +  ", " : "no userID")
	+ "\n        " + (rating != 0 ? "rating=" + rating + ", " : "no rating")
	+ "\n        " + (location != null ? location + ", " : "no location")
	+ "\n        " + (country != null ? "country=" + country + ", " : "no country");
	}
}