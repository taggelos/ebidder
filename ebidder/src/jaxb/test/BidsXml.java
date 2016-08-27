package jaxb.test;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



import java.util.ArrayList;

import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Bids")
public class BidsXml implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<BidXml> bids;
	
	@XmlElement(name = "Bid")
	public List<BidXml> getBids() {
		if (null==bids) {
			bids = new ArrayList<BidXml>();
		}
		return bids;
	}

	public void setPrograms(List<BidXml> bids) {
		this.bids = bids;
	}

	@Override
	public String toString() {
		return "Bids [" + (bids != null ? "bids=" + bids : "") + "]";
	}
}