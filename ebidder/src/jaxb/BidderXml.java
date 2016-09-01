package jaxb;



import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import entities.User;


/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Bidder")
public class BidderXml   {
	
	private int rating;
	
	private String userID;
	
	private LocationXml location = new LocationXml();
	
	private String country;
	
	private User bidder = new User();

	
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
	
	public void setBidder(){
		//bidder.setUserID(Integer.parseInt(userID));
		bidder.setCountry(country);
		bidder.setRating_Bidder(rating);
		bidder.setLocation(location.getLocation());
	}
	
	public User getBidder(){
		return bidder;
	}
	
	@Override
	public String toString() {
		return "   Bidder "
	+ "\n        " + (userID != null ? "bidderID=" + userID +  ", " : "no userID")
	+ "\n        " + (rating != 0 ? "rating=" + rating + ", " : "no rating")
	+ "\n        " + (location != null ? location + ", " : "no location")
	+ "\n        " + (country != null ? "country=" + country + ", " : "no country");
	}
}