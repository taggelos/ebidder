package ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import entities.BidPK;

@ManagedBean(name="bid")
@RequestScoped
public class BidBean {
    
	private BidPK id;

	private int amount;  
    
    public String manage(){
    	return "/restricted/manage";
    }
    
    public String search(){
    	return "/restricted/search";
    }
    
    
    
    /*functions*/
    public BidPK getId() {
		return this.id;
	}

	public void setId(BidPK id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}