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
package de.alpharogroup.address.book.factories;

import java.io.Serializable;

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.entities.Zipcodes;

/**
 * A factory for creating Domain objects for the user management.
 */
public class AddressBookFactory implements Serializable
{

	/** The Constant instance. */
	private static final AddressBookFactory instance = new AddressBookFactory();

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets the single instance of UserManagementFactory.
	 * 
	 * @return single instance of UserManagementFactory
	 */
	public static AddressBookFactory getInstance()
	{
		return instance;
	}

	/**
	 * Instantiates a new UserManagementFactory object.
	 */
	private AddressBookFactory()
	{
		super();
	}

	/**
	 * Gets the addresses.
	 *
	 * @param id
	 *            the id
	 * @param addressComment
	 *            the address comment
	 * @param federalstate
	 *            the federalstate
	 * @param geohash
	 *            the geohash
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @param street
	 *            the street
	 * @param streetnumber
	 *            the streetnumber
	 * @param zipcode
	 *            the zipcode
	 * @return the addresses
	 */
	public Addresses newAddresses(final Integer id, final String addressComment,
		final Federalstates federalstate, final String geohash, final java.math.BigDecimal latitude,
		final java.math.BigDecimal longitude, final String street, final String streetnumber,
		final Zipcodes zipcode)
	{
		String lat = (latitude != null) ? latitude.toString() : "";
		String lng = (longitude != null) ? longitude.toString() : "";
		return newAddresses(addressComment, federalstate, geohash, id, lat, lng, street,
			streetnumber, zipcode);
	}

	/**
	 * Data pool factory for Addresses object.
	 *
	 * @param addressComment
	 *            the address comment
	 * @param federalstate
	 *            A valid Federalstates object
	 * @param geohash
	 *            the geohash
	 * @param id
	 *            the id
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @param street
	 *            the street
	 * @param streetnumber
	 *            the streetnumber
	 * @param zipcode
	 *            A valid Zipcodes object
	 * @return Addresses A Addresses object
	 */
	public Addresses newAddresses(String addressComment, Federalstates federalstate, String geohash,
		Integer id, String latitude, String longitude, String street, String streetnumber,
		Zipcodes zipcode)
	{
		Addresses addresses = new Addresses();
		addresses.setAddressComment(addressComment);
		addresses.setFederalstate(federalstate);
		addresses.setGeohash(geohash);
		addresses.setId(id);
		addresses.setLatitude(latitude);
		addresses.setLongitude(longitude);
		addresses.setStreet(street);
		addresses.setStreetnumber(streetnumber);
		addresses.setZipcode(zipcode);
		return addresses;
	}

	/**
	 * New addresses.
	 *
	 * @param addressComment
	 *            the address comment
	 * @param federalstate
	 *            the federalstate
	 * @param geohash
	 *            the geohash
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @param street
	 *            the street
	 * @param streetnumber
	 *            the streetnumber
	 * @param zipcode
	 *            the zipcode
	 * @return the addresses
	 */
	public Addresses newAddresses(final String addressComment, final Federalstates federalstate,
		final String geohash, final java.math.BigDecimal latitude,
		final java.math.BigDecimal longitude, final String street, final String streetnumber,
		final Zipcodes zipcode)
	{
		String lat = (latitude != null) ? latitude.toString() : "";
		String lng = (longitude != null) ? longitude.toString() : "";
		return newAddresses(addressComment, federalstate, geohash, null, lat, lng, street,
			streetnumber, zipcode);
	}

	/**
	 * Factory method for create an Countries object.
	 *
	 * @param id
	 *            the id
	 * @param iso3166A2name
	 *            the iso3166 a2name
	 * @param iso3166A3name
	 *            the iso3166 a3name
	 * @param iso3166Number
	 *            the iso3166 number
	 * @param name
	 *            the name
	 * @return Countries A Countries object
	 */
	public Countries newCountries(Integer id, String iso3166A2name, String iso3166A3name,
		String iso3166Number, String name)
	{
		Countries countries = new Countries();
		countries.setId(id);
		countries.setIso3166A2name(iso3166A2name);
		countries.setIso3166A3name(iso3166A3name);
		countries.setIso3166Number(iso3166Number);
		countries.setName(name);
		return countries;
	}

	/**
	 * Data pool factory for Federalstates object.
	 *
	 * @param country
	 *            A valid Countries object
	 * @param id
	 *            the id
	 * @param iso3166A2code
	 *            the iso3166 a2code
	 * @param name
	 *            the name
	 * @param subdivisionCategory
	 *            the subdivision category
	 * @param subdivisionName
	 *            the subdivision name
	 * @return Federalstates A Federalstates object
	 */
	public Federalstates newFederalstates(Countries country, Integer id, String iso3166A2code,
		String name, String subdivisionCategory, String subdivisionName)
	{
		Federalstates federalstates = new Federalstates();
		federalstates.setCountry(country);
		federalstates.setId(id);
		federalstates.setIso3166A2code(iso3166A2code);
		federalstates.setName(name);
		federalstates.setSubdivisionCategory(subdivisionCategory);
		federalstates.setSubdivisionName(subdivisionName);
		return federalstates;
	}

	/**
	 * Factory method for create an Zipcodes object.
	 *
	 * @param country
	 *            the country
	 * @param city
	 *            the city
	 * @param zipcode
	 *            the zipcode
	 * @return Zipcodes A Zipcodes object
	 */
	public Zipcodes newZipcodes(Countries country, String city, String zipcode)
	{
		Zipcodes zipcodes = new Zipcodes();
		zipcodes.setCountry(country);
		zipcodes.setCity(city);
		zipcodes.setZipcode(zipcode);
		return zipcodes;
	}

	/**
	 * Factory method for create an Zipcodes object.
	 *
	 * @param id
	 *            the id
	 * @param country
	 *            the country
	 * @param city
	 *            the city
	 * @param zipcode
	 *            the zipcode
	 * @return Zipcodes A Zipcodes object
	 */
	public Zipcodes newZipcodes(Integer id, Countries country, String city, String zipcode)
	{
		Zipcodes zipcodes = newZipcodes(country, city, zipcode);
		zipcodes.setId(id);
		return zipcodes;
	}
}
