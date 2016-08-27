package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemID;

	private int amount;

	private float buy_Price;

	private String country;

	private float currently;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ends;

	private float first_Bid;

	private float latitude;

	private float longitude;

	private String name;

	private int number_of_Bids;

	private int sellerID;

	@Temporal(TemporalType.TIMESTAMP)
	private Date started;

	//bi-directional many-to-one association to Bid
	@OneToMany(mappedBy="item")
	private List<Bid> bids;

	//bi-directional many-to-one association to Image
	@OneToMany(cascade=CascadeType.PERSIST,mappedBy="item")
	private List<Image> images;

	//bi-directional many-to-many association to Category
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(
		name="item_has_category"
		, joinColumns={
			@JoinColumn(name="item_ItemID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="category_Name")
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

	public float getBuy_Price() {
		return this.buy_Price;
	}

	public void setBuy_Price(float buy_Price) {
		this.buy_Price = buy_Price;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public float getCurrently() {
		return this.currently;
	}

	public void setCurrently(float currently) {
		this.currently = currently;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEnds() {
		return this.ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	public float getFirst_Bid() {
		return this.first_Bid;
	}

	public void setFirst_Bid(float first_Bid) {
		this.first_Bid = first_Bid;
	}

	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getStarted() {
		return this.started;
	}

	public void setStarted(Date started) {
		this.started = started;
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

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setItem(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setItem(null);

		return image;
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