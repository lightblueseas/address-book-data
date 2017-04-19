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
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.domain.model.AddressSearchModel;
import de.alpharogroup.address.book.rest.api.CountriesResource;
import de.alpharogroup.address.book.service.api.CountryService;
import de.alpharogroup.collections.pairs.KeyValuesPair;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link CountriesRestResource} provides an implementation of the inteface
 * {@link CountriesResource}.
 */
public class CountriesRestResource extends AbstractRestfulResource<Integer, Country, CountryService>
	implements
		CountriesResource
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Country find(String iso3166a2name)
	{
		return getDomainService().find(iso3166a2name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Country> findAll(String iso3166a2name, String iso3166a3name, String iso3166Number,
		String name)
	{
		return getDomainService().findAll(iso3166a2name, iso3166a3name, iso3166Number, name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Country findByName(String name)
	{
		return getDomainService().findByName(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getCountriesToFederalstatesAsStringList()
	{
		return getDomainService().getCountriesToFederalstatesAsStringList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<Country, Federalstate>> getCountriesToFederalstatesList()
	{
		return getDomainService().getCountriesToFederalstatesList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getCountriesToZipcodesAndCitiesAsStringList()
	{
		return getDomainService().getCountriesToZipcodesAndCitiesAsStringList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getCountriesToZipcodesAsStringList()
	{
		return getDomainService().getCountriesToZipcodesAsStringList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<Country, Zipcode>> getCountriesToZipcodesList()
	{
		return getDomainService().getCountriesToZipcodesList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAndCitiesAsStringList()
	{
		return getDomainService().getGermanCountriesToZipcodesAndCitiesAsStringList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAsStringList()
	{
		return getDomainService().getGermanCountriesToZipcodesAsStringList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<Country, Zipcode>> getGermanCountriesToZipcodesList()
	{
		return getDomainService().getGermanCountriesToZipcodesList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AddressSearchModel setLocationSearchModel(AddressSearchModel modelObject)
	{
		return getDomainService().setLocationSearchModel(modelObject);
	}

}
