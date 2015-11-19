package de.alpharogroup.address.book.rest;

import java.util.List;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.rest.api.AddressesResource;
import de.alpharogroup.address.book.service.api.AddressService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link AddressesRestResource} provides an implementation of the inteface {@link AddressesResource}.
 */
public class AddressesRestResource extends AbstractRestfulResource<Integer, Address, AddressService>
	implements AddressesResource
{
	
	/**
	 * {@inheritDoc}
	 */
	public List<Address> find(String geohash) {
		List<Address> addresses = getDomainService().find(geohash);
		return addresses;		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findNeighbourhood(String geohash) {
		List<Address> addresses = getDomainService().findNeighbourhood(geohash);
		return addresses;	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findFirstRingNeighbourhood(String geohash) {
		List<Address> addresses = getDomainService().findFirstRingNeighbourhood(geohash);
		return addresses;	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findFirstAndSecondRingNeighbourhood(String geohash) {
		List<Address> addresses = getDomainService().findFirstAndSecondRingNeighbourhood(geohash);
		return addresses;	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(String latitude, String longitude) {
		List<Address> addresses = getDomainService().find(latitude, longitude);
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address contains(String latitude, String longitude) {
		return getDomainService().contains(latitude, longitude);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address contains(Zipcode zipcode) {
		return getDomainService().contains(zipcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(Zipcode zipcode) {
		return getDomainService().find(zipcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findAllAddressesWithCountry(Country country) {
		List<Zipcode> zipcodes = getDomainService().findAllAddressesWithCountry(country);
		return zipcodes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findAll(Country country) {
		List<Address> addresses = getDomainService().findAll(country);
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findGeohashIsNull() {
		List<Address> addresses = getDomainService().findGeohashIsNull();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(Country country, String zipcode) {
		List<Address> addresses = getDomainService().find(country, zipcode);
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(Country country, String zipcode, String city) {
		List<Address> addresses = getDomainService().find(country, zipcode, city);
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address findFirst(Country country, String zipcode) {
		return getDomainService().findFirst(country, zipcode);
	}
	
}
