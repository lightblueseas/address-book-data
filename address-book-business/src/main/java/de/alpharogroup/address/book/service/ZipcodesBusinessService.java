package de.alpharogroup.address.book.service;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.ZipcodesDao;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.factories.AddressBookFactory;
import de.alpharogroup.address.book.service.api.ZipcodesService;
import de.alpharogroup.address.book.service.util.HqlStringCreator;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;

/**
 * The class {@link ZipcodesBusinessService}.
 */
@Transactional
@Service("zipcodesService")
public class ZipcodesBusinessService extends AbstractBusinessService<Zipcodes, Integer, ZipcodesDao> implements ZipcodesService {


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Sets the specific {@link ZipcodesDao}.
	 *
	 * @param countriesDao the new {@link ZipcodesDao}.
	 */
	@Autowired
	public void setZipcodesDao(ZipcodesDao zipcodesDao) {
		setDao(zipcodesDao);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public void deleteAllZipcodes() {
		final List<Zipcodes> zipcodes = this.findAll();
		for (final Iterator<Zipcodes> iterator = zipcodes.iterator(); iterator
				.hasNext();) {
			final Zipcodes zipcode = iterator.next();
			delete(zipcode);
		}
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public boolean existsZipcode(final String zipcode) {
		final List<Zipcodes> zipcodes = findAll(null, zipcode, null);
		findAll(null, zipcode, null);
		if (zipcodes != null && !zipcodes.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public List<Zipcodes> findZipcodes(final String zipcode) {
		return findAll(null, zipcode, null);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Zipcodes getZipcode(String zipcode, final String city) {
		final Zipcodes zc;
		final List<Zipcodes> zipcodes = findZipcodes(zipcode);
		if (zipcodes != null && !zipcodes.isEmpty()) {
			zc = zipcodes.get(0);
		} else {
			zc = AddressBookFactory.getInstance().newZipcodes(null, null, city,
					zipcode);
		}
		return zc;
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public List<Zipcodes> find(final Countries country) {
		return findAll(country, null, null);		
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Zipcodes findCityFromZipcode(Countries country, String zipcode) {
		List<Zipcodes> zipcodes = findAll(country, zipcode, null);
		if(zipcodes != null && !zipcodes.isEmpty()){
			return zipcodes.get(0);
		}
		return null;
		
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Zipcodes> findAll(Countries country, String zipcode, String city) {
		String c = country == null ? null : country.getName();
		final String hqlString = HqlStringCreator.forZipcodes(c, zipcode, city);
		final Query query = getQuery(hqlString);
		if(country != null){
			query.setParameter("country", country);			
		}
		if(zipcode != null && !zipcode.isEmpty()){
			query.setParameter("zipcode", zipcode);			
		}
		if(city != null && !city.isEmpty()){
			query.setParameter("city", city);
			
		}
		List<Zipcodes> zipcodes = query.getResultList();
		return zipcodes;
	}
	
}