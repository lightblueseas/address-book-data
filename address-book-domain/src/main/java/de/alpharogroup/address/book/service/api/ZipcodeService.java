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
package de.alpharogroup.address.book.service.api;

import java.util.List;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.service.domain.DomainService;

/**
 * The interface {@link ZipcodeService}.
 */
public interface ZipcodeService extends DomainService<Integer, Zipcode> {

	/**
	 * Delete all zipcodes.
	 */
	void deleteAllZipcodes();

	/**
	 * Checks if the given zipcode string exists.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @return true, if successful
	 */
	boolean existsZipcode(final String zipcode);

	/**
	 * Finds all {@link Zipcode} from the given {@link Country} object.
	 *
	 * @param country
	 *            the country
	 * @return the list of {@link Zipcode}
	 */
	List<Zipcode> find(final Country country);

	/**
	 * Gets a List of {@link Zipcode} with the given parameters that can be null
	 * if they shell be ignored in the query.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @return the list of {@link Zipcode}
	 */
	List<Zipcode> findAll(Country country, String zipcode, String city);

	/**
	 * Find the {@link Zipcode} to resolve the city from the given
	 * {@link Country} object and zipcode string.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @return the {@link Zipcode}
	 */
	Zipcode findCityFromZipcode(Country country, String zipcode);

	/**
	 * Find {@link Zipcode} objects from the given zipcode string.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @return the list of {@link Zipcode}
	 */
	List<Zipcode> findZipcodes(final String zipcode);

	/**
	 * Gets the {@link Zipcode} object from the given zipcode string and city.
	 * If it does not exist it will be create a new {@link Zipcode} object.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @return the {@link Zipcode}
	 */
	Zipcode getZipcode(final String zipcode, final String city);
}
