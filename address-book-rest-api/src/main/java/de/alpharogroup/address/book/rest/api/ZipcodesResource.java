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
import javax.ws.rs.core.Response;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.service.rs.RestfulResource;

@Path("/zipcode/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ZipcodesResource extends RestfulResource<Integer, Zipcode> {

	/**
	 * Checks if the given zipcode string exists.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @return true, if successful
	 */
	@GET
	@Path("/exists/{zipcode}/")
	Response existsZipcode(@PathParam("zipcode") final String zipcode);

	/**
	 * Finds all {@link Zipcode} from the given {@link Country} object.
	 *
	 * @param country
	 *            the country
	 * @return the list of {@link Zipcode}
	 */
	@POST
	@Path("/find/by/country")
	List<Zipcode> find(final Country country);

	/**
	 * Gets a List of {@link Zipcode} with the given parameters that can be null
	 * if they shell be ignored in the query.
	 *
	 * @param searchCriteria
	 *            the search criteria
	 * @return the list of {@link Zipcode} objects
	 */
	@POST
	@Path("/find/all")
	List<Zipcode> findAll(Triple<Country, String, String> searchCriteria);

	/**
	 * Find the {@link Zipcode} to resolve the city from the given
	 * {@link Country} object and zipcode string encapsulated as a
	 * {@link KeyValuePair} object as parameter.
	 *
	 * @param countryWithZipcode
	 *            the {@link KeyValuePair} object that encapsulate the country
	 *            with the zipcode
	 * @return the {@link Zipcode} object
	 */
	@POST
	@Path("/find/city/from/zipcode")
	Zipcode findCityFromZipcode(final KeyValuePair<Country, String> countryWithZipcode);

	/**
	 * Find {@link Zipcode} objects from the given zipcode string.
	 * 
	 * @param zipcode
	 *            the zipcode
	 * @return the list of {@link Zipcode}
	 */
	@GET
	@Path("/find/{zipcode}/")
	List<Zipcode> findZipcodes(@PathParam("zipcode") final String zipcode);

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
	@GET
	@Path("/get/{zipcode}/{city}")
	Zipcode getZipcode(@PathParam("zipcode") final String zipcode, @PathParam("city") final String city);

}
