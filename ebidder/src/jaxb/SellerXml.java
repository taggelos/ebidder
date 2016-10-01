package jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import entities.Location;
import entities.Seller;
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
	
	private Seller seller;
	
	public SellerXml(){
		seller = new Seller();
		seller.setUser(new User());
		seller.getUser().setCountry("default country");
		seller.getUser().setName("default name");
		seller.getUser().setEmail("default e_mail");
		seller.getUser().setPassword("default password");
		seller.getUser().setSurname("default surname");
		seller.getUser().setTaxRegistrationNumber("default tax_registration_number");
		Location location = new Location();
		location.setName("default location");
		seller.getUser().setLocation(location);
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
	
	public void setSeller(Seller user){
		seller = user;		
	}
	
	public Seller getSeller(){
		seller.getUser().setUsername(userID);
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