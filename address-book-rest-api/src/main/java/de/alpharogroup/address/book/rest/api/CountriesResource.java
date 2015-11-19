package de.alpharogroup.address.book.rest.api;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.service.rs.RestfulResource;

@Path("/country/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CountriesResource extends RestfulResource<Integer, Country>
{

	/**
	 * Gets a map with the mapping: the key as Country object and as value the
	 * corresponding federal states as a List of Federalstate objects.
	 * 
	 * @return the countries to federalstates map
	 */
	@POST
	@Path("/to/federalstates/")
	Map<Country, List<Federalstate>> getCountriesToFederalstatesMap();

	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding federal states as a List of Iso3166A2code String objects.
	 * 
	 * @return the countries to federalstates as string map
	 */
	Map<String, List<String>> getCountriesToFederalstatesAsStringMap();
	
	/**
	 * Gets a map with the mapping: the key as Country object and as value the
	 * corresponding Zipcodes as a List of Zipcode objects.
	 *
	 * @return the countries to zipcodes map
	 */
	Map<Country, List<Zipcode>> getCountriesToZipcodesMap();
	
	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string map
	 */
	Map<String, List<String>> getCountriesToZipcodesAsStringMap();
	
	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string map for german speeking countries only
	 */
	Map<Country, List<Zipcode>> getGermanCountriesToZipcodesMap();
	
	/**
	 * Gets the german countries to zipcodes as string map.
	 *
	 * @return the german countries to zipcodes as string map
	 */
	Map<String, List<String>> getGermanCountriesToZipcodesAsStringMap();
	
	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding zipcodes and cities as a List of String objects.
	 *
	 * @return the countries to zipcodes and cities as string map
	 */
	Map<String, List<String>> getCountriesToZipcodesAndCitiesAsStringMap();
	
	/**
	 * Gets the german countries to zipcodes and cities as string map.
	 *
	 * @return the german countries to zipcodes and cities as string map
	 */
	Map<String, List<String>> getGermanCountriesToZipcodesAndCitiesAsStringMap();
	
	/**
	 * Find all {@link Country} from the given arguments.
	 *
	 * @param iso3166A2name the iso3166 a2name
	 * @param iso3166A3name the iso3166 a3name
	 * @param iso3166Number the iso3166 number
	 * @param name the name
	 * @return the list of {@link Country}
	 */
	List<Country> findAll(String iso3166A2name, String iso3166A3name,
			String iso3166Number, String name);
	
	/**
	 * Find the {@link Country} object from the given ISO 3166 {@link String} object.
	 *
	 * @param iso3166A2name the iso3166 a2name
	 * @return the {@link Country} object
	 */
	Country find(String iso3166A2name);
	
	/**
	 * Find the {@link Country} object by name.
	 *
	 * @param name the name
	 * @return the countries
	 */
	Country findByName(String name);
	
	/**
	 * Sets the location model.
	 *
	 * @param modelObject the model object
	 * @param zc the zipcode as string
	 * @return null if everything is ok otherwise an error property string
	 */
	String setLocationModel(LocationModel<Address> modelObject, String zc);
}
