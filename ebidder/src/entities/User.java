package entities;

import java.io.Serializable;
import javax.persistence.*;


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

	 //bi-directional many-to-one association to Location
	 @ManyToOne
	 @JoinColumn(name="location_name")
	 private Location location;

	private String name;

	private String password;

	private int pending;

	private String phone;

	private String surname;

	@Column(name="tax_registration_number")
	private String taxRegistrationNumber;

	private String username;

	//bi-directional one-to-one association to Bidder
	@OneToOne(mappedBy="user")
	private Bidder bidder;

	//bi-directional one-to-one association to Seller
	@OneToOne(mappedBy="user")
	private Seller seller;

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

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location locationName) {
		this.location = locationName;
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

	public Bidder getBidder() {
		return this.bidder;
	}

	public void setBidder(Bidder bidder) {
		this.bidder = bidder;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

}