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

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.rest.api.FederalstatesResource;
import de.alpharogroup.address.book.service.api.FederalstateService;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link FederalstatesRestResource} provides an implementation of the inteface
 * {@link FederalstatesResource}.
 */
public class FederalstatesRestResource
	extends
		AbstractRestfulResource<Integer, Federalstate, FederalstateService>
	implements
		FederalstatesResource
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate findFederalstate(final KeyValuePair<Country, String> countryWithName)
	{
		return getDomainService().findFederalstate(countryWithName.getKey(),
			countryWithName.getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate findFederalstateFromIso3166A2code(String iso3166a2code)
	{
		return getDomainService().findFederalstateFromIso3166A2code(iso3166a2code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String findFederalstateNameFromIso3166A2code(String iso3166a2code)
	{
		return getDomainService().findFederalstateNameFromIso3166A2code(iso3166a2code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Federalstate> findFederalstatesFromCountry(Country country)
	{
		return getDomainService().findFederalstatesFromCountry(country);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Federalstate> findFederalstatesFromCountry(
		final KeyValuePair<Country, String> countryWithName)
	{
		return getDomainService().findFederalstatesFromCountry(countryWithName.getKey(),
			countryWithName.getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate getFederalstate(String country, String stateCode)
	{
		return getDomainService().getFederalstate(country, stateCode);
	}
}
