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
	
	
	private BidderXml thebidder = new BidderXml();


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
	
	@XmlElement(name="Bidder")
	public BidderXml getBidder() {
		return thebidder;
	}


	public void setBidder(BidderXml bidder) {
		this.thebidder = bidder;
	}


	public void setBid(Bid b){
		time = b.getTime();
		amount = b.getAmount();
		thebidder = new BidderXml();
		thebidder.setBidder(b.getBidder());
		bid = b;
	}
	
	public Bid getBid(){
		bid.setTime(time);
		bid.setAmount(amount);	
		bid.setBidder(thebidder.getBidder());
		return bid;	
	}
	
	@Override
	public String toString() {
		return "\n	Bid"
	+ "\n    " + "time=" + time
	+ "\n    " + "amount=" + amount
	+ "\n    " + thebidder ;
	}
}