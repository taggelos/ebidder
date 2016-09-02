package jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import entities.Location;
import entities.User;


/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Seller")
public class SellerXml implements Serializable {
	private static final long serialVersionUID = 1L;

	private int rating;
	
	private String userID;
	
	private User seller;
	
	public SellerXml(){
		seller = new User();
		seller.setCountry("default country");
		seller.setName("default name");
		seller.setEmail("default e_mail");
		seller.setPassword("default password");
		seller.setSurname("default surname");
		seller.setTaxRegistrationNumber("default tax_registration_number");
		Location location = new Location();
		location.setName("default location");
		seller.setLocation(location);
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
	
	public void setSeller(User user){
		seller = user;		
	}
	
	public User getSeller(){
		seller.setUsername(userID);
		seller.setRating_Seller(rating);
		return seller;
	}
	
	@Override
	public String toString() {
		return "   Seller "
	+ "\n      " + (userID != null ? "sellerID=" + userID +  ", " : "no userID")
	+ "\n      " + (rating != 0 ? "rating=" + rating + ", " : "no rating");
	}
}