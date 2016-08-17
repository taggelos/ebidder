package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bid database table.
 * 
 */
@Entity
@NamedQuery(name="Bid.findAll", query="SELECT b FROM Bid b")
public class Bid implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BidPK id;

	private int amount;

	//bi-directional many-to-one association to Item
	@ManyToOne
	@JoinColumn(name="Items_ItemID")
	private Item item;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="users_UserID")
	private User user;

	public Bid() {
	}

	public BidPK getId() {
		return this.id;
	}

	public void setId(BidPK id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}