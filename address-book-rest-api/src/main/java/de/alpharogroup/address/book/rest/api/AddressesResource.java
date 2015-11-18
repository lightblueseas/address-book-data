package de.alpharogroup.address.book.rest.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.service.rs.RestfulResource;

/**
 * The interface {@link AddressesResource} provides methods for resolving addresses.
 */
@Path("/address/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AddressesResource extends RestfulResource<Integer, Address>
{

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
	 * Finds a list of {@link Address} from the neighbourhood areas of the
	 * given geohash.
	 *
	 * @param geohash
	 *            the geohash
	 * @return the list of {@link Address}
	 */
	@GET
	@Path("/geohash/neighbourhood/{geohash}/")
	List<Address> findNeighbourhood(@PathParam("geohash") String geohash);
	

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
	List<Address> find(@PathParam("latitude")String latitude, @PathParam("longitude")String longitude);
	
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
	Address contains(@PathParam("latitude")String latitude, @PathParam("longitude")String longitude);

	/**
	 * Checks if the given {@link Zipcode} is contained in the database.
	 *
	 * @param zipcode
	 *            the zipcode
	 * @return the {@link Address}
	 */
	@GET
	@Path("/contains/zipcode")
	Address contains(@QueryParam("zipcode")Zipcode zipcode);
//
//	/**
//	 * Finds a list of {@link Address} from the given {@link Zipcode} object.
//	 *
//	 * @param zipcode
//	 *            the zipcode
//	 * @return the list of {@link Address}
//	 */
//	@POST
//	@Path("/contains/zipcode")
//	List<Address> find(Zipcode zipcode);
//
//	/**
//	 * Find all {@link Zipcode} with the given country.
//	 *
//	 * @param country
//	 *            the county
//	 * @return the list of {@link Zipcode}
//	 */
//	@POST
//	@Path("/find/zipcodes/by/country")
//	List<Zipcode> findAllAddressesWithCountry(Country country);
//
//	/**
//	 * Find all {@link Address} with the given country.
//	 *
//	 * @param country
//	 *            the country
//	 * @return the list
//	 */
//	@POST
//	@Path("/find/addresses/by/country")
//	List<Address> findAll(Country country);
//
//	/**
//	 * Find all addresses that have a geohash value that is null.
//	 *
//	 * @return the list of {@link Address}
//	 */
//	@POST
//	@Path("/find/addresses/if/geohash/null")
//	List<Address> findGeohashIsNull();
//
//	/**
//	 * Finds a list of {@link Address} from the given arguments.
//	 *
//	 * @param country
//	 *            the country
//	 * @param zipcode
//	 *            the zipcode
//	 * @return the list of {@link Address}
//	 */
//	List<Address> find(Country country, String zipcode);
//
//	/**
//	 * Finds a list of {@link Address} from the given arguments.
//	 *
//	 * @param country
//	 *            the country
//	 * @param zipcode
//	 *            the zipcode
//	 * @param city
//	 *            the city
//	 * @return the list of {@link Address}
//	 */
//	List<Address> find(Country country, String zipcode, String city);
//
//	/**
//	 * Finds the first {@link Address} from the given arguments.
//	 *
//	 * @param country
//	 *            the country
//	 * @param zipcode
//	 *            the zipcode
//	 * @return the {@link Address}
//	 */
//	Address findFirst(Country country, String zipcode);
//
//	/**
//	 * Find invalid {@link Address} objects from the given arguments.
//	 *
//	 * @param country
//	 *            the country
//	 * @param geohash
//	 *            the geohash
//	 * @return the list
//	 */
//	List<Address> findInvalidAddresses(Country country, String geohash);
//
//	/**
//	 * Find invalid {@link Address} objects from the given arguments.
//	 *
//	 * @param country
//	 *            the country
//	 * @param geohash
//	 *            the geohash
//	 * @param not
//	 *            the flag that indicates a negation from the geohash value
//	 * @return the list of {@link Address}
//	 */
//	List<Address> findInvalidAddresses(Country country, String geohash, boolean not);
//
//	/**
//	 * Finds a list of {@link Address} that have the same cityname.
//	 *
//	 * @param country
//	 *            the country
//	 * @param city
//	 *            the city
//	 * @return the list of {@link Address}
//	 */
//	List<Address> findAddressesWithSameCityname(Country country, String city);
//
//	/**
//	 * Finds a list of {@link Address} that have the same zipcode.
//	 *
//	 * @param country
//	 *            the country
//	 * @param zipcode
//	 *            the zipcode
//	 * @return the list of {@link Address}
//	 */
//	List<Address> findAddressesWithSameZipcode(Country country, String zipcode);
}
