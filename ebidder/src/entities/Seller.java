package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seller database table.
 * 
 */
@Entity
@NamedQuery(name="Seller.findAll", query="SELECT s FROM Seller s")
public class Seller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_username")
	private String userUsername;

	private int rating_Seller;

	//bi-directional many-to-one association to Item
	@OneToMany(mappedBy="seller")
	private List<Item> items;

	//bi-directional many-to-one association to Message
	@OneToMany(mappedBy="seller")
	private List<Message> messages;

	//bi-directional one-to-one association to User
	@OneToOne(cascade={CascadeType.PERSIST})
	@PrimaryKeyJoinColumn(name="user_username")
	private User user;

	public Seller() {
	}

	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public int getRating_Seller() {
		return this.rating_Seller;
	}

	public void setRating_Seller(int rating_Seller) {
		this.rating_Seller = rating_Seller;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Item addItem(Item item) {
		getItems().add(item);
		item.setSeller(this);

		return item;
	}

	public Item removeItem(Item item) {
		getItems().remove(item);
		item.setSeller(null);

		return item;
	}

	public List<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Message addMessage(Message message) {
		getMessages().add(message);
		message.setSeller(this);

		return message;
	}

	public Message removeMessage(Message message) {
		getMessages().remove(message);
		message.setSeller(null);

		return message;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}