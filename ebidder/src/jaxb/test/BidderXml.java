package jaxb.test;

import java.io.Serializable;

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
public class BidderXml implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String rating;
	
	private String userID;
	
	private String location;
	
	private String country;
	
	private User bidder = new User();

	@XmlAttribute(name="Rating")
	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@XmlAttribute(name="UserID")
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlElement(name="Location")
	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
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
		bidder.setLocation(location);
		//bidder.setUserID(Integer.parseInt(userID));
		bidder.setCountry(country);
		bidder.setRating_Bidder(Integer.parseInt(rating));
	}
	
	public User getBidder(){
		return bidder;
	}
	
	
}