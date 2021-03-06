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

	private Float buy_Price;

	private String country;

	private Float currently;

	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	private Date ends;

	private Float first_Bid;

	private String name;

	private int number_of_Bids;

	@Temporal(TemporalType.TIMESTAMP)
	private Date started;

	//bi-directional many-to-one association to Bid
	@OneToMany(mappedBy="item",cascade=CascadeType.REMOVE)
	private List<Bid> bids;

	//bi-directional many-to-many association to Category
	@ManyToMany
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

	//bi-directional many-to-one association to Location
	@ManyToOne
	private Location location;

	//bi-directional many-to-one association to Seller
	@ManyToOne
	private Seller seller;

	//bi-directional many-to-one association to Photo
	@OneToMany(mappedBy="item",cascade=CascadeType.REMOVE)
	private List<Photo> photos;

	public Item() {
	}

	public int getItemID() {
		return this.itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public Float getBuy_Price() {
		return this.buy_Price;
	}

	public void setBuy_Price(Float buy_Price) {
		this.buy_Price = buy_Price;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Float getCurrently() {
		return this.currently;
	}

	public void setCurrently(Float currently) {
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

	public Float getFirst_Bid() {
		return this.first_Bid;
	}

	public void setFirst_Bid(Float first_Bid) {
		this.first_Bid = first_Bid;
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

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Seller getSeller() {
		return this.seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public List<Photo> getPhotos() {
		return this.photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo addPhoto(Photo photo) {
		getPhotos().add(photo);
		photo.setItem(this);

		return photo;
	}

	public Photo removePhoto(Photo photo) {
		getPhotos().remove(photo);
		photo.setItem(null);

		return photo;
	}

}