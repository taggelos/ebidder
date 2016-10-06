package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the message database table.
 * 
 */
@Entity
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int messageID;

	private String text;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	//bi-directional many-to-one association to Bidder
	@ManyToOne
	private Bidder bidder;

	//bi-directional many-to-one association to Seller
	@ManyToOne
	private Seller seller;

	private byte unread;
	
	public byte getUnread() {
		return this.unread;
	}

	public void setUnread(byte unread) {
		this.unread = unread;
	}
	
	public Message() {
	}

	public int getMessageID() {
		return this.messageID;
	}

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
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