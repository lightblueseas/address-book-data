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

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.rest.beanparams.AddressSearchCriteria;
import de.alpharogroup.service.rs.RestfulResource;

/**
 * The interface {@link AddressesResource} provides methods for resolving
 * addresses.
 */
@Path("/address/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AddressesResource extends RestfulResource<Integer, Address> {

	/**
	 * Checks if the given latitude and longitude is contained in the database.
	 *
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @return the Address
	 */
	@GET
	@Path("/contains/{latitude}/{longitude}")
	Address contains(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude);

	/**
	 * Checks if the given {@link Zipcode} is contained in the database and
	 * return the first occurence.
	 *
	 * @param zipcode
	 *            the zipcode
	 * @return the {@link Address}
	 */
	@POST
	@Path("/contains/zipcode")
	Address contains(Zipcode zipcode);

	/**
	 * Finds a list of {@link Address} from the given arguments.
	 *
	 * @param addressSearchCriteria
	 *            the address search criteria
	 * 
	 * @return the list of {@link Address}
	 */
	@POST
	@Path("/find/addresses")
	List<Address> find(AddressSearchCriteria addressSearchCriteria);

	/**
	 * Finds a list of {@link Address} from the given geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Address}
	 */
	@GET
	@Path("/geohash/{geohash}/")
	List<Address> find(@PathParam("geohash") String geohash);

	/**
	 * Finds a list of {@link Address} from the given latitude and longitude.
	 *
	 * @param latitude
	 *            the latitude
	 * @param longitude
	 *            the longitude
	 * @return the list of {@link Address}
	 */
	@GET
	@Path("/find/{latitude}/{longitude}")
	List<Address> find(@PathParam("latitude") String latitude, @PathParam("longitude") String longitude);

	/**
	 * Finds a list of {@link Address} from the given {@link Zipcode} object.
	 *
	 * @param zipcode
	 *            the zipcode
	 * @return the list of {@link Address}
	 */
	@POST
	@Path("/find/zipcodes")
	List<Address> find(Zipcode zipcode);

	/**
	 * Find all {@link Address} with the given country.
	 *
	 * @param country
	 *            the country
	 * @return the list
	 */
	@POST
	@Path("/find/addresses/by/country")
	List<Address> findAll(Country country);

	/**
	 * Find all {@link Zipcode} with the given country.
	 *
	 * @param country
	 *            the county
	 * @return the list of {@link Zipcode}
	 */
	@POST
	@Path("/find/zipcodes/by/country")
	List<Zipcode> findAllAddressesWithCountry(Country country);

	/**
	 * Finds the first {@link Address} from the given arguments.
	 *
	 * @param addressSearchCriteria
	 *            the address search criteria
	 * @return the {@link Address}
	 */
	@POST
	@Path("/find/first/by/country/and/zipcode")
	Address findFirst(AddressSearchCriteria addressSearchCriteria);

	/**
	 * Finds a list of {@link Address} from the first and second ring
	 * neighbourhood areas of the given geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Address}
	 */
	@GET
	@Path("/geohash/first/and/second/ring/{geohash}/")
	List<Address> findFirstAndSecondRingNeighbourhood(@PathParam("geohash") String geohash);

	/**
	 * Finds a list of {@link Address} from the first ring neighbourhood areas
	 * of the given geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Address}
	 */
	@GET
	@Path("/geohash/first/ring/{geohash}/")
	List<Address> findFirstRingNeighbourhood(@PathParam("geohash") String geohash);

	/**
	 * Find all addresses that have a geohash value that is null.
	 *
	 * @return the list of {@link Address}
	 */
	@POST
	@Path("/find/addresses/if/geohash/null")
	List<Address> findGeohashIsNull();

	/**
	 * Finds a list of {@link Address} from the neighbourhood areas of the given
	 * geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Address}
	 */
	@GET
	@Path("/geohash/neighbourhood/{geohash}/")
	List<Address> findNeighbourhood(@PathParam("geohash") String geohash);

}
