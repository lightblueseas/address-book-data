package de.alpharogroup.address.book.service.api;

import java.util.List;
import java.util.Map;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.domain.model.LocationSearchModel;
import de.alpharogroup.collections.pairs.KeyValuesPair;
import de.alpharogroup.service.domain.DomainService;

public interface CountryService extends DomainService<Integer, Country>
{

	/**
	 * Gets a map with the mapping: the key as Country object and as value the
	 * corresponding federal states as a List of Federalstate objects.
	 * 
	 * @return the countries to federalstates map
	 * @deprecated use instead {@link CountryService#getCountriesToFederalstatesList()}
	 */
	@Deprecated
	Map<Country, List<Federalstate>> getCountriesToFederalstatesMap();	

	/**
	 * Gets a list with the mapping-class: the key as Country object and as value the
	 * corresponding federal states as a List of Federalstate objects.
	 * 
	 * @return the Country to federalstate list
	 */
	List<KeyValuesPair<Country, Federalstate>> getCountriesToFederalstatesList();

	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding federal states as a List of Iso3166A2code String objects.
	 * 
	 * @return the countries to federalstates as string map
	 * @deprecated use instead {@link CountryService#getCountriesToFederalstatesAsStringList()}
	 */
	@Deprecated
	Map<String, List<String>> getCountriesToFederalstatesAsStringMap();

	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as
	 * value the corresponding federal states as a List of Iso3166A2code String objects.
	 * 
	 * @return the countries to federalstates as string list
	 */
	List<KeyValuesPair<String, String>> getCountriesToFederalstatesAsStringList();
	
	/**
	 * Gets a map with the mapping: the key as Country object and as value the
	 * corresponding Zipcodes as a List of Zipcode objects.
	 *
	 * @return the countries to zipcodes map
	 * @deprecated use instead {@link CountryService#getCountriesToZipcodesList()}
	 */
	@Deprecated
	Map<Country, List<Zipcode>> getCountriesToZipcodesMap();
	
	/**
	 * Gets a list with the mapping-class: the key as Country object and as value the
	 * corresponding Zipcodes as a List of Zipcode objects.
	 *
	 * @return the countries to zipcodes list
	 */
	List<KeyValuesPair<Country, Zipcode>> getCountriesToZipcodesList();
	
	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string map
	 * @deprecated use instead {@link CountryService#getCountriesToZipcodesAsStringList()}
	 */
	@Deprecated
	Map<String, List<String>> getCountriesToZipcodesAsStringMap();
	
	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as
	 * value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string list
	 */
	List<KeyValuesPair<String, String>> getCountriesToZipcodesAsStringList();
	
	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string map for german speeking countries only
	 * @deprecated use instead {@link CountryService#getGermanCountriesToZipcodesList()}
	 */
	@Deprecated
	Map<Country, List<Zipcode>> getGermanCountriesToZipcodesMap();
	
	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as
	 * value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string list for german speeking countries only
	 */
	List<KeyValuesPair<Country, Zipcode>> getGermanCountriesToZipcodesList();
	
	/**
	 * Gets the german countries to zipcodes as string map.
	 *
	 * @return the german countries to zipcodes as string map
	 * @deprecated use instead {@link CountryService#getGermanCountriesToZipcodesAsStringList()}
	 */
	@Deprecated
	Map<String, List<String>> getGermanCountriesToZipcodesAsStringMap();
	
	/**
	 * Gets the german countries to zipcodes as string list.
	 *
	 * @return the german countries to zipcodes as string list
	 */
	List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAsStringList();
	
	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding zipcodes and cities as a List of String objects.
	 *
	 * @return the countries to zipcodes and cities as string map
	 * @deprecated use instead {@link CountryService#getCountriesToZipcodesAndCitiesAsStringList()}
	 */
	@Deprecated
	Map<String, List<String>> getCountriesToZipcodesAndCitiesAsStringMap();
	
	/**
	 * Gets a list with the mapping-class: the key is the name of the country and as
	 * value the corresponding zipcodes and cities as a List of String objects.
	 *
	 * @return the countries to zipcodes and cities as string list
	 */
	List<KeyValuesPair<String, String>> getCountriesToZipcodesAndCitiesAsStringList();
	
	/**
	 * Gets the german countries to zipcodes and cities as string map.
	 *
	 * @return the german countries to zipcodes and cities as string map
	 * @deprecated use instead {@link CountryService#getGermanCountriesToZipcodesAndCitiesAsStringList()}
	 */
	@Deprecated
	Map<String, List<String>> getGermanCountriesToZipcodesAndCitiesAsStringMap();
	
	/**
	 * Gets the german countries to zipcodes and cities as string list.
	 *
	 * @return the german countries to zipcodes and cities as string list
	 */
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
	 * @deprecated use instead {@link CountryService#setLocationSearchModel()}
	 */
	@Deprecated
	String setLocationModel(LocationModel<Address> modelObject, String zc);
	
	/**
	 * Sets the given {@link LocationSearchModel} object and returns it.
	 *
	 * @param modelObject the model object
	 * 
	 * @return the modified {@link LocationSearchModel} object.
	 */
	LocationSearchModel<Address> setLocationSearchModel(LocationSearchModel<Address> modelObject);
}
