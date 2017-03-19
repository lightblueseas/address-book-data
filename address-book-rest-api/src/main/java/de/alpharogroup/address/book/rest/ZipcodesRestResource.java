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

import javax.ws.rs.core.Response;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.rest.api.ZipcodesResource;
import de.alpharogroup.address.book.service.api.ZipcodeService;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link ZipcodesRestResource} provides an implementation of the
 * inteface {@link ZipcodesResource}.
 */
public class ZipcodesRestResource extends AbstractRestfulResource<Integer, Zipcode, ZipcodeService>
		implements ZipcodesResource {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response existsZipcode(String zipcode) {
		return Response.ok(getDomainService().existsZipcode(zipcode)).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> find(final Country country) {
		return getDomainService().find(country);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findAll(Triple<Country, String, String> searchCriteria) {
		return getDomainService().findAll(searchCriteria.getLeft(), searchCriteria.getMiddle(),
				searchCriteria.getRight());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zipcode findCityFromZipcode(KeyValuePair<Country, String> countryWithZipcode) {
		return getDomainService().findCityFromZipcode(countryWithZipcode.getKey(), countryWithZipcode.getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findZipcodes(String zipcode) {
		return getDomainService().findZipcodes(zipcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zipcode getZipcode(String zipcode, String city) {
		return getDomainService().getZipcode(zipcode, city);
	}

}
