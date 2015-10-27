package de.alpharogroup.address.book.application.model;

import java.io.Serializable;

import de.alpharogroup.address.book.entities.Addresses;

public interface LocationModel extends Serializable {
	
	String getLocation();
	
	void setLocation(String location);
	
	String getSelectedCountryName();
	
	void setSelectedCountryName(String selectedCountryName);
	
	Addresses getAddress();
	
	void setAddress(Addresses address);

}
