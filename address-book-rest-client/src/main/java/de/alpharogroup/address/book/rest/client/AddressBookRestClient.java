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
package de.alpharogroup.address.book.rest.client;

import de.alpharogroup.address.book.rest.api.AddressesResource;
import de.alpharogroup.address.book.rest.api.CountriesResource;
import de.alpharogroup.address.book.rest.api.FederalstatesResource;
import de.alpharogroup.address.book.rest.api.ZipcodesResource;
import de.alpharogroup.cxf.rest.client.AbstractRestClient;
import lombok.Getter;

/**
 * The class {@link AddressBookRestClient} is a rest client for accessing the
 * rest services from the address-book database.
 */
public class AddressBookRestClient extends AbstractRestClient {

	/**
	 * The {@link AddressesResource}.
	 */
	@Getter
	private final AddressesResource addressesResource;

	/**
	 * The {@link CountriesResource}.
	 */
	@Getter
	private final CountriesResource countriesResource;

	/**
	 * The {@link FederalstatesResource}.
	 */
	@Getter
	private final FederalstatesResource federalstatesResource;

	/**
	 * The {@link ZipcodesResource}.
	 */
	@Getter
	private final ZipcodesResource zipcodesResource;

	/**
	 * Instantiates a new {@link AddressBookRestClient} with the default base
	 * url.
	 */
	public AddressBookRestClient() {
		this(DEFAULT_BASE_HTTP_URL);
	}

	/**
	 * Instantiates a new {@link AddressBookRestClient}.
	 *
	 * @param baseUrl
	 *            the base url
	 */
	public AddressBookRestClient(final String baseUrl) {
		super(baseUrl);
		addressesResource = newResource(AddressesResource.class);

		countriesResource = newResource(CountriesResource.class);

		federalstatesResource = newResource(FederalstatesResource.class);

		zipcodesResource = newResource(ZipcodesResource.class);
	}

}
