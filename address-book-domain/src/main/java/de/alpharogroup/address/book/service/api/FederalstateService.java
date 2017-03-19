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
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.service.domain.DomainService;

public interface FederalstateService extends DomainService<Integer, Federalstate> {

	/**
	 * Find the first federal state from country.
	 * 
	 * @param country
	 *            the country
	 * @param name
	 *            the name of the federal state
	 * @return the the first federal state or null if not found.
	 */
	Federalstate findFederalstate(final Country country, String name);

	/**
	 * Find federal state from iso3166 a2code.
	 * 
	 * @param iso3166A2code
	 *            the iso3166 a2code
	 * @return the federal states
	 */
	Federalstate findFederalstateFromIso3166A2code(final String iso3166A2code);

	/**
	 * Find federalstate name from iso3166 a2code.
	 * 
	 * @param iso3166A2code
	 *            the iso3166 a2code
	 * @return the string
	 */
	String findFederalstateNameFromIso3166A2code(final String iso3166A2code);

	/**
	 * Find federalstates from country.
	 * 
	 * @param country
	 *            the country
	 * @return the list
	 */
	List<Federalstate> findFederalstatesFromCountry(final Country country);

	/**
	 * Find federal states from country.
	 * 
	 * @param country
	 *            the country
	 * @param name
	 *            the name of the federal state
	 * @return the list of found federal states.
	 */
	List<Federalstate> findFederalstatesFromCountry(final Country country, String name);

	/**
	 * Gets the Federalstate from the given String object. Example for the
	 * format for the given String is(without the double quotes):
	 * "gr.grc=&gt;gr.a" or "de.deu=&gt;de.bw"
	 *
	 * @param string
	 *            a concat string from country and an optional state string
	 *            code.
	 * @return the federalstate
	 * @deprecated use instead
	 *             {@link FederalstateService#getFederalstate(String, String)}
	 */
	@Deprecated
	Federalstate getFederalstate(final String string);

	/**
	 * Gets the Federalstate from the given String objects. Example:
	 * "country=gr.grc, stateCode=gr.a" or "country=de.deu, stateCode=de.bw"
	 *
	 * @param country
	 *            the country.
	 * @param stateCode
	 *            the optional state string code.
	 * @return the federalstate
	 */
	Federalstate getFederalstate(final String country, final String stateCode);
}
