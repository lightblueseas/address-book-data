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
package de.alpharogroup.address.book.rest.client;

import java.util.List;

import javax.ws.rs.core.Response;

import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.domain.model.LocationAddressModel;
import de.alpharogroup.address.book.domain.model.LocationSearchModel;
import de.alpharogroup.address.book.rest.api.AddressesResource;
import de.alpharogroup.address.book.rest.api.CountriesResource;
import de.alpharogroup.address.book.rest.api.FederalstatesResource;
import de.alpharogroup.address.book.rest.api.ZipcodesResource;
import de.alpharogroup.address.book.rest.beanparams.AddressSearchCriteria;
import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.KeyValuesPair;
import de.alpharogroup.collections.pairs.Triple;
import lombok.Getter;

/**
 * The class {@link AddressBookRestClientTest}.
 */
public class AddressBookRestClientTest {

	@Getter
	private AddressBookRestClient restClient;

	/**
	 * The {@link AddressesResource}.
	 */
	@Getter
	private AddressesResource addressesResource;

	/**
	 * The {@link CountriesResource}.
	 */
	@Getter
	private CountriesResource countriesResource;

	/**
	 * The {@link FederalstatesResource}.
	 */
	@Getter
	private FederalstatesResource federalstatesResource;

	/**
	 * The {@link ZipcodesResource}.
	 */
	@Getter
	private ZipcodesResource zipcodesResource;

	private Country getGermanyAsCountry() {
		// 81 DE DEU 276 de.deu
		final Country country = Country.builder().iso3166A2name("DE").iso3166A3name("DEU").iso3166Number("276")
				.name("de.deu").build();
		country.setId(81);
		return country;
	}

	private Zipcode getZipcode() {
		final Country germany = getGermanyAsCountry();
		// 4 Kenzingen 79341 81
		final Zipcode zc = Zipcode.builder().city("Kenzingen").country(germany).zipcode("79341").build();
		zc.setId(4);
		return zc;
	}

	@BeforeClass
	public void setUpClass() throws Exception {
	}

	@BeforeMethod
	public void setUpMethod() throws Exception {
		if (restClient == null) {
			restClient = new AddressBookRestClient();
			addressesResource = restClient.getAddressesResource();
			countriesResource = restClient.getCountriesResource();
			federalstatesResource = restClient.getFederalstatesResource();
			zipcodesResource = restClient.getZipcodesResource();
		}
	}

	@AfterClass
	public void tearDownClass() throws Exception {
	}

	@AfterMethod
	public void tearDownMethod() throws Exception {
	}

	/**
	 * Test the {@link AddressesResource}.
	 *
	 * Note: you have to start a rest server to test this or you have to mock
	 * it.
	 */
	@Test(enabled = false)
	public void testAddressesRestResource() {

		// http://localhost:8080/address/geohash/u336
		List<Address> addresses = addressesResource.find("u336");
		System.out.println(addresses.size());
		// http://localhost:8080/address/geohash/neighbourhood/u336
		addresses = addressesResource.findNeighbourhood("u336");
		System.out.println(addresses.size());
		// http://localhost:8080/address/geohash/first/ring/u336
		addresses = addressesResource.findFirstRingNeighbourhood("u336");
		System.out.println(addresses.size());
		// http://localhost:8080/address/geohash/first/and/second/ring/u336
		addresses = addressesResource.findFirstAndSecondRingNeighbourhood("u336");
		System.out.println(addresses.size());
		// http://localhost:8080/address/find/49.647934/8.110127
		addresses = addressesResource.find("49.647934", "8.110127");
		System.out.println(addresses.size());
		// http://localhost:8080/address/contains/49.647934/8.110127
		Address address = addressesResource.contains("49.647934", "8.110127");
		System.out.println(address);
		final Zipcode zc = getZipcode();
		final Country germany = getGermanyAsCountry();
		// http://localhost:8080/address/contains/zipcode
		address = addressesResource.contains(zc);
		System.out.println(address);
		// http://localhost:8080/address/find/zipcodes
		addresses = addressesResource.find(zc);
		System.out.println(addresses.size());
		System.out.println(ListExtensions.getFirst(addresses));
		// http://localhost:8080/address/find/zipcodes/by/country
		final List<Zipcode> zipcodes = addressesResource.findAllAddressesWithCountry(germany);
		System.out.println(zipcodes.size());
		// http://localhost:8080/address/find/addresses/by/country
		addresses = addressesResource.findAll(germany);
		System.out.println(addresses.size());
		// http://localhost:8080/address/find/addresses/if/geohash/null
		addresses = addressesResource.findGeohashIsNull();
		System.out.println(addresses.size());
		// http://localhost:8080/address/find/addresses
		addresses = addressesResource.find(AddressSearchCriteria.builder().country(germany).zipcode("71638").build());
		System.out.println(addresses.size());
		// http://localhost:8080/address/find/addresses
		addresses = addressesResource
				.find(AddressSearchCriteria.builder().country(germany).zipcode("71638").city("Ludwigsburg").build());
		System.out.println(addresses.size());
		// http://localhost:8080/address/find/first/by/country/and/zipcode
		address = addressesResource
				.findFirst(AddressSearchCriteria.builder().country(germany).zipcode("71638").build());
		System.out.println(address);
	}

	/**
	 * Test the {@link CountriesResource}.
	 *
	 * Note: you have to start a rest server to test this or you have to mock
	 * it.
	 */
	@Test(enabled = false)
	public void testCountriesRestResource() {

		// http://localhost:8080/country/get/country2federalstate/list/
		final List<KeyValuesPair<Country, Federalstate>> countriesToFederalstatesList = countriesResource
				.getCountriesToFederalstatesList();
		AssertJUnit.assertNotNull(countriesToFederalstatesList);

		// http://localhost:8080/country/get/country2federalstate/stringlist/
		List<KeyValuesPair<String, String>> countriesToFederalstatesAsStringList = countriesResource
				.getCountriesToFederalstatesAsStringList();
		AssertJUnit.assertNotNull(countriesToFederalstatesAsStringList);

		// http://localhost:8080/country/get/country2zipcodes/list/
		List<KeyValuesPair<Country, Zipcode>> countriesToZipcodesList = countriesResource.getCountriesToZipcodesList();
		AssertJUnit.assertNotNull(countriesToZipcodesList);

		// http://localhost:8080/country/get/country2zipcodes/stringlist/
		List<KeyValuesPair<String, String>> countriesToZipcodesAsStringList = countriesResource
				.getCountriesToZipcodesAsStringList();
		AssertJUnit.assertNotNull(countriesToZipcodesAsStringList);

		// http://localhost:8080/country/get/germancountry2zipcodes/list/
		List<KeyValuesPair<Country, Zipcode>> germanCountriesToZipcodesList = countriesResource
				.getGermanCountriesToZipcodesList();
		AssertJUnit.assertNotNull(germanCountriesToZipcodesList);

		// http://localhost:8080/country/get/germancountry2zipcodes/stringlist/
		List<KeyValuesPair<String, String>> germanCountriesToZipcodesAsStringList = countriesResource
				.getGermanCountriesToZipcodesAsStringList();
		AssertJUnit.assertNotNull(germanCountriesToZipcodesAsStringList);

		// http://localhost:8080/country/get/country2zipcodesandcities/stringlist/
		List<KeyValuesPair<String, String>> countriesToZipcodesAndCitiesAsStringList = countriesResource
				.getCountriesToZipcodesAndCitiesAsStringList();
		AssertJUnit.assertNotNull(countriesToZipcodesAndCitiesAsStringList);

		// http://localhost:8080/country/get/germancountry2zipcodesandcities/stringlist/
		List<KeyValuesPair<String, String>> germanCountriesToZipcodesAndCitiesAsStringList = countriesResource
				.getGermanCountriesToZipcodesAndCitiesAsStringList();
		AssertJUnit.assertNotNull(germanCountriesToZipcodesAndCitiesAsStringList);

		LocationSearchModel<Address> lsm = LocationSearchModel.<Address>builder().zipcode("71638").location(
				LocationAddressModel.builder().selectedCountryName("de.deu").address(Address.builder().build()).build())
				.build();
		// {
		// "location":{
		// "location":null,
		// "selectedCountryName":"de.deu",
		// "address":{
		// "addressComment":null,
		// "federalstate":null,
		// "geohash":null,
		// "latitude":null,
		// "longitude":null,
		// "street":null,
		// "streetnumber":null,
		// "zipcode":null,
		// "id":null
		// }
		// },
		// "zipcode":"71638",
		// "errorKey":null
		// }
		// http://localhost:8080/country/resolve/location
		LocationSearchModel<Address> locationSearchModel = countriesResource.setLocationSearchModel(lsm);
		AssertJUnit.assertNotNull(locationSearchModel);

	}

	/**
	 * Test the {@link FederalstatesResource}.
	 *
	 * Note: you have to start a rest server to test this or you have to mock
	 * it.
	 */
	@Test(enabled = false)
	public void testFederalstatesRestResource() {

		// http://localhost:8080/federalstate/find/federalstate/gb.lnd
		Federalstate federalstate = federalstatesResource.findFederalstateFromIso3166A2code("gb.lnd");
		AssertJUnit.assertNotNull(federalstate);

		// http://localhost:8080/federalstate/find/federalstatestring/gb.lnd
		String name = federalstatesResource.findFederalstateNameFromIso3166A2code("gb.lnd");
		AssertJUnit.assertNotNull(name);

		final Country germany = getGermanyAsCountry();
		// http://localhost:8080/federalstate/find/federalstates/country
		// {"iso3166A2name":"DE","iso3166A3name":"DEU","iso3166Number":"276","name":"de.deu","id":81}
		List<Federalstate> federalstates = federalstatesResource.findFederalstatesFromCountry(germany);
		AssertJUnit.assertNotNull(federalstates);

		// http://localhost:8080/federalstate/find/federalstates/country/with/name
		// {"key":{"iso3166A2name":"DE","iso3166A3name":"DEU","iso3166Number":"276","name":"de.deu","id":81},"value":"Berlin"}
		KeyValuePair<Country, String> berlin = KeyValuePair.<Country, String>builder().key(germany).value("Berlin")
				.build();
		federalstates = federalstatesResource.findFederalstatesFromCountry(berlin);
		AssertJUnit.assertNotNull(federalstates);

		// http://localhost:8080/federalstate/find/federalstate/country/with/name
		// {"key":{"iso3166A2name":"DE","iso3166A3name":"DEU","iso3166Number":"276","name":"de.deu","id":81},"value":"Berlin"}
		federalstate = federalstatesResource.findFederalstate(berlin);
		AssertJUnit.assertNotNull(federalstate);

		// http://localhost:8080/get/federalstate/de.deu/de.bw
		federalstate = federalstatesResource.getFederalstate("de.deu", "de.bw");
		AssertJUnit.assertNotNull(federalstate);
	}

	/**
	 * Test the {@link ZipcodesResource}.
	 *
	 * Note: you have to start a rest server to test this or you have to mock
	 * it.
	 */
	@Test(enabled = false)
	public void testZipcodesRestResource() {
		final Country germany = getGermanyAsCountry();
		String zipcode = "22049";
		String zcBerlin = "10783";
		String city = "Hamburg";

		Triple<Country, String, String> searchCriteria = Triple.<Country, String, String>builder().left(germany)
				.middle(zipcode).right(city).build();
		// http://localhost:8080/zipcode/find/all
		// {"left":{"iso3166A2name":"DE","iso3166A3name":"DEU","iso3166Number":"276","name":"de.deu","id":81},"middle":"22049","right":"Hamburg"}
		List<Zipcode> zipcodes = zipcodesResource.findAll(searchCriteria);
		AssertJUnit.assertNotNull(zipcodes);

		// http://localhost:8080/zipcode/exists/22049
		final Response response = zipcodesResource.existsZipcode("22049");
		AssertJUnit.assertNotNull(response);

		final boolean exists = response.readEntity(boolean.class);
		AssertJUnit.assertTrue(exists);

		// http://localhost:8080/zipcode/find/22049
		zipcodes = zipcodesResource.findZipcodes(zipcode);
		AssertJUnit.assertNotNull(zipcodes);

		// http://localhost:8080/zipcode/get/22049/Hamburg
		Zipcode zc = zipcodesResource.getZipcode(zipcode, city);
		AssertJUnit.assertNotNull(zc);

		KeyValuePair<Country, String> berlin = KeyValuePair.<Country, String>builder().key(germany).value(zcBerlin)
				.build();

		// http://localhost:8080/zipcode/find/city/from/zipcode

		// {"key":{"iso3166A2name":"DE","iso3166A3name":"DEU","iso3166Number":"276","name":"de.deu","id":81},"value":"10783"}
		zc = zipcodesResource.findCityFromZipcode(berlin);
		AssertJUnit.assertNotNull(zc);

		// http://localhost:8080/zipcode/find/by/country
		// {"iso3166A2name":"DE","iso3166A3name":"DEU","iso3166Number":"276","name":"de.deu","id":81}
		zipcodes = zipcodesResource.find(germany);
		AssertJUnit.assertNotNull(zipcodes);

	}
}
