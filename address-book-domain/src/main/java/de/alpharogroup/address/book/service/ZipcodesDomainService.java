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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.ZipcodesDao;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.mapper.ZipcodesMapper;
import de.alpharogroup.address.book.service.api.ZipcodeService;
import de.alpharogroup.address.book.service.api.ZipcodesService;
import de.alpharogroup.service.domain.AbstractDomainService;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link ZipcodesDomainService}.
 */
@Transactional
@Service("zipcodesDomainService")
public class ZipcodesDomainService extends
		AbstractDomainService<Integer, Zipcode, Zipcodes, ZipcodesDao, ZipcodesMapper> implements ZipcodeService {

	/** The {@link ZipcodesService}. */
	@Autowired
	@Getter
	@Setter
	private ZipcodesService zipcodesService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAllZipcodes() {
		zipcodesService.deleteAllZipcodes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsZipcode(String zipcode) {
		return zipcodesService.existsZipcode(zipcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> find(Country country) {
		List<Zipcode> zcs = new ArrayList<>();
		if (country != null) {
			Countries countries = getMapper().map(country, Countries.class);
			return getMapper().toDomainObjects(zipcodesService.find(countries));
		}
		return zcs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findAll(Country country, String zipcode, String city) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(zipcodesService.findAll(c, zipcode, city));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zipcode findCityFromZipcode(Country country, String zipcode) {
		return getMapper().toDomainObject(
				zipcodesService.findCityFromZipcode(getMapper().map(country, Countries.class), zipcode));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findZipcodes(String zipcode) {
		return getMapper().toDomainObjects(zipcodesService.findZipcodes(zipcode));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zipcode getZipcode(String zipcode, String city) {
		return getMapper().toDomainObject(zipcodesService.getZipcode(zipcode, city));
	}

	/**
	 * Sets the specific {@link ZipcodesDao}.
	 *
	 * @param zipcodesDao
	 *            the new {@link ZipcodesDao}.
	 */
	@Autowired
	public void setZipcodesDao(ZipcodesDao zipcodesDao) {
		setDao(zipcodesDao);
	}

	/**
	 * Sets the specific {@link ZipcodesMapper}.
	 *
	 * @param mapper
	 *            the new {@link ZipcodesMapper}.
	 */
	@Autowired
	public void setZipcodesMapper(ZipcodesMapper mapper) {
		setMapper(mapper);
	}
}
