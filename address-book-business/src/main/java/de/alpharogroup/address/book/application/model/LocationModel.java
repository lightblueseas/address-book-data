package de.alpharogroup.address.book.application.model;

import java.io.Serializable;

import de.alpharogroup.address.book.entities.Addresses;

/**
 * The interface {@link LocationModel}.
 */
// TODO generify the entity object to make it posible domain object...
public interface LocationModel extends Serializable {
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	String getLocation();
	
	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	void setLocation(String location);
	
	/**
	 * Gets the selected country name.
	 *
	 * @return the selected country name
	 */
	String getSelectedCountryName();
	
	/**
	 * Sets the selected country name.
	 *
	 * @param selectedCountryName the new selected country name
	 */
	void setSelectedCountryName(String selectedCountryName);
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	Addresses getAddress();
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	void setAddress(Addresses address);

}
