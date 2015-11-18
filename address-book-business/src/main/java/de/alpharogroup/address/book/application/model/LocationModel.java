package de.alpharogroup.address.book.application.model;

import java.io.Serializable;

/**
 * The interface {@link LocationModel}.
 */
public interface LocationModel<T> extends Serializable {
	
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
	T getAddress();
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	void setAddress(T address);

}
