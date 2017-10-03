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
package de.alpharogroup.address.book.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.torpedoquery.jpa.Torpedo;

import de.alpharogroup.address.book.daos.AddressesDao;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.factories.AddressBookFactory;
import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.address.book.service.api.ZipcodesService;
import de.alpharogroup.address.book.service.util.HqlStringCreator;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;
import de.alpharogroup.jgeohash.Adjacent;
import de.alpharogroup.jgeohash.GeoHashExtensions;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link AddressesBusinessService}.
 */
@Transactional
@Service("addressesService")
public class AddressesBusinessService
	extends
		AbstractBusinessService<Addresses, Integer, AddressesDao>
	implements
		AddressesService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The {@link ZipcodesService}. */
	@Autowired
	@Getter
	@Setter
	private ZipcodesService zipcodesService;

	/** The {@link FederalstatesService}. */
	@Autowired
	@Getter
	@Setter
	private FederalstatesService federalstatesService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Addresses contains(String latitude, String longitude)
	{
		final List<Addresses> addresses = find(latitude, longitude);
		return ListExtensions.getFirst(addresses);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Addresses contains(Zipcodes zipcode)
	{
		final List<Addresses> addresses = find(zipcode);
		return ListExtensions.getFirst(addresses);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Addresses createAddress(final String street, final String streetnumber,
		final String addressComment, final String zipcode, final String city,
		final String federalstate)
	{

		final Zipcodes zc = zipcodesService.getZipcode(zipcode, city);
		Federalstates federalstates;
		if (zc != null && zc.getCountry() != null)
		{
			federalstates = federalstatesService.findFederalstate(zc.getCountry(), federalstate);
		}
		else
		{
			federalstates = federalstatesService.findFederalstateFromIso3166A2code(federalstate);
		}

		final Addresses address = AddressBookFactory.getInstance().newAddresses(addressComment,
			federalstates, null, null, null, street, streetnumber, zc);
		return address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Addresses createAddress(final String street, final String streetnumber,
		final String addressComment, final String zipcode, final String city,
		final String federalstate, final String geohash, final java.math.BigDecimal latitude,
		final java.math.BigDecimal longitude)
	{
		final Zipcodes zip = zipcodesService.getZipcode(zipcode, city);

		Federalstates federalstates;
		if (zip != null && zip.getCountry() != null)
		{
			federalstates = federalstatesService.findFederalstate(zip.getCountry(), federalstate);
		}
		else
		{
			federalstates = federalstatesService.findFederalstateFromIso3166A2code(federalstate);
		}
		final Addresses address = AddressBookFactory.getInstance().newAddresses(addressComment,
			federalstates, geohash, latitude, longitude, street, streetnumber, zip);

		return address;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Addresses> find(Countries country, String zipcode)
	{
		return find(country, zipcode, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> find(Countries country, String zipcode, String city)
	{
		final String hqlString = HqlStringCreator.forAddresses(country, zipcode, city);
		final Query query = getQuery(hqlString);
		if (country != null)
		{
			query.setParameter("country", country);
		}
		if (zipcode != null)
		{
			query.setParameter("zipcode", zipcode);
		}
		if (city != null)
		{
			query.setParameter("city", city);
		}
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> find(String geohash)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.geohash=:geohash");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("geohash", geohash);
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> find(String latitude, String longitude)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.latitude=:latitude");
		sb.append(" ");
		sb.append("and a.longitude=:longitude");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("latitude", latitude);
		query.setParameter("longitude", longitude);
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> find(String geohash, String latitude, String longitude)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.geohash=:geohash");
		sb.append(" ");
		sb.append("and a.latitude=:latitude");
		sb.append(" ");
		sb.append("and a.longitude=:longitude");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("geohash", geohash);
		query.setParameter("latitude", latitude);
		query.setParameter("longitude", longitude);
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> find(Zipcodes zipcode)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode=:zipcode");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("zipcode", zipcode);
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findAddressesWithSameCityname(Countries country, String city)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode.country=:country");
		sb.append(" ");
		sb.append("and a.zipcode.city=:city");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("country", country);
		query.setParameter("city", city);
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findAddressesWithSameZipcode(Countries country, String zipcode)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode.country=:country");
		sb.append(" ");
		sb.append("and a.zipcode.zipcode=:zipcode");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("country", country);
		query.setParameter("zipcode", zipcode);
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Addresses> findAll(Countries country)
	{
		List<Addresses> addresses = null;
		final Addresses from = Torpedo.from(Addresses.class);
		Torpedo.where(from.getZipcode().getCountry()).eq(country);
		final org.torpedoquery.jpa.Query<Addresses> select = Torpedo.select(from);
		addresses = select.list(getDao().getEntityManager());
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Zipcodes> findAllAddressesWithCountry(Countries country)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select a.zipcode from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode.country=:country");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("country", country);
		final List<Zipcodes> zipcodes = query.getResultList();
		return zipcodes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Addresses findFirst(Countries country, String zipcode)
	{
		final List<Addresses> addresses = find(country, zipcode);
		return ListExtensions.getFirst(addresses);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findFirstAndSecondRingNeighbourhood(String geohash)
	{
		Map<String, String> adjacentAreas = null;
		if (geohash != null && !geohash.isEmpty())
		{
			adjacentAreas = GeoHashExtensions.getTwentyFiveAreasMap(geohash);
		}
		final String hqlString = HqlStringCreator.getGeohashFirstAndSecondRingQuery(true);
		final Query query = getQuery(hqlString);
		for (final Entry<String, String> entry : adjacentAreas.entrySet())
		{
			query.setParameter(entry.getKey(), entry.getValue() + "%");
		}
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findFirstRingNeighbourhood(String geohash)
	{
		Map<String, String> adjacentAreas = null;
		if (geohash != null && !geohash.isEmpty())
		{
			adjacentAreas = GeoHashExtensions.getAllAdjacentAreasMap(geohash);
		}
		final String hqlString = HqlStringCreator.getGeohashFirstRingQuery(true);
		final Query query = getQuery(hqlString);
		for (final Entry<String, String> entry : adjacentAreas.entrySet())
		{
			query.setParameter(entry.getKey(), entry.getValue() + "%");
		}
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findGeohashIsNull()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.geohash is null");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		final List<Addresses> addresses = query.getResultList();
		return addresses;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Addresses> findInvalidAddresses(Countries country, String geohash)
	{
		return findInvalidAddresses(country, geohash, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findInvalidAddresses(Countries country, String geohash, boolean not)
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode.country=:country");
		sb.append(" ");
		sb.append("and a.geohash ");
		if (not)
		{
			sb.append("not ");
		}
		sb.append("like :geohash");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("country", country);
		query.setParameter("geohash", geohash + "%");
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findNeighbourhood(String geohash)
	{
		final String hqlString = "select address from Addresses address " + "where address.geohash like :"
			+ Adjacent.CENTER;
		final Query query = getQuery(hqlString);
		query.setParameter("center", geohash + "%");
		final List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * Sets the specific {@link AddressesDao}.
	 *
	 * @param addressesDao
	 *            the new {@link AddressesDao}.
	 */
	@Autowired
	public void setAddressesDao(AddressesDao addressesDao)
	{
		setDao(addressesDao);
	}

}