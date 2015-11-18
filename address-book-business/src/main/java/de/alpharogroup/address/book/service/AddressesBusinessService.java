package de.alpharogroup.address.book.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import de.alpharogroup.db.service.jpa.AbstractBusinessService;
import de.alpharogroup.jgeohash.Adjacent;
import de.alpharogroup.jgeohash.GeoHashUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link AddressesBusinessService}.
 */
@Transactional
@Service("addressesService")
public class AddressesBusinessService
	extends
		AbstractBusinessService<Addresses, Integer, AddressesDao> implements AddressesService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Sets the specific {@link AddressesDao}.
	 *
	 * @param addressesDao the new {@link AddressesDao}.
	 */
	@Autowired
	public void setAddressesDao(AddressesDao addressesDao)
	{
		setDao(addressesDao);
	}


	/** The {@link ZipcodesService}. */
	@Autowired @Getter @Setter
	private ZipcodesService zipcodesService;

	/** The {@link FederalstatesService}. */
	@Autowired @Getter @Setter
	private FederalstatesService federalstatesService;

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
	@SuppressWarnings("unchecked")
	public List<Addresses> find(String geohash, String latitude, String longitude)
	{
		StringBuilder sb = new StringBuilder();
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
		List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Zipcodes> findAllAddressesWithCountry(Countries country)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a.zipcode from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode.country=:country");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("country", country);
		List<Zipcodes> zipcodes = query.getResultList();
		return zipcodes;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findAll(Countries country)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode.country=:country");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("country", country);
		List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findGeohashIsNull()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.geohash is null");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		List<Addresses> addresses = query.getResultList();
		return addresses;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> find(String geohash)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.geohash=:geohash");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("geohash", geohash);
		List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findNeighbourhood(String geohash)
	{
		String hqlString = "select address from Addresses address " + "where address.geohash like :"
				+ Adjacent.CENTER;
		final Query query = getQuery(hqlString);
		query.setParameter("center", geohash + "%");
		List<Addresses> addresses = query.getResultList();
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
			adjacentAreas = GeoHashUtils.getAllAdjacentAreasMap(geohash);
		}
		String hqlString = HqlStringCreator.getGeohashFirstRingQuery();
		final Query query = getQuery(hqlString);
		for (Entry<String, String> entry : adjacentAreas.entrySet())
		{
			query.setParameter(entry.getKey(), entry.getValue() + "%");
		}
		List<Addresses> addresses = query.getResultList();
		return addresses;
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
			adjacentAreas = GeoHashUtils.getTwentyFiveAreasMap(geohash);
		}
		String hqlString = HqlStringCreator.getGeohashFirstAndSecondRingQuery();
		final Query query = getQuery(hqlString);
		for (Entry<String, String> entry : adjacentAreas.entrySet())
		{
			query.setParameter(entry.getKey(), entry.getValue() + "%");
		}
		List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> find(String latitude, String longitude)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.latitude=:latitude");
		sb.append(" ");
		sb.append("and a.longitude=:longitude");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("latitude", latitude);
		query.setParameter("longitude", longitude);
		List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Addresses contains(String latitude, String longitude)
	{
		List<Addresses> addresses = find(latitude, longitude);
		if (addresses != null && !addresses.isEmpty())
		{
			return addresses.get(0);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Addresses contains(Zipcodes zipcode)
	{
		List<Addresses> addresses = find(zipcode);
		if (addresses != null && !addresses.isEmpty())
		{
			return addresses.get(0);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> find(Zipcodes zipcode)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode=:zipcode");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("zipcode", zipcode);
		List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> find(Countries country, String zipcode)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode.country=:country");
		sb.append(" ");
		sb.append("and a.zipcode.zipcode=:zipcode");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("zipcode", zipcode);
		query.setParameter("country", country);
		List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> find(Countries country, String zipcode, String city)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode.country=:country");
		sb.append(" ");
		sb.append("and a.zipcode.zipcode=:zipcode");
		sb.append(" ");
		sb.append("and a.zipcode.city like :city");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("country", country);
		query.setParameter("zipcode", zipcode);
		query.setParameter("city", "%" + city + "%");
		List<Addresses> addresses = query.getResultList();
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
		StringBuilder sb = new StringBuilder();
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
		List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findAddressesWithSameCityname(Countries country, String city)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode.country=:country");
		sb.append(" ");
		sb.append("and a.zipcode.city=:city");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("country", country);
		query.setParameter("city", city);
		List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Addresses> findAddressesWithSameZipcode(Countries country, String zipcode)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.zipcode.country=:country");
		sb.append(" ");
		sb.append("and a.zipcode.zipcode=:zipcode");
		final String hqlString = sb.toString();
		final Query query = getQuery(hqlString);
		query.setParameter("country", country);
		query.setParameter("zipcode", zipcode);
		List<Addresses> addresses = query.getResultList();
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Addresses findFirst(Countries country, String zipcode)
	{
		List<Addresses> addresses = find(country, zipcode);
		if (addresses != null && !addresses.isEmpty())
		{
			return addresses.get(0);
		}
		return null;
	}

}