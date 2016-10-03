package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the bidder database table.
 * 
 */
@Entity
@NamedQuery(name="Bidder.findAll", query="SELECT b FROM Bidder b")
public class Bidder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_username")
	private String userUsername;

	private int rating_Bidder;

	//bi-directional many-to-one association to Bid
	@OneToMany(mappedBy="bidder")
	private List<Bid> bids;

	//bi-directional one-to-one association to User
	@OneToOne(cascade={CascadeType.PERSIST})
	@PrimaryKeyJoinColumn(name="user_username")
	private User user;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="bidder")
	private List<Message> messages;

	public Bidder() {
	}

	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public int getRating_Bidder() {
		return this.rating_Bidder;
	}

	public void setRating_Bidder(int rating_Bidder) {
		this.rating_Bidder = rating_Bidder;
	}

	public List<Bid> getBids() {
		return this.bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Bid addBid(Bid bid) {
		getBids().add(bid);
		bid.setBidder(this);

		return bid;
	}

	public Bid removeBid(Bid bid) {
		getBids().remove(bid);
		bid.setBidder(null);

		return bid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setBidder(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setBidder(null);

		return message;
	}

}