package de.alpharogroup.address.book.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.service.rs.RestfulResource;

@Path("/zipcode/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ZipcodesResource extends RestfulResource<Integer, Zipcode>
{

	/**
	 * Gets a List of {@link Zipcode} with the given parameters that can be null if
	 * they shell be ignored in the query.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @return the list of {@link Zipcode}
	 */
	@GET
	@Path("/find/{country}/{zipcode}/{city}/")
	List<Zipcode> find(@PathParam("country") String country, @PathParam("zipcode") String zipcode, @PathParam("city") String city);

	/**
	 * Gets a List of {@link Zipcode} with the given parameters that can be null if
	 * they shell be ignored in the query.
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
	 * Delete all zipcodes.
	 */
	@GET
	@Path("/deleteAllZipcodes/")
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

	/**
	 * Finds all {@link Zipcode} from the given {@link Country} object.
	 *
	 * @param country
	 *            the country
	 * @return the list of {@link Zipcode}
	 */
	List<Zipcode> find(final Country country);

	/**
	 * Find the {@link Zipcode} to resolve the city from the given {@link Country} object and zipcode string.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @return the {@link Zipcode}
	 */
	Zipcode findCityFromZipcode(Country country, String zipcode);
}
