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
package de.alpharogroup.address.book.mapper;

import java.math.BigDecimal;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.factories.AddressBookFactory;

public class MapperTest {

	@Test
	public void testToBusinessObject() {
		Countries country = AddressBookFactory.getInstance().newCountries(1, "DE", "DEU", "276", "de.deu");
		CountriesMapper mapper = new CountriesMapper();
		Country bo = mapper.toDomainObject(country);
		AssertJUnit.assertEquals(country.getIso3166A2name(), bo.getIso3166A2name());
		AssertJUnit.assertEquals(country.getIso3166A3name(), bo.getIso3166A3name());
		AssertJUnit.assertEquals(country.getIso3166Number(), bo.getIso3166Number());
		AssertJUnit.assertEquals(country.getName(), bo.getName());
		AssertJUnit.assertEquals(country.getId(), bo.getId());

		Federalstates federalstates = AddressBookFactory.getInstance().newFederalstates(country, 2636, "de.hh",
				"Hamburg", null, null);
		FederalstatesMapper federalstatesMapper = new FederalstatesMapper();
		Federalstate federalstate = federalstatesMapper.toDomainObject(federalstates);
		AssertJUnit.assertEquals(federalstates.getId(), federalstate.getId());
		AssertJUnit.assertEquals(federalstates.getIso3166A2code(), federalstate.getIso3166A2code());
		AssertJUnit.assertEquals(federalstates.getName(), federalstate.getName());
		AssertJUnit.assertEquals(federalstates.getCountry().getId(), federalstate.getCountry().getId());
		AssertJUnit.assertEquals(federalstates.getCountry().getName(), federalstate.getCountry().getName());
		AssertJUnit.assertEquals(federalstates.getCountry().getIso3166Number(),
				federalstate.getCountry().getIso3166Number());
		AssertJUnit.assertEquals(federalstates.getCountry().getIso3166A3name(),
				federalstate.getCountry().getIso3166A3name());
		AssertJUnit.assertEquals(federalstates.getCountry().getIso3166A2name(),
				federalstate.getCountry().getIso3166A2name());

		Zipcodes zipcodes = AddressBookFactory.getInstance().newZipcodes(1, country, "Endingen am Kaiserstuhl",
				"79344");
		ZipcodesMapper zipcodesMapper = new ZipcodesMapper();
		Zipcode zipcode = zipcodesMapper.toDomainObject(zipcodes);
		AssertJUnit.assertEquals(zipcodes.getId(), zipcode.getId());
		AssertJUnit.assertEquals(zipcodes.getCity(), zipcode.getCity());
		AssertJUnit.assertEquals(zipcodes.getZipcode(), zipcode.getZipcode());
		AssertJUnit.assertEquals(zipcodes.getCountry().getId(), zipcode.getCountry().getId());
		AssertJUnit.assertEquals(zipcodes.getCountry().getName(), zipcode.getCountry().getName());
		AssertJUnit.assertEquals(zipcodes.getCountry().getIso3166Number(), zipcode.getCountry().getIso3166Number());
		AssertJUnit.assertEquals(zipcodes.getCountry().getIso3166A3name(), zipcode.getCountry().getIso3166A3name());
		AssertJUnit.assertEquals(zipcodes.getCountry().getIso3166A2name(), zipcode.getCountry().getIso3166A2name());

		Addresses addresses = AddressBookFactory.getInstance().newAddresses(0, "comment", federalstates, "u0vf2w1s5tsy",
				BigDecimal.valueOf(49.647934), BigDecimal.valueOf(8.110127), "blue street", "23", zipcodes);
		AddressesMapper addressesMapper = new AddressesMapper();
		Address address = addressesMapper.toDomainObject(addresses);
		AssertJUnit.assertEquals(addresses.getId(), address.getId());
		AssertJUnit.assertEquals(addresses.getAddressComment(), address.getAddressComment());
		AssertJUnit.assertEquals(addresses.getGeohash(), address.getGeohash());
		AssertJUnit.assertEquals(addresses.getLatitude(), address.getLatitude());
		AssertJUnit.assertEquals(addresses.getLongitude(), address.getLongitude());
		AssertJUnit.assertEquals(addresses.getStreet(), address.getStreet());
		AssertJUnit.assertEquals(addresses.getStreetnumber(), address.getStreetnumber());
		AssertJUnit.assertEquals(addresses.getFederalstate().getId(), address.getFederalstate().getId());
		AssertJUnit.assertEquals(addresses.getFederalstate().getIso3166A2code(),
				addresses.getFederalstate().getIso3166A2code());
		AssertJUnit.assertEquals(addresses.getFederalstate().getName(), addresses.getFederalstate().getName());
		AssertJUnit.assertEquals(addresses.getFederalstate().getCountry().getId(),
				addresses.getFederalstate().getCountry().getId());
		AssertJUnit.assertEquals(addresses.getFederalstate().getCountry().getName(),
				addresses.getFederalstate().getCountry().getName());
		AssertJUnit.assertEquals(addresses.getFederalstate().getCountry().getIso3166Number(),
				addresses.getFederalstate().getCountry().getIso3166Number());
		AssertJUnit.assertEquals(addresses.getFederalstate().getCountry().getIso3166A3name(),
				addresses.getFederalstate().getCountry().getIso3166A3name());
		AssertJUnit.assertEquals(addresses.getFederalstate().getCountry().getIso3166A2name(),
				addresses.getFederalstate().getCountry().getIso3166A2name());
		AssertJUnit.assertEquals(addresses.getZipcode().getId(), addresses.getZipcode().getId());
		AssertJUnit.assertEquals(addresses.getZipcode().getCity(), addresses.getZipcode().getCity());
		AssertJUnit.assertEquals(addresses.getZipcode().getZipcode(), addresses.getZipcode().getZipcode());
		AssertJUnit.assertEquals(addresses.getZipcode().getCountry().getId(),
				addresses.getZipcode().getCountry().getId());
		AssertJUnit.assertEquals(addresses.getZipcode().getCountry().getName(),
				addresses.getZipcode().getCountry().getName());
		AssertJUnit.assertEquals(addresses.getZipcode().getCountry().getIso3166Number(),
				addresses.getZipcode().getCountry().getIso3166Number());
		AssertJUnit.assertEquals(addresses.getZipcode().getCountry().getIso3166A3name(),
				addresses.getZipcode().getCountry().getIso3166A3name());
		AssertJUnit.assertEquals(addresses.getZipcode().getCountry().getIso3166A2name(),
				addresses.getZipcode().getCountry().getIso3166A2name());
	}

