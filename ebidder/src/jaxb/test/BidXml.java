package jaxb.test;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Bid")
public class BidXml implements Serializable {
	private static final long serialVersionUID = 1L;

	//private Item item;

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


	
	
	
	@Override
	public String toString() {
		return "User ["
	+ "\n" + (time != null ? "time=" + time + ", " : "")
	+ "\n" + (amount != 0 ? "amount=" + amount
	+  ", " : "")
	+ "\n" + (amount != 0 ? "amount=" + amount + ", " : "") + "]";
	}
}