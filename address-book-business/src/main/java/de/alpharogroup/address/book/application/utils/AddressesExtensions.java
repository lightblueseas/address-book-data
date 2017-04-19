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
package de.alpharogroup.address.book.application.utils;

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Zipcodes;

/**
 * The class {@link AddressesExtensions}.
 */
public class AddressesExtensions
{

	/**
	 * Gets the location in the format first 2 character from zipcode plus the city.
	 *
	 * @param address
	 *            the address
	 * @return the location
	 */
	public static String getFullLocation(Addresses address)
	{
		String locaction = null;
		if (address != null)
		{
			Zipcodes zc = address.getZipcode();
			if (zc != null)
			{
				locaction = zc.getZipcode().trim() + " " + zc.getCity().trim();
			}
		}
		return locaction;
	}

	/**
	 * Gets the location in the format first 2 character from zipcode plus the city.
	 *
	 * @param address
	 *            the address
	 * @return the location
	 */
	public static String getLocation(Addresses address)
	{
		String locaction = null;
		if (address != null)
		{
			Zipcodes zc = address.getZipcode();
			if (zc != null)
			{
				locaction = zc.getZipcode().substring(0, 2) + "* " + zc.getCity();
			}
		}
		return locaction;
	}

}
