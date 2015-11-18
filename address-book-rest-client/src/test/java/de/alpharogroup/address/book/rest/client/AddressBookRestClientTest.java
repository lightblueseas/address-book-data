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

import org.testng.annotations.Test;

import de.alpharogroup.address.book.domain.Address;

/**
 * The class {@link AddressBookRestClientTest}.
 */
public class AddressBookRestClientTest {
    
	/**
	 * Test the {@link AddressesResource}. 
	 * 
	 * Note: you have to start a rest server
	 * to test this or you have to mock it.
	 */
	@Test(enabled = true)
	public void testAddressesRestResource() {
		AddressBookRestClient restClient = new AddressBookRestClient();
		// http://localhost:8080/address/geohash/u336
		List<Address> addresses = restClient.getAddressesResource().find("u336");
		System.out.println(addresses.size());
		// http://localhost:8080/address/geohash/neighbourhood/u336
		addresses = restClient.getAddressesResource().findNeighbourhood("u336");
		System.out.println(addresses.size());
		// http://localhost:8080/address/geohash/first/ring/u336
		addresses = restClient.getAddressesResource().findFirstRingNeighbourhood("u336");
		System.out.println(addresses.size());
		// http://localhost:8080/address/geohash/first/and/second/ring/u336
		addresses = restClient.getAddressesResource().findFirstAndSecondRingNeighbourhood("u336");
		System.out.println(addresses.size());
		// http://localhost:8080/address/find/49.647934/8.110127
		addresses = restClient.getAddressesResource().find("49.647934", "8.110127");
		System.out.println(addresses.size());
	}
}
