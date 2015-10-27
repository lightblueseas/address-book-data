package de.alpharogroup.address.book.application.utils;

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Zipcodes;

/**
 * The Class AddressesUtils.
 */
public class AddressesUtils {


	/**
	 * Gets the location in the format first 2 character from zipcode plus the city.
	 *
	 * @param address the address
	 * @return the location
	 */
	public static String getLocation(Addresses address) {
		String locaction = null;
		if(address != null) {
			Zipcodes zc = address.getZipcode();
			if(zc != null) {
				locaction = zc.getZipcode().substring(0, 2) + "* " + zc.getCity();
			}			
		}
		return locaction;
	}

	/**
	 * Gets the location in the format first 2 character from zipcode plus the city.
	 *
	 * @param address the address
	 * @return the location
	 */
	public static String getFullLocation(Addresses address) {
		String locaction = null;
		if(address != null) {
			Zipcodes zc = address.getZipcode();
			if(zc != null) {
				locaction = zc.getZipcode().trim() + " " + zc.getCity().trim();
			}			
		}
		return locaction;
	}
	
	
}
