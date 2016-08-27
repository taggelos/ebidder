package jaxb.test;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import entities.Bid;
import entities.Item;



/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Bid")
public class BidXml implements Serializable {
	private static final long serialVersionUID = 1L;

	private Bid bid = new Bid();

	private float amount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	private BidderXml Bidder = new BidderXml();


	@XmlElement(name="Amount")
	public float getAmount() {
		return amount;
	}


	public void setAmount(float amount) {
		this.amount = amount;
	}

	@XmlElement(name="Time")
	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}
	
	@XmlElement(name="Bidder")
	public BidderXml getBidder() {
		return Bidder;
	}


	public void setBidder(BidderXml bidder) {
		Bidder = bidder;
	}


	public void setBid(){
		bid.setTime(time);
		bid.setAmount(amount);	
		bid.setUser(Bidder.getBidder());
	}
	
	public Bid getBid(){
		return bid;	
	}
	
	@Override
	public String toString() {
		return "User ["
	+ "\n" + (time != null ? "time=" + time + ", " : "")
	+ "\n" + (amount != 0 ? "amount=" + amount
	+  ", " : "")
	+ "\n" + (amount != 0 ? "amount=" + amount + ", " : "") + "]";
	}
}