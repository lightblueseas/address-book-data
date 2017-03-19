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
import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;

/**
 * The class {@link ZipcodesBusinessService}.
 */
@Transactional
@Service("zipcodesService")
public class ZipcodesBusinessService extends AbstractBusinessService<Zipcodes, Integer, ZipcodesDao>
		implements ZipcodesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public void deleteAllZipcodes() {
		final List<Zipcodes> zipcodes = this.findAll();
		for (final Iterator<Zipcodes> iterator = zipcodes.iterator(); iterator.hasNext();) {
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
	public List<Zipcodes> find(final Countries country) {
		return findAll(country, null, null);
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Zipcodes> findAll(final Countries country, final String zipcode, final String city) {
		final String hqlString = HqlStringCreator.forZipcodes(country, zipcode, city);
		final Query query = getQuery(hqlString);
		if (country != null) {
			query.setParameter("country", country);
		}
		if (zipcode != null && !zipcode.isEmpty()) {
			query.setParameter("zipcode", zipcode);
		}
		if (city != null && !city.isEmpty()) {
			query.setParameter("city", city);

		}
		final List<Zipcodes> zipcodes = query.getResultList();
		return zipcodes;
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public Zipcodes findCityFromZipcode(final Countries country, final String zipcode) {
		final List<Zipcodes> zipcodes = findAll(country, zipcode, null);
		return ListExtensions.getFirst(zipcodes);

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
	public Zipcodes getZipcode(final String zipcode, final String city) {
		final List<Zipcodes> zipcodes = findZipcodes(zipcode);
		Zipcodes zc = ListExtensions.getFirst(zipcodes);
		if (zc == null) {
			zc = AddressBookFactory.getInstance().newZipcodes(null, null, city, zipcode);
		}
		return zc;
	}

	/**
	 * Sets the specific {@link ZipcodesDao}.
	 *
	 * @param zipcodesDao
	 *            the new {@link ZipcodesDao}.
	 */
	@Autowired
	public void setZipcodesDao(final ZipcodesDao zipcodesDao) {
		setDao(zipcodesDao);
	}

}