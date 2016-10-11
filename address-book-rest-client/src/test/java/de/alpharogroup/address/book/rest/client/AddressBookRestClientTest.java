/*
 * Copyright 2015 Alpha Ro Group UG (haftungsbeschrängt).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.alpharogroup.address.book.rest.client;

import java.util.List;
import java.util.Map;

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
import de.alpharogroup.address.book.rest.api.AddressesResource;
import de.alpharogroup.address.book.rest.api.CountriesResource;
import de.alpharogroup.address.book.rest.api.FederalstatesResource;
import de.alpharogroup.address.book.rest.api.ZipcodesResource;
import de.alpharogroup.address.book.rest.beanparams.AddressSearchCriteria;
import de.alpharogroup.collections.ListExtensions;
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

	@BeforeClass
	public static void setUpClass() throws Exception {

	}

	@AfterClass
	public static void tearDownClass() throws Exception {
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
		addresses = addressesResource.find(AddressSearchCriteria.builder().country(germany).zipcode("71638").build() );
		System.out.println(addresses.size());
		// http://localhost:8080/address/find/addresses
		addresses = addressesResource.find(AddressSearchCriteria.builder().country(germany).zipcode("71638").city("Ludwigsburg").build() );
		System.out.println(addresses.size());
		// http://localhost:8080/address/find/first/by/country/and/zipcode
		address = addressesResource.findFirst(AddressSearchCriteria.builder().country(germany).zipcode("71638").build());
		System.out.println(address);
	}

	/**
	 * Test the {@link CountriesResource}.
	 *
	 * Note: you have to start a rest server to test this or you have to mock
	 * it.
	 */
	@Test(enabled = true)
	public void testCountriesRestResource() {
		final Map<Country, List<Federalstate>> map = countriesResource.getCountriesToFederalstatesMap();
		AssertJUnit.assertNotNull(map);
	}

	private Zipcode getZipcode() {
		final Country germany = getGermanyAsCountry();
		// 4 Kenzingen 79341 81
		final Zipcode zc = Zipcode.builder().city("Kenzingen").country(germany).zipcode("79341").build();
		zc.setId(4);
		return zc;
	}

	private Country getGermanyAsCountry() {
		// 81 DE DEU 276 de.deu
		final Country country = Country.builder().iso3166A2name("DE").iso3166A3name("DEU").iso3166Number("276").name("de.deu")
				.build();
		country.setId(81);
		return country;
	}
}
