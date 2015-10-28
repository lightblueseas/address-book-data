package de.alpharogroup.address.book.service.api;

import java.util.List;

import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.db.service.jpa.BusinessService;

// TODO: Auto-generated Javadoc
/**
 * The Interface ZipcodesService.
 */
public interface ZipcodesService extends BusinessService<Zipcodes, Integer>{

	/**
	 * Delete all zipcodes.
	 */
	void deleteAllZipcodes();

	/**
	 * Exists zipcode.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @return true, if successful
	 */
	boolean existsZipcode(final String zipcode);

	/**
	 * Find zipcodes.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @return the list
	 */
	List<Zipcodes> findZipcodes(final String zipcode);

	/**
	 * Gets the Zipcodes object from the given zipcode string and city. If it
	 * does not exist it will be create a new Zipcodes object.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @return the zipcode
	 */
	Zipcodes getZipcode(final String zipcode, final String city);
	
	/**
	 * Finds all Zipcodes from the given Countries object.
	 *
	 * @param country the country
	 * @return the list
	 */
	List<Zipcodes> find(final Countries country);
	
	/**
	 * Find the city from the given Countries object and zipcode.
	 *
	 * @param country the country
	 * @param zipcode the zipcode
	 * @return the list
	 */
	Zipcodes findCityFromZipcode(Countries country, String zipcode);
	
	/**
	 * Gets a List from Zipcodes with the given parameters that can be
	 * null if they shell be ignored in the query.
	 *
	 * @param country the country
	 * @param zipcode the zipcode
	 * @param city the city
	 * @return the list
	 */
	List<Zipcodes> findAll(Countries country, String zipcode, String city);
}