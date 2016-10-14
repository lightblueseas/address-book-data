package de.alpharogroup.address.book.rest.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.alpharogroup.address.book.application.model.LocationSearchModel;
import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.collections.pairs.KeyValuesPair;
import de.alpharogroup.service.rs.RestfulResource;

@Path("/country/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface CountriesResource extends RestfulResource<Integer, Country>
{

	/**
	 * Gets a list with the mapping-class: the key as Country object and as value the
	 * corresponding federal states as a List of Federalstate objects.
	 * 
	 * @return the countries to federalstates list
	 */
	@GET
	@Path("/get/country2federalstate/list/")
	List<KeyValuesPair<Country, Federalstate>> getCountriesToFederalstatesList();

	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as
	 * value the corresponding federal states as a List of Iso3166A2code String objects.
	 * 
	 * @return the countries to federalstates as string list
	 */
	@GET
	@Path("/get/country2federalstate/stringlist/")
	List<KeyValuesPair<String, String>> getCountriesToFederalstatesAsStringList();
		
	/**
	 * Gets a list with the mapping-class: the key as Country object and as value the
	 * corresponding Zipcodes as a List of Zipcode objects.
	 *
	 * @return the countries to zipcodes list
	 */
	@GET
	@Path("/get/country2zipcodes/list/")
	List<KeyValuesPair<Country, Zipcode>> getCountriesToZipcodesList();	

	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as
	 * value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string list
	 */
	@GET
	@Path("/get/country2zipcodes/stringlist/")
	List<KeyValuesPair<String, String>> getCountriesToZipcodesAsStringList();
		
	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as
	 * value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string list for german speeking countries only
	 */
	@GET
	@Path("/get/germancountry2zipcodes/list/")
	List<KeyValuesPair<Country, Zipcode>> getGermanCountriesToZipcodesList();
	
	/**
	 * Gets the german countries to zipcodes as string list.
	 *
	 * @return the german countries to zipcodes as string list
	 */
	@GET
	@Path("/get/germancountry2zipcodes/stringlist/")
	List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAsStringList();
	
	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as
	 * value the corresponding zipcodes and cities as a List of String objects.
	 *
	 * @return the countries to zipcodes and cities as string list
	 */
	@GET
	@Path("/get/country2zipcodesandcities/stringlist/")
	List<KeyValuesPair<String, String>> getCountriesToZipcodesAndCitiesAsStringList();
	
	/**
	 * Gets the german countries to zipcodes and cities as string list.
	 *
	 * @return the german countries to zipcodes and cities as string list
	 */
	@GET
	@Path("/get/germancountry2zipcodesandcities/stringlist/")
	List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAndCitiesAsStringList();
	
	/**
	 * Find all {@link Country} from the given arguments.
	 *
	 * @param iso3166A2name the iso3166 a2name
	 * @param iso3166A3name the iso3166 a3name
	 * @param iso3166Number the iso3166 number
	 * @param name the name
	 * @return the list of {@link Country}
	 */
	@GET
	@Path("/find/{iso3166A2name}/{iso3166A3name}/{iso3166Number}/{name}")
	List<Country> findAll(@PathParam("iso3166A2name")String iso3166A2name, @PathParam("iso3166A3name")String iso3166A3name,
			@PathParam("iso3166Number")String iso3166Number, @PathParam("name")String name);
	
	/**
	 * Find the {@link Country} object from the given ISO 3166 {@link String} object.
	 *
	 * @param iso3166A2name the iso3166 a2name
	 * @return the {@link Country} object
	 */
	@GET
	@Path("/find/by/isoa2/{iso3166A2name}")
	Country find(String iso3166A2name);
	
	/**
	 * Find the {@link Country} object by name.
	 *
	 * @param name the name
	 * @return the countries
	 */
	@GET
	@Path("/find/by/name/{iso3166A2name}")
	Country findByName(String name);
		
	/**
	 * Sets the given {@link LocationSearchModel} object and returns it.
	 *
	 * @param modelObject the model object
	 * 
	 * @return the modified {@link LocationSearchModel} object.
	 */
	@POST
	@Path("/resolve/location")
	LocationSearchModel<Address> setLocationSearchModel(LocationSearchModel<Address> modelObject);
}
