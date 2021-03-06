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
package de.alpharogroup.address.book.service.locator;

import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.address.book.service.api.ZipcodesService;

public interface ServiceLocator
{
	/**
	 * Gets the addresses business service.
	 * 
	 * @return the addresses business service
	 */
	AddressesService getAddressesService();

	/**
	 * Gets the countries business service.
	 * 
	 * @return the countries business service
	 */
	CountriesService getCountriesService();

	/**
	 * Gets the federalstates business service.
	 * 
	 * @return the federalstates business service
	 */
	FederalstatesService getFederalstatesService();

	/**
	 * Gets the zipcodes business service.
	 * 
	 * @return the zipcodes business service
	 */
	ZipcodesService getZipcodesService();

	/**
	 * Sets the addresses business service.
	 * 
	 * @param addressesService
	 *            the new addresses business service
	 */
	void setAddressesService(AddressesService addressesService);

	/**
	 * Sets the countries business service.
	 * 
	 * @param countriesService
	 *            the new countries business service
	 */
	void setCountriesService(CountriesService countriesService);

	/**
	 * Sets the federalstates business service.
	 * 
	 * @param federalstatesService
	 *            the new federalstates business service
	 */
	void setFederalstatesService(FederalstatesService federalstatesService);

	/**
	 * Sets the zipcodes business service.
	 * 
	 * @param zipcodesService
	 *            the new zipcodes business service
	 */
	void setZipcodesService(ZipcodesService zipcodesService);

}