	@Test
	public void testToEntity() {
		Countries country = AddressBookFactory.getInstance().newCountries(1, "DE", "DEU", "276", "de.deu");
		CountriesMapper mapper = new CountriesMapper();
		Country bo = mapper.toDomainObject(country);
		country = mapper.toEntity(bo);
		AssertJUnit.assertEquals(country.getIso3166A2name(), bo.getIso3166A2name());
		AssertJUnit.assertEquals(country.getIso3166A3name(), bo.getIso3166A3name());
		AssertJUnit.assertEquals(country.getIso3166Number(), bo.getIso3166Number());
		AssertJUnit.assertEquals(country.getName(), bo.getName());
		AssertJUnit.assertEquals(country.getId(), bo.getId());

		Federalstates federalstates = AddressBookFactory.getInstance().newFederalstates(country, 2636, "de.hh",
				"Hamburg", null, null);
		FederalstatesMapper federalstatesMapper = new FederalstatesMapper();
		Federalstate federalstate = federalstatesMapper.toDomainObject(federalstates);
		federalstates = federalstatesMapper.toEntity(federalstate);
		AssertJUnit.assertEquals(federalstates.getId(), federalstate.getId());
		AssertJUnit.assertEquals(federalstates.getIso3166A2code(), federalstate.getIso3166A2code());
		AssertJUnit.assertEquals(federalstates.getName(), federalstate.getName());
		AssertJUnit.assertEquals(federalstates.getCountry().getId(), federalstate.getCountry().getId());
		AssertJUnit.assertEquals(federalstates.getCountry().getName(), federalstate.getCountry().getName());
		AssertJUnit.assertEquals(federalstates.getCountry().getIso3166Number(),
				federalstate.getCountry().getIso3166Number());
		AssertJUnit.assertEquals(federalstates.getCountry().getIso3166A3name(),
				federalstate.getCountry().getIso3166A3name());
		AssertJUnit.assertEquals(federalstates.getCountry().getIso3166A2name(),
				federalstate.getCountry().getIso3166A2name());

		Zipcodes zipcodes = AddressBookFactory.getInstance().newZipcodes(1, country, "Endingen am Kaiserstuhl",
				"79344");
		ZipcodesMapper zipcodesMapper = new ZipcodesMapper();
		Zipcode zipcode = zipcodesMapper.toDomainObject(zipcodes);
		zipcodes = zipcodesMapper.toEntity(zipcode);
		AssertJUnit.assertEquals(zipcodes.getId(), zipcode.getId());
		AssertJUnit.assertEquals(zipcodes.getCity(), zipcode.getCity());
		AssertJUnit.assertEquals(zipcodes.getZipcode(), zipcode.getZipcode());
		AssertJUnit.assertEquals(zipcodes.getCountry().getId(), zipcode.getCountry().getId());
		AssertJUnit.assertEquals(zipcodes.getCountry().getName(), zipcode.getCountry().getName());
		AssertJUnit.assertEquals(zipcodes.getCountry().getIso3166Number(), zipcode.getCountry().getIso3166Number());
		AssertJUnit.assertEquals(zipcodes.getCountry().getIso3166A3name(), zipcode.getCountry().getIso3166A3name());
		AssertJUnit.assertEquals(zipcodes.getCountry().getIso3166A2name(), zipcode.getCountry().getIso3166A2name());

		Addresses addresses = AddressBookFactory.getInstance().newAddresses(0, "comment", federalstates, "u0vf2w1s5tsy",
				BigDecimal.valueOf(49.647934), BigDecimal.valueOf(8.110127), "blue street", "23", zipcodes);
		AddressesMapper addressesMapper = new AddressesMapper();
		Address address = addressesMapper.toDomainObject(addresses);
		addresses = addressesMapper.toEntity(address);
		AssertJUnit.assertEquals(addresses.getId(), address.getId());
		AssertJUnit.assertEquals(addresses.getAddressComment(), address.getAddressComment());
		AssertJUnit.assertEquals(addresses.getGeohash(), address.getGeohash());
		AssertJUnit.assertEquals(addresses.getLatitude(), address.getLatitude());
		AssertJUnit.assertEquals(addresses.getLongitude(), address.getLongitude());
		AssertJUnit.assertEquals(addresses.getStreet(), address.getStreet());
		AssertJUnit.assertEquals(addresses.getStreetnumber(), address.getStreetnumber());
		AssertJUnit.assertEquals(addresses.getFederalstate().getId(), address.getFederalstate().getId());
		AssertJUnit.assertEquals(addresses.getFederalstate().getIso3166A2code(),
				addresses.getFederalstate().getIso3166A2code());
		AssertJUnit.assertEquals(addresses.getFederalstate().getName(), addresses.getFederalstate().getName());
		AssertJUnit.assertEquals(addresses.getFederalstate().getCountry().getId(),
				addresses.getFederalstate().getCountry().getId());
		AssertJUnit.assertEquals(addresses.getFederalstate().getCountry().getName(),
				addresses.getFederalstate().getCountry().getName());
		AssertJUnit.assertEquals(addresses.getFederalstate().getCountry().getIso3166Number(),
				addresses.getFederalstate().getCountry().getIso3166Number());
		AssertJUnit.assertEquals(addresses.getFederalstate().getCountry().getIso3166A3name(),
				addresses.getFederalstate().getCountry().getIso3166A3name());
		AssertJUnit.assertEquals(addresses.getFederalstate().getCountry().getIso3166A2name(),
				addresses.getFederalstate().getCountry().getIso3166A2name());
		AssertJUnit.assertEquals(addresses.getZipcode().getId(), addresses.getZipcode().getId());
		AssertJUnit.assertEquals(addresses.getZipcode().getCity(), addresses.getZipcode().getCity());
		AssertJUnit.assertEquals(addresses.getZipcode().getZipcode(), addresses.getZipcode().getZipcode());
		AssertJUnit.assertEquals(addresses.getZipcode().getCountry().getId(),
				addresses.getZipcode().getCountry().getId());
		AssertJUnit.assertEquals(addresses.getZipcode().getCountry().getName(),
				addresses.getZipcode().getCountry().getName());
		AssertJUnit.assertEquals(addresses.getZipcode().getCountry().getIso3166Number(),
				addresses.getZipcode().getCountry().getIso3166Number());
		AssertJUnit.assertEquals(addresses.getZipcode().getCountry().getIso3166A3name(),
				addresses.getZipcode().getCountry().getIso3166A3name());
		AssertJUnit.assertEquals(addresses.getZipcode().getCountry().getIso3166A2name(),
				addresses.getZipcode().getCountry().getIso3166A2name());
	}

}
