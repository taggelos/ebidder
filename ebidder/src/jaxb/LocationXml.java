package jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.XmlTransient;

import entities.Location;



/**
 * The persistent class for the item database table.
 * 
 */

@XmlRootElement(name="Location")
public class LocationXml {
	
	private Float latitude;

	private Float longitude;
	
    private String name;
	
    private Location location = new Location();
	
	@XmlAttribute(name="Latitude")
	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	@XmlAttribute(name="Longitude")
	public Float getLongitude() {
		return longitude;
	}

	
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	

	//@XmlJavaTypeAdapter(CollapsedStringAdapter.class)
	@XmlValue
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public Location getLocation(){
		//bidder.setUserID(Integer.parseInt(userID));
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		location.setName(name);
		return location;
		//bidder.setLocation(location);
	}
	
	public void setLocation(Location location){
		//bidder.setUserID(Integer.parseInt(userID));
		latitude = location.getLatitude();
		longitude = location.getLongitude();
		name = location.getName();
		this.location = location;
		//bidder.setLocation(location);
	}
	
	@Override
	public String toString() {
		return "   Location "
	+ "\n        " + (latitude != null ? "latitude=" + latitude +  ", " : "no latitude")
	+ "\n        " + (longitude != null ? "longitude=" + longitude + ", " : "no longitude")
	+ "\n        " + (name != null ? "location=" + name + ", " : "no location");
	}
}