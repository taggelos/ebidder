package jaxb;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import entities.Bid;



/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Bid")
public class BidXml {
	private Bid bid = new Bid();

	private Float amount;

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;
	
	private BidderXml bidder = new BidderXml();


	@XmlElement(name="Amount")
	@XmlJavaTypeAdapter(PriceAdapterXml.class)
	public Float getAmount() {
		return amount;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}

	@XmlElement(name="Time")
	@XmlJavaTypeAdapter(DateAdapterXml.class)
	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}
	
	@XmlElement(name="bidder")
	public BidderXml getBidder() {
		return bidder;
	}


	public void setBidder(BidderXml bidder) {
		this.bidder = bidder;
	}


	public void setBid(){
		bid.setTime(time);
		bid.setAmount(amount);	
		bid.setBidder(bidder.getBidder());
	}
	
	public Bid getBid(){
		return bid;	
	}
	
	@Override
	public String toString() {
		return "\n	Bid"
	+ "\n    " + "time=" + time
	+ "\n    " + "amount=" + amount
	+ "\n    " + bidder ;
	}
}