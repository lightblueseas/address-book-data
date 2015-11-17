package de.alpharogroup.address.book.service.api;

import java.util.List;
import java.util.Map;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.db.service.jpa.BusinessService;

/**
 * The interface {@link CountriesService}.
 */
public interface CountriesService extends BusinessService<Countries, Integer> {

	/**
	 * Gets a map with the mapping: the key as Countries object and as value the
	 * corresponding federal states as a List of Federalstates objects.
	 * 
	 * @return the countries to federalstates map
	 */
	Map<Countries, List<Federalstates>> getCountriesToFederalstatesMap();

	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding federal states as a List of Iso3166A2code String objects.
	 * 
	 * @return the countries to federalstates as string map
	 */
	Map<String, List<String>> getCountriesToFederalstatesAsStringMap();
	
	/**
	 * Gets a map with the mapping: the key as Countries object and as value the
	 * corresponding Zipcodes as a List of Zipcodes objects.
	 *
	 * @return the countries to zipcodes map
	 */
	Map<Countries, List<Zipcodes>> getCountriesToZipcodesMap();
	
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
	Map<Countries, List<Zipcodes>> getGermanCountriesToZipcodesMap();
	
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
	 * Find all {@link Countries} from the given arguments.
	 *
	 * @param iso3166A2name the iso3166 a2name
	 * @param iso3166A3name the iso3166 a3name
	 * @param iso3166Number the iso3166 number
	 * @param name the name
	 * @return the list of {@link Countries}
	 */
	List<Countries> findAll(String iso3166A2name, String iso3166A3name,
			String iso3166Number, String name);
	
	/**
	 * Find the {@link Countries} object from the given ISO 3166 {@link String} object.
	 *
	 * @param iso3166A2name the iso3166 a2name
	 * @return the {@link Countries} object
	 */
	Countries find(String iso3166A2name);
	
	/**
	 * Find the {@link Countries} object by name.
	 *
	 * @param name the name
	 * @return the countries
	 */
	Countries findByName(String name);
	
	/**
	 * Sets the location model.
	 *
	 * @param modelObject the model object
	 * @param zc the zipcode as string
	 * @return null if everything is ok otherwise an error property string
	 */
	String setLocationModel(LocationModel modelObject, String zc);
}