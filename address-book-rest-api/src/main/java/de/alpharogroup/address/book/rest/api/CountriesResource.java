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
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.domain.model.AddressSearchModel;
import de.alpharogroup.collections.pairs.KeyValuesPair;
import de.alpharogroup.service.rs.RestfulResource;

@Path("/country/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CountriesResource extends RestfulResource<Integer, Country>
{

	/**
	 * Find the {@link Country} object from the given ISO 3166 {@link String} object.
	 *
	 * @param iso3166A2name
	 *            the iso3166 a2name
	 * @return the {@link Country} object
	 */
	@GET
	@Path("/find/by/isoa2/{iso3166A2name}")
	Country find(String iso3166A2name);

	/**
	 * Find all {@link Country} from the given arguments.
	 *
	 * @param iso3166A2name
	 *            the iso3166 a2name
	 * @param iso3166A3name
	 *            the iso3166 a3name
	 * @param iso3166Number
	 *            the iso3166 number
	 * @param name
	 *            the name
	 * @return the list of {@link Country}
	 */
	@GET
	@Path("/find/{iso3166A2name}/{iso3166A3name}/{iso3166Number}/{name}")
	List<Country> findAll(@PathParam("iso3166A2name") String iso3166A2name,
		@PathParam("iso3166A3name") String iso3166A3name,
		@PathParam("iso3166Number") String iso3166Number, @PathParam("name") String name);

	/**
	 * Find the {@link Country} object by name.
	 *
	 * @param name
	 *            the name
	 * @return the countries
	 */
	@GET
	@Path("/find/by/name/{iso3166A2name}")
	Country findByName(String name);

	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as value the
	 * corresponding federal states as a List of Iso3166A2code String objects.
	 * 
	 * @return the countries to federalstates as string list
	 */
	@GET
	@Path("/get/country2federalstate/stringlist/")
	List<KeyValuesPair<String, String>> getCountriesToFederalstatesAsStringList();

	/**
	 * Gets a list with the mapping-class: the key as Country object and as value the corresponding
	 * federal states as a List of Federalstate objects.
	 * 
	 * @return the countries to federalstates list
	 */
	@GET
	@Path("/get/country2federalstate/list/")
	List<KeyValuesPair<Country, Federalstate>> getCountriesToFederalstatesList();

	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as value the
	 * corresponding zipcodes and cities as a List of String objects.
	 *
	 * @return the countries to zipcodes and cities as string list
	 */
	@GET
	@Path("/get/country2zipcodesandcities/stringlist/")
	List<KeyValuesPair<String, String>> getCountriesToZipcodesAndCitiesAsStringList();

	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as value the
	 * corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string list
	 */
	@GET
	@Path("/get/country2zipcodes/stringlist/")
	List<KeyValuesPair<String, String>> getCountriesToZipcodesAsStringList();

	/**
	 * Gets a list with the mapping-class: the key as Country object and as value the corresponding
	 * Zipcodes as a List of Zipcode objects.
	 *
	 * @return the countries to zipcodes list
	 */
	@GET
	@Path("/get/country2zipcodes/list/")
	List<KeyValuesPair<Country, Zipcode>> getCountriesToZipcodesList();

	/**
	 * Gets the german countries to zipcodes and cities as string list.
	 *
	 * @return the german countries to zipcodes and cities as string list
	 */
	@GET
	@Path("/get/germancountry2zipcodesandcities/stringlist/")
	List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAndCitiesAsStringList();

	/**
	 * Gets the german countries to zipcodes as string list.
	 *
	 * @return the german countries to zipcodes as string list
	 */
	@GET
	@Path("/get/germancountry2zipcodes/stringlist/")
	List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAsStringList();

	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as value the
	 * corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string list for german speeking countries only
	 */
	@GET
	@Path("/get/germancountry2zipcodes/list/")
	List<KeyValuesPair<Country, Zipcode>> getGermanCountriesToZipcodesList();

	/**
	 * Sets the given {@link AddressSearchModel} object and returns it.
	 *
	 * @param modelObject
	 *            the model object
	 * 
	 * @return the modified {@link AddressSearchModel} object.
	 */
	@POST
	@Path("/resolve/location")
	AddressSearchModel setLocationSearchModel(AddressSearchModel modelObject);
}
