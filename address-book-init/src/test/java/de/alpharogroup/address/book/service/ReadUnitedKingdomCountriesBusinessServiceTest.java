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
package de.alpharogroup.address.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.ZipcodesService;
import de.alpharogroup.jgeohash.GeoHashPoint;

@ContextConfiguration(locations = "classpath:test-h2-applicationContext.xml")
public class ReadUnitedKingdomCountriesBusinessServiceTest extends AbstractTestNGSpringContextTests
{

	@Autowired
	private CountriesService countriesService;
	@Autowired
	private ZipcodesService zipcodesService;
	@Autowired
	private AddressesService addressesService;

	@Test(enabled = false)
	public void getAllSwitzerlandAddresses()
	{
		final Countries country = countriesService.find("DE");
		final List<Zipcodes> countryZipcodes = zipcodesService.find(country);
		System.out.println("All zipcodes from austria:" + countryZipcodes.size());
		final List<Zipcodes> zipcodesChInDb = addressesService.findAllAddressesWithCountry(country);
		countryZipcodes.removeAll(zipcodesChInDb);
		System.out.println("All zipcodes from austria not in db:" + countryZipcodes.size());
	}

	@Test(enabled = false)
	public void testFindGeohashNull()
	{
		final List<Addresses> addresses = addressesService.findGeohashIsNull();
		for (final Addresses address : addresses)
		{
			final String l = address.getLatitude();
			final String longtidude = address.getLongitude();
			final double lat = Double.parseDouble(l);
			final double lng = Double.parseDouble(longtidude);
			final GeoHashPoint geoHashPoint = new GeoHashPoint(lat, lng);
			final Addresses addr = addressesService.get(address.getId());
			addr.setGeohash(geoHashPoint.getGeohash());
			addressesService.merge(addr);
		}
	}

}
