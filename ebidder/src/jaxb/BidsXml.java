package jaxb;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



import java.util.ArrayList;

import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@XmlRootElement(name="Bids")
public class BidsXml {
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
		return " Bids " + "\n"+ (bids != null ?  bids : "\n") ;
	}
}