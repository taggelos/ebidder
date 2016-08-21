package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userID;

	private int admin;

	private String country;

	private String email;

	private String location;

	private String name;

	private String password;

	private int pending;

	private String phone;

	private int rating_Bidder;

	private int rating_Seller;

	private String surname;

	@Column(name="tax_registration_number")
	private String taxRegistrationNumber;

	private String username;

	//bi-directional many-to-one association to Bid
	@OneToMany(mappedBy="user")
	private List<Bid> bids;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="user")
	private List<Item> items;

	public User() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getAdmin() {
		return this.admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPending() {
		return this.pending;
	}

	public void setPending(int pending) {
		this.pending = pending;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getRating_Bidder() {
		return this.rating_Bidder;
	}

	public void setRating_Bidder(int rating_Bidder) {
		this.rating_Bidder = rating_Bidder;
	}

	public int getRating_Seller() {
		return this.rating_Seller;
	}

	public void setRating_Seller(int rating_Seller) {
		this.rating_Seller = rating_Seller;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTaxRegistrationNumber() {
		return this.taxRegistrationNumber;
	}

	public void setTaxRegistrationNumber(String taxRegistrationNumber) {
		this.taxRegistrationNumber = taxRegistrationNumber;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Bid> getBids() {
		return this.bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Bid addBid(Bid bid) {
		getBids().add(bid);
		bid.setUser(this);

		return bid;
	}

	public Bid removeBid(Bid bid) {
		getBids().remove(bid);
		bid.setUser(null);

		return bid;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setUser(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setUser(null);

		return item;
	}

}