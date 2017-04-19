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
package de.alpharogroup.address.book.rest;

import java.util.List;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.rest.api.AddressesResource;
import de.alpharogroup.address.book.rest.beanparams.AddressSearchCriteria;
import de.alpharogroup.address.book.service.api.AddressService;
import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link AddressesRestResource} provides an implementation of the inteface
 * {@link AddressesResource}.
 */
public class AddressesRestResource extends AbstractRestfulResource<Integer, Address, AddressService>
	implements
		AddressesResource
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address contains(String latitude, String longitude)
	{
		return getDomainService().contains(latitude, longitude);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address contains(Zipcode zipcode)
	{
		return getDomainService().contains(zipcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(AddressSearchCriteria addressSearchCriteria)
	{
		List<Address> addresses = getDomainService().find(addressSearchCriteria.getCountry(),
			addressSearchCriteria.getZipcode(), addressSearchCriteria.getCity());
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Address> find(String geohash)
	{
		List<Address> addresses = getDomainService().find(geohash);
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(String latitude, String longitude)
	{
		List<Address> addresses = getDomainService().find(latitude, longitude);
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(Zipcode zipcode)
	{
		return getDomainService().find(zipcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findAll(Country country)
	{
		List<Address> addresses = getDomainService().findAll(country);
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findAllAddressesWithCountry(Country country)
	{
		List<Zipcode> zipcodes = getDomainService().findAllAddressesWithCountry(country);
		return zipcodes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address findFirst(AddressSearchCriteria addressSearchCriteria)
	{
		List<Address> addresses = find(addressSearchCriteria);
		return ListExtensions.getFirst(addresses);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findFirstAndSecondRingNeighbourhood(String geohash)
	{
		List<Address> addresses = getDomainService().findFirstAndSecondRingNeighbourhood(geohash);
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findFirstRingNeighbourhood(String geohash)
	{
		List<Address> addresses = getDomainService().findFirstRingNeighbourhood(geohash);
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findGeohashIsNull()
	{
		List<Address> addresses = getDomainService().findGeohashIsNull();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findNeighbourhood(String geohash)
	{
		List<Address> addresses = getDomainService().findNeighbourhood(geohash);
		return addresses;
	}

}
