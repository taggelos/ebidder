package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the items database table.
 * 
 */
@Entity
@Table(name="items")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemID;

	private int amount;

	private int buy_Price;

	private String country;

	private int currently;

	private String description;

	private String ends;

	private int first_Bid;

	private String latitude;

	private String longitude;

	private int number_of_Bids;

	private int sellerID;

	private String started;

	private String time;

	//bi-directional many-to-one association to Bid
	@OneToMany(mappedBy="item")
	private List<Bid> bids;

	//bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(
		name="items_has_categories"
		, joinColumns={
			@JoinColumn(name="Items_ItemID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Categories_Name")
			}
		)
	private List<Category> categories;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_UserID")
	private User user;

	public Item() {
	}

	public int getItemID() {
		return this.itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getBuy_Price() {
		return this.buy_Price;
	}

	public void setBuy_Price(int buy_Price) {
		this.buy_Price = buy_Price;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getCurrently() {
		return this.currently;
	}

	public void setCurrently(int currently) {
		this.currently = currently;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnds() {
		return this.ends;
	}

	public void setEnds(String ends) {
		this.ends = ends;
	}

	public int getFirst_Bid() {
		return this.first_Bid;
	}

	public void setFirst_Bid(int first_Bid) {
		this.first_Bid = first_Bid;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getNumber_of_Bids() {
		return this.number_of_Bids;
	}

	public void setNumber_of_Bids(int number_of_Bids) {
		this.number_of_Bids = number_of_Bids;
	}

	public int getSellerID() {
		return this.sellerID;
	}

	public void setSellerID(int sellerID) {
		this.sellerID = sellerID;
	}

	public String getStarted() {
		return this.started;
	}

	public void setStarted(String started) {
		this.started = started;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<Bid> getBids() {
		return this.bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Bid addBid(Bid bid) {
		getBids().add(bid);
		bid.setItem(this);

		return bid;
	}

	public Bid removeBid(Bid bid) {
		getBids().remove(bid);
		bid.setItem(null);

		return bid;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}