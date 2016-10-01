package entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the bidder database table.
 * 
 */
@Entity
@NamedQuery(name="Bidder.findAll", query="SELECT b FROM Bidder b")
public class Bidder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int user_UserID;

	private int rating_Bidder;

	//bi-directional one-to-one association to User
	@OneToOne(cascade={CascadeType.PERSIST})
	@PrimaryKeyJoinColumn(name="user_UserID")
	private User user;

	public Bidder() {
	}

	public int getUser_UserID() {
		return this.user_UserID;
	}

	public void setUser_UserID(int user_UserID) {
		this.user_UserID = user_UserID;
	}

	public int getRating_Bidder() {
		return this.rating_Bidder;
	}

	public void setRating_Bidder(int rating_Bidder) {
		this.rating_Bidder = rating_Bidder;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}