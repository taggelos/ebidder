package jaxb.test;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Seller")
public class SellerXml implements Serializable {
	private static final long serialVersionUID = 1L;

	private String rating;
	
	private String userID;

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
	
	
}