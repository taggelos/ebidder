package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the seller database table.
 * 
 */
@Entity
@NamedQuery(name="Seller.findAll", query="SELECT s FROM Seller s")
public class Seller implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int user_UserID;

	private int rating_Seller;

	//bi-directional one-to-one association to User
	@OneToOne(cascade={CascadeType.PERSIST})
	@PrimaryKeyJoinColumn(name="user_UserID")
	private User user;

	public Seller() {
	}

	public int getUser_UserID() {
		return this.user_UserID;
	}

	public void setUser_UserID(int user_UserID) {
		this.user_UserID = user_UserID;
	}

	public int getRating_Seller() {
		return this.rating_Seller;
	}

	public void setRating_Seller(int rating_Seller) {
		this.rating_Seller = rating_Seller;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}