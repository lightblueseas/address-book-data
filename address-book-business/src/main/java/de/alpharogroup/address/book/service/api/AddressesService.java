/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.address.book.service.api;

import java.util.List;

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.db.service.api.BusinessService;

/**
 * The interface {@link AddressesService}.
 */
public interface AddressesService extends BusinessService<Addresses, Integer> {

	/**
	 * Checks if the given latitude and longitude is contained in the database.
	 *
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @return the addresses
	 */
	Addresses contains(String latitude, String longitude);

	/**
	 * Checks if the given {@link Zipcodes} is contained in the database and
	 * return the first occurence.
	 *
	 * @param zipcode
	 *            the zipcode
	 * @return the {@link Addresses}
	 */
	Addresses contains(Zipcodes zipcode);

	/**
	 * Creates the address.
	 *
	 * @param street
	 *            the street
	 * @param streetnumber
	 *            the streetnumber
	 * @param addressComment
	 *            the address comment
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @param federalstate
	 *            the federalstate
	 * @return the addresses
	 */
	Addresses createAddress(final String street, final String streetnumber, final String addressComment,
			final String zipcode, final String city, String federalstate);

	/**
	 * Creates the address.
	 *
	 * @param street
	 *            the street
	 * @param streetnumber
	 *            the streetnumber
	 * @param addressComment
	 *            the address comment
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @param federalstate
	 *            the federalstate
	 * @param geohash
	 *            the geohash
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @return the addresses
	 */
	Addresses createAddress(final String street, final String streetnumber, final String addressComment,
			final String zipcode, final String city, final String federalstate, final String geohash,
			final java.math.BigDecimal latitude, final java.math.BigDecimal longitude);

	/**
	 * Finds a list of {@link Addresses} from the given arguments.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> find(Countries country, String zipcode);

	/**
	 * Finds a list of {@link Addresses} from the given arguments.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> find(Countries country, String zipcode, String city);

	/**
	 * Finds a list of {@link Addresses} from the given arguments.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> find(String geohash);

	/**
	 * Finds a list of {@link Addresses} from the given latitude and longitude.
	 *
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> find(String latitude, String longitude);

	/**
	 * Finds a list of {@link Addresses} from the given arguments.
	 *
	 * @param geohash
	 *            the geohash
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> find(String geohash, String latitude, String longitude);

	/**
	 * Finds a list of {@link Addresses} from the given {@link Zipcodes} object.
	 *
	 * @param zipcode
	 *            the zipcode
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> find(Zipcodes zipcode);

	/**
	 * Finds a list of {@link Addresses} that have the same cityname.
	 *
	 * @param country
	 *            the country
	 * @param city
	 *            the city
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> findAddressesWithSameCityname(Countries country, String city);

	/**
	 * Finds a list of {@link Addresses} that have the same zipcode.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> findAddressesWithSameZipcode(Countries country, String zipcode);

	/**
	 * Find all addresses with the given country.
	 *
	 * @param country
	 *            the country
	 * @return the list
	 */
	List<Addresses> findAll(Countries country);

	/**
	 * Find all {@link Zipcodes} with the given country.
	 *
	 * @param county
	 *            the county
	 * @return the list of {@link Zipcodes}
	 */
	List<Zipcodes> findAllAddressesWithCountry(Countries county);

	/**
	 * Finds the first {@link Addresses} from the given arguments.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @return the {@link Addresses}
	 */
	Addresses findFirst(Countries country, String zipcode);

	/**
	 * Finds a list of {@link Addresses} from the first and second ring
	 * neighbourhood areas of the given geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> findFirstAndSecondRingNeighbourhood(String geohash);

	/**
	 * Finds a list of {@link Addresses} from the first ring neighbourhood areas
	 * of the given geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> findFirstRingNeighbourhood(String geohash);

	/**
	 * Find all addresses that have a geohash value that is null.
	 *
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> findGeohashIsNull();

	/**
	 * Find invalid {@link Addresses} objects from the given arguments.
	 *
	 * @param country
	 *            the country
	 * @param geohash
	 *            the geohash
	 * @return the list
	 */
	List<Addresses> findInvalidAddresses(Countries country, String geohash);

	/**
	 * Find invalid {@link Addresses} objects from the given arguments.
	 *
	 * @param country
	 *            the country
	 * @param geohash
	 *            the geohash
	 * @param not
	 *            the flag that indicates a negation from the geohash value
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> findInvalidAddresses(Countries country, String geohash, boolean not);

	/**
	 * Finds a list of {@link Addresses} from the neighbourhood areas of the
	 * given geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Addresses}
	 */
	List<Addresses> findNeighbourhood(String geohash);
}