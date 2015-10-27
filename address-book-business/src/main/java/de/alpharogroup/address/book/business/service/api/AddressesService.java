package de.alpharogroup.address.book.business.service.api;

import java.util.List;

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.db.service.jpa.BusinessService;

public interface AddressesService extends BusinessService<Addresses, Integer>{

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
	Addresses createAddress(final String street, final String streetnumber,
			final String addressComment, final String zipcode,
			final String city, String federalstate);

	/**
	 * Creates the address.
	 *
	 * @param street the street
	 * @param streetnumber the streetnumber
	 * @param addressComment the address comment
	 * @param zipcode the zipcode
	 * @param city the city
	 * @param federalstate the federalstate
	 * @param geohash the geohash
	 * @param latitude the latitude
	 * @param longitude the longitude
	 * @return the addresses
	 */
	Addresses createAddress(final String street,
			final String streetnumber, final String addressComment,
			final String zipcode, final String city, final String federalstate,
			final String geohash, final java.math.BigDecimal latitude,
			final java.math.BigDecimal longitude);
	
	List<Addresses> find(String geohash, String latitude, String longitude);
	
	List<Addresses> find(String geohash);
	
	List<Addresses> findNeighbourhood(String geohash);
	
	List<Addresses> findFirstRingNeighbourhood(String geohash);
	
	List<Addresses> findFirstAndSecondRingNeighbourhood(String geohash);
	
	List<Addresses> find(String latitude, String longitude);
	
	Addresses contains(String latitude, String longitude);
	
	Addresses contains(Zipcodes zipcode);
	
	List<Addresses> find(Zipcodes zipcode);
	
	List<Zipcodes> findAllAddressesWithCountry(Countries county);
	
	List<Addresses> findAll(Countries country);
	
	List<Addresses> findGeohashIsNull();
	
	List<Addresses> find(Countries country, String zipcode);
	
	List<Addresses> find(Countries country, String zipcode, String city);
	
	Addresses findFirst(Countries country, String zipcode);
	
	List<Addresses> findInvalidAddresses(Countries country, String geohash);
	
	List<Addresses> findInvalidAddresses(Countries country, String geohash, boolean not);
	
	List<Addresses> findAddressesWithSameCityname(Countries country, String city);
	
	List<Addresses> findAddressesWithSameZipcode(Countries country, String zipcode);
}