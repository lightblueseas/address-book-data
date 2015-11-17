package de.alpharogroup.address.book.service.mapper.api;

import java.util.List;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.db.service.entitymapper.BusinessMapperService;

/**
 * The interface {@link AddressService}.
 */
public interface AddressService extends BusinessMapperService<Integer, Address>{
	
	/**
	 * Finds a list of {@link Address} from the given arguments.
	 *
	 * @param geohash
	 *            the geohash
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @return the list of {@link Address}
	 */
	List<Address> find(String geohash, String latitude, String longitude);

	/**
	 * Finds a list of {@link Address} from the given arguments.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Address}
	 */
	List<Address> find(String geohash);

	/**
	 * Finds a list of {@link Address} from the neighbourhood areas of the
	 * given geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Address}
	 */
	List<Address> findNeighbourhood(String geohash);

	/**
	 * Finds a list of {@link Address} from the first ring neighbourhood areas
	 * of the given geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Address}
	 */
	List<Address> findFirstRingNeighbourhood(String geohash);

	/**
	 * Finds a list of {@link Address} from the first and second ring
	 * neighbourhood areas of the given geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Address}
	 */
	List<Address> findFirstAndSecondRingNeighbourhood(String geohash);

	/**
	 * Finds a list of {@link Address} from the given latitude and longitude.
	 *
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @return the list of {@link Address}
	 */
	List<Address> find(String latitude, String longitude);

	/**
	 * Checks if the given latitude and longitude is contained in the database.
	 *
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @return the Address
	 */
	Address contains(String latitude, String longitude);

	/**
	 * Checks if the given {@link Zipcode} is contained in the database.
	 *
	 * @param zipcode
	 *            the zipcode
	 * @return the {@link Address}
	 */
	Address contains(Zipcode zipcode);

	/**
	 * Finds a list of {@link Address} from the given {@link Zipcode} object.
	 *
	 * @param zipcode
	 *            the zipcode
	 * @return the list of {@link Address}
	 */
	List<Address> find(Zipcode zipcode);

	/**
	 * Find all {@link Zipcode} with the given country.
	 *
	 * @param country
	 *            the county
	 * @return the list of {@link Zipcode}
	 */
	List<Zipcode> findAllAddressesWithCountry(Country country);

	/**
	 * Find all {@link Address} with the given country.
	 *
	 * @param country
	 *            the country
	 * @return the list
	 */
	List<Address> findAll(Country country);

	/**
	 * Find all addresses that have a geohash value that is null.
	 *
	 * @return the list of {@link Address}
	 */
	List<Address> findGeohashIsNull();

	/**
	 * Finds a list of {@link Address} from the given arguments.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @return the list of {@link Address}
	 */
	List<Address> find(Country country, String zipcode);

	/**
	 * Finds a list of {@link Address} from the given arguments.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @return the list of {@link Address}
	 */
	List<Address> find(Country country, String zipcode, String city);

	/**
	 * Finds the first {@link Address} from the given arguments.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @return the {@link Address}
	 */
	Address findFirst(Country country, String zipcode);

	/**
	 * Find invalid {@link Address} objects from the given arguments.
	 *
	 * @param country
	 *            the country
	 * @param geohash
	 *            the geohash
	 * @return the list
	 */
	List<Address> findInvalidAddresses(Country country, String geohash);

	/**
	 * Find invalid {@link Address} objects from the given arguments.
	 *
	 * @param country
	 *            the country
	 * @param geohash
	 *            the geohash
	 * @param not
	 *            the flag that indicates a negation from the geohash value
	 * @return the list of {@link Address}
	 */
	List<Address> findInvalidAddresses(Country country, String geohash, boolean not);

	/**
	 * Finds a list of {@link Address} that have the same cityname.
	 *
	 * @param country
	 *            the country
	 * @param city
	 *            the city
	 * @return the list of {@link Address}
	 */
	List<Address> findAddressesWithSameCityname(Country country, String city);

	/**
	 * Finds a list of {@link Address} that have the same zipcode.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @return the list of {@link Address}
	 */
	List<Address> findAddressesWithSameZipcode(Country country, String zipcode);
}
