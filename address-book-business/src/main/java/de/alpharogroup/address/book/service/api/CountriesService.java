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
package de.alpharogroup.address.book.service.api;

import java.util.List;
import java.util.Map;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.application.model.LocationSearchModel;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.collections.pairs.KeyValuesPair;
import de.alpharogroup.db.service.api.BusinessService;

/**
 * The interface {@link CountriesService}.
 */
public interface CountriesService extends BusinessService<Countries, Integer> {

	/**
	 * Find the {@link Countries} object from the given ISO 3166 {@link String}
	 * object.
	 *
	 * @param iso3166A2name
	 *            the iso3166 a2name
	 * @return the {@link Countries} object
	 */
	Countries find(String iso3166A2name);

	/**
	 * Find all {@link Countries} from the given arguments.
	 *
	 * @param iso3166A2name
	 *            the iso3166 a2name
	 * @param iso3166A3name
	 *            the iso3166 a3name
	 * @param iso3166Number
	 *            the iso3166 number
	 * @param name
	 *            the name
	 * @return the list of {@link Countries}
	 */
	List<Countries> findAll(String iso3166A2name, String iso3166A3name, String iso3166Number, String name);

	/**
	 * Find the {@link Countries} object by name.
	 *
	 * @param name
	 *            the name
	 * @return the countries
	 */
	Countries findByName(String name);

	/**
	 * Gets a list with the mapping-class: the key is the name of the country
	 * and as value the corresponding federal states as a List of Iso3166A2code
	 * String objects.
	 * 
	 * @return the countries to federalstates as string list
	 */
	List<KeyValuesPair<String, String>> getCountriesToFederalstatesAsStringList();

	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding federal states as a List of Iso3166A2code String
	 * objects.
	 * 
	 * @return the countries to federalstates as string map
	 * @deprecated use instead
	 *             {@link CountriesService#getCountriesToFederalstatesAsStringList()}
	 */
	@Deprecated
	Map<String, List<String>> getCountriesToFederalstatesAsStringMap();

	/**
	 * Gets a list with the mapping-class: the key as Countries object and as
	 * value the corresponding federal states as a List of Federalstates
	 * objects.
	 * 
	 * @return the countries to federalstates list
	 */
	List<KeyValuesPair<Countries, Federalstates>> getCountriesToFederalstatesList();

	/**
	 * Gets a map with the mapping: the key as Countries object and as value the
	 * corresponding federal states as a List of Federalstates objects.
	 * 
	 * @return the countries to federalstates map
	 * @deprecated use instead
	 *             {@link CountriesService#getCountriesToFederalstatesList()}
	 */
	@Deprecated
	Map<Countries, List<Federalstates>> getCountriesToFederalstatesMap();

	/**
	 * Gets a list with the mapping-class: the key is the name of the country
	 * and as value the corresponding zipcodes and cities as a List of String
	 * objects.
	 *
	 * @return the countries to zipcodes and cities as string list
	 */
	List<KeyValuesPair<String, String>> getCountriesToZipcodesAndCitiesAsStringList();

	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding zipcodes and cities as a List of String objects.
	 *
	 * @return the countries to zipcodes and cities as string map
	 * @deprecated use instead
	 *             {@link CountriesService#getCountriesToZipcodesAndCitiesAsStringList()}
	 */
	@Deprecated
	Map<String, List<String>> getCountriesToZipcodesAndCitiesAsStringMap();

	/**
	 * Gets a list with the mapping-class: the key is the name of the country
	 * and as value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string list
	 */
	List<KeyValuesPair<String, String>> getCountriesToZipcodesAsStringList();

	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string map
	 * @deprecated use instead
	 *             {@link CountriesService#getCountriesToZipcodesAsStringList()}
	 */
	@Deprecated
	Map<String, List<String>> getCountriesToZipcodesAsStringMap();

	/**
	 * Gets a list with the mapping-class: the key as Countries object and as
	 * value the corresponding Zipcodes as a List of Zipcodes objects.
	 *
	 * @return the countries to zipcodes list
	 */
	List<KeyValuesPair<Countries, Zipcodes>> getCountriesToZipcodesList();

	/**
	 * Gets a map with the mapping: the key as Countries object and as value the
	 * corresponding Zipcodes as a List of Zipcodes objects.
	 *
	 * @return the countries to zipcodes map
	 * @deprecated use instead
	 *             {@link CountriesService#getCountriesToZipcodesList()}
	 */
	Map<Countries, List<Zipcodes>> getCountriesToZipcodesMap();

	/**
	 * Gets the german countries to zipcodes and cities as string list.
	 *
	 * @return the german countries to zipcodes and cities as string list
	 */
	List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAndCitiesAsStringList();

	/**
	 * Gets the german countries to zipcodes and cities as string map.
	 *
	 * @return the german countries to zipcodes and cities as string map
	 * @deprecated use instead
	 *             {@link CountriesService#getGermanCountriesToZipcodesAndCitiesAsStringList()}
	 */
	@Deprecated
	Map<String, List<String>> getGermanCountriesToZipcodesAndCitiesAsStringMap();

	/**
	 * Gets a list with the mapping-class: the key is the name of the german
	 * speaking country and as value the corresponding zipcodes as a List of
	 * String objects.
	 *
	 * @return the german speaking countries to zipcodes as string list
	 */
	List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAsStringList();

	/**
	 * Gets the german countries to zipcodes as string map.
	 *
	 * @return the german countries to zipcodes as string map
	 * @deprecated use instead
	 *             {@link CountriesService#getGermanCountriesToZipcodesAsStringList()}
	 */
	@Deprecated
	Map<String, List<String>> getGermanCountriesToZipcodesAsStringMap();

	/**
	 * Gets a list with the mapping-class: the key is the name of the country
	 * and as value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as list of {@link KeyValuesPair} for
	 *         german speeking countries only
	 */
	List<KeyValuesPair<Countries, Zipcodes>> getGermanCountriesToZipcodesList();

	/**
	 * Gets a map with the mapping: the key is the name of the country and as
	 * value the corresponding zipcodes as a List of String objects.
	 *
	 * @return the countries to zipcodes as string map for german speeking
	 *         countries only
	 * @deprecated use instead
	 *             {@link CountriesService#getGermanCountriesToZipcodesList()}
	 */
	@Deprecated
	Map<Countries, List<Zipcodes>> getGermanCountriesToZipcodesMap();

	/**
	 * Sets the location model.
	 *
	 * @param modelObject
	 *            the model object
	 * @param zc
	 *            the zipcode as string
	 * @return null if everything is ok otherwise an error property string
	 * @deprecated use instead {@link CountriesService#setLocationSearchModel(LocationSearchModel)}
	 */
	@Deprecated
	String setLocationModel(LocationModel<Addresses> modelObject, String zc);

	/**
	 * Sets the given {@link LocationSearchModel} object and returns it.
	 *
	 * @param modelObject
	 *            the model object
	 * 
	 * @return the modified {@link LocationSearchModel} object.
	 */
	LocationSearchModel<Addresses> setLocationSearchModel(LocationSearchModel<Addresses> modelObject);
}