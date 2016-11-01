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
	
	
}
