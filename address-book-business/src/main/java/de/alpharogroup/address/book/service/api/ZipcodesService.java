package de.alpharogroup.address.book.service.api;

import java.util.List;

import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.db.service.api.BusinessService;

/**
 * The interface {@link ZipcodesService}.
 */
public interface ZipcodesService extends BusinessService<Zipcodes, Integer>{

	/**
	 * Delete all zipcodes.
	 */
	void deleteAllZipcodes();

	/**
	 * Checks if the given zipcode string exists.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @return true, if successful
	 */
	boolean existsZipcode(final String zipcode);

	/**
	 * Find {@link Zipcodes} objects from the given zipcode string.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @return the list of {@link Zipcodes}
	 */
	List<Zipcodes> findZipcodes(final String zipcode);

	/**
	 * Gets the {@link Zipcodes} object from the given zipcode string and city. If it
	 * does not exist it will be create a new {@link Zipcodes} object.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @return the {@link Zipcodes}
	 */
	Zipcodes getZipcode(final String zipcode, final String city);
	
	/**
	 * Finds all {@link Zipcodes} from the given Countries object.
	 *
	 * @param country the country
	 * @return the list of {@link Zipcodes}
	 */
	List<Zipcodes> find(final Countries country);
	
	/**
	 * Find the city from the given Countries object and zipcode.
	 *
	 * @param country the country
	 * @param zipcode the zipcode
	 * @return the {@link Zipcodes}
	 */
	Zipcodes findCityFromZipcode(Countries country, String zipcode);
	
	/**
	 * Gets a List from Zipcodes with the given parameters that can be
	 * null if they shell be ignored in the query.
	 *
	 * @param country the country
	 * @param zipcode the zipcode
	 * @param city the city
	 * @return the list of {@link Zipcodes}
	 */
	List<Zipcodes> findAll(Countries country, String zipcode, String city);
}