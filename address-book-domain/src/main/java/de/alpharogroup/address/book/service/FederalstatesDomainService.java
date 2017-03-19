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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.FederalstatesDao;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.mapper.FederalstatesMapper;
import de.alpharogroup.address.book.service.api.FederalstateService;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.service.domain.AbstractDomainService;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link FederalstatesDomainService}.
 */
@Transactional
@Service("federalstatesDomainService")
public class FederalstatesDomainService
		extends AbstractDomainService<Integer, Federalstate, Federalstates, FederalstatesDao, FederalstatesMapper>
		implements FederalstateService {

	/** The {@link FederalstatesService}. */
	@Autowired
	@Getter
	@Setter
	private FederalstatesService federalstatesService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate findFederalstate(Country country, String name) {
		return getMapper().toDomainObject(
				federalstatesService.findFederalstate(getMapper().getMapper().map(country, Countries.class), name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate findFederalstateFromIso3166A2code(final String iso3166a2code) {
		return getMapper().toDomainObject(federalstatesService.findFederalstateFromIso3166A2code(iso3166a2code));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String findFederalstateNameFromIso3166A2code(String iso3166a2code) {
		return federalstatesService.findFederalstateNameFromIso3166A2code(iso3166a2code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Federalstate> findFederalstatesFromCountry(Country country) {
		Countries c = getMapper().getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(federalstatesService.findFederalstatesFromCountry(c));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Federalstate> findFederalstatesFromCountry(Country country, String name) {
		Countries c = getMapper().getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(federalstatesService.findFederalstatesFromCountry(c, name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Deprecated
	@Override
	public Federalstate getFederalstate(String string) {
		return getMapper().toDomainObject(federalstatesService.getFederalstate(string));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate getFederalstate(String country, String stateCode) {
		return getMapper().toDomainObject(federalstatesService.getFederalstate(country, stateCode));
	}

	/**
	 * Sets the specific {@link FederalstatesDao}.
	 *
	 * @param federalstatesDao
	 *            the new {@link FederalstatesDao}.
	 */
	@Autowired
	public void setFederalstatesDao(final FederalstatesDao federalstatesDao) {
		setDao(federalstatesDao);
	}

	/**
	 * Sets the specific {@link FederalstatesMapper}.
	 *
	 * @param mapper
	 *            the new {@link FederalstatesMapper}.
	 */
	@Autowired
	public void setFederalstatesMapper(FederalstatesMapper mapper) {
		setMapper(mapper);
	}

}
