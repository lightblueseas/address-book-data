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
package de.alpharogroup.address.book.rest.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.service.rs.RestfulResource;

@Path("/federalstate/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface FederalstatesResource extends RestfulResource<Integer, Federalstate>
{

	/**
	 * Find the first federal state from country.
	 * 
	 * @param countryWithName
	 *            the {@link KeyValuePair} object that encapsulate the country with the name of the
	 *            federal state
	 * @return the the first federal state or null if not found.
	 */
	@POST
	@Path("/find/federalstate/country/with/name")
	Federalstate findFederalstate(final KeyValuePair<Country, String> countryWithName);

	/**
	 * Find federal state from iso3166 a2code.
	 * 
	 * @param iso3166A2code
	 *            the iso3166 a2code
	 * @return the federal states
	 */
	@GET
	@Path("/find/federalstate/{iso3166A2code}/")
	Federalstate findFederalstateFromIso3166A2code(
		@PathParam("iso3166A2code") final String iso3166A2code);

	/**
	 * Find federalstate name from iso3166 a2code.
	 * 
	 * @param iso3166A2code
	 *            the iso3166 a2code
	 * @return the string
	 */
	@GET
	@Path("/find/federalstatestring/{iso3166A2code}/")
	String findFederalstateNameFromIso3166A2code(
		@PathParam("iso3166A2code") final String iso3166A2code);

	/**
	 * Find federalstates from country.
	 * 
	 * @param country
	 *            the country
	 * @return the list
	 */
	@POST
	@Path("/find/federalstates/country")
	List<Federalstate> findFederalstatesFromCountry(final Country country);

	/**
	 * Find federal states from country.
	 * 
	 * @param countryWithName
	 *            the {@link KeyValuePair} object that encapsulate the country with the name of the
	 *            federal state
	 * @return the list of found federal states.
	 */
	@POST
	@Path("/find/federalstates/country/with/name")
	List<Federalstate> findFederalstatesFromCountry(
		final KeyValuePair<Country, String> countryWithName);

	/**
	 * Gets the Federalstate from the given String objects. Example: "country=gr.grc,
	 * stateCode=gr.a" or "country=de.deu, stateCode=de.bw"
	 *
	 * @param country
	 *            the country.
	 * @param stateCode
	 *            the optional state string code.
	 * @return the federalstate
	 */
	@GET
	@Path("/get/federalstate/{country}/{statecode}/")
	Federalstate getFederalstate(@PathParam("country") final String country,
		@PathParam("statecode") final String stateCode);
}
