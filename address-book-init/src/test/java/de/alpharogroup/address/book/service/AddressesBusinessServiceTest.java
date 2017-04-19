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

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.init.DeZipcodeBean;
import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.ZipcodesService;
import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jgeohash.GeoHashPoint;
import de.alpharogroup.jgeohash.distance.DistanceCalculator;
import de.alpharogroup.jgeohash.distance.MeasuringUnit;
import de.alpharogroup.xml.XmlExtensions;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class AddressesBusinessServiceTest extends AbstractTestNGSpringContextTests
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
	public void getNeighbourhood()
	{
		List<Addresses> addresses = addressesService.findNeighbourhood("u336");
		System.out.println("addresses found:" + addresses);
		System.out.println("addresses size:" + addresses.size());
		addresses = addressesService.findFirstRingNeighbourhood("u336");
		System.out.println("addresses found:" + addresses);
		System.out.println("addresses size:" + addresses.size());
		addresses = addressesService.findFirstAndSecondRingNeighbourhood("u336");
		System.out.println("addresses found:" + addresses);
		System.out.println("addresses size:" + addresses.size());
	}

	protected File getProcessedDir()
	{
		final File smr = PathFinder.getSrcMainResourcesDir();
		final File processedDir = PathFinder.getRelativePath(smr, "zipcodes", "processed");
		return processedDir;
	}

	@Test(enabled = false)
	public void testCreateAddress()
	{
		Addresses addr = addressesService.createAddress("Alter Teichweg", "188", "", "22049",
			"Hamburg", "Hamburg");
		System.out.println(addr);
		addr = addressesService.createAddress("Alter Teichweg", "188", "", "22049", "Hamburg",
			null);
		System.out.println(addr);
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

	@Test(enabled = false)
	public void testGetInvalidAddresses()
	{
		final Countries country = countriesService.find("DE");
		System.out.println(country.getName());
		final List<Addresses> addresses = addressesService.findInvalidAddresses(country, "u4",
			false);
		System.out.println("addresses size:" + addresses.size());
		for (final Addresses address : addresses)
		{

			System.out.println("processed :id:" + address.getId() + ":"
				+ address.getZipcode().getZipcode() + " " + address.getZipcode().getCity());
		}
		for (final Addresses address : addresses)
		{
			System.out.println("to process:" + address.getZipcode().getZipcode() + " "
				+ address.getZipcode().getCity());
			final List<Addresses> addressesSameCityName = addressesService
				.findAddressesWithSameCityname(address.getZipcode().getCountry(),
					address.getZipcode().getCity());
			// List<Addresses> addressesSameCityName =
			// addressesService.findAddressesWithSameZipcode(address.getZipcode().getCountry(),
			// address.getZipcode().getZipcode());
			addressesSameCityName.remove(address);
			for (final Addresses address2 : addressesSameCityName)
			{
				if (address2.getGeohash().startsWith("u"))
				{
					final Addresses freshFromDb = addressesService.get(address.getId());
					freshFromDb.setGeohash(address2.getGeohash());
					freshFromDb.setLatitude(address2.getLatitude());
					freshFromDb.setLongitude(address2.getLongitude());
					addressesService.merge(freshFromDb);
					System.out.println("processed:" + freshFromDb.getZipcode().getZipcode() + " "
						+ freshFromDb.getZipcode().getCity());
					break;
				}
			}
		}
		// for (Addresses address : addresses) {
		// Addresses freshFromDb = addressesService.get(address.getId());
		// GeoHashPoint ghp = new GeoHashPoint(freshFromDb.getLatitude(),
		// freshFromDb.getLongitude());
		// freshFromDb.setGeohash(ghp.getGeohash());
		// addressesService.mergeAndFlush(freshFromDb);
		// addressesService.flush();
		// System.out.println("processed:"
		// +freshFromDb.getZipcode().getZipcode()+"
		// "+freshFromDb.getZipcode().getCity());
		// }
	}

	@Test(enabled = false)
	public void validateAddressesWithSameCityname()
	{
		final Countries country = countriesService.find("DE");
		final List<Addresses> addresses = addressesService.findAll(country);
		for (final Iterator<Addresses> iterator = addresses.iterator(); iterator.hasNext();)
		{
			final Addresses address = iterator.next();
			final List<Addresses> addressesSameCityName = addressesService
				.findAddressesWithSameCityname(address.getZipcode().getCountry(),
					address.getZipcode().getCity());
			if (4 < addressesSameCityName.size())
			{
				continue;
			}
			for (final Addresses address2 : addressesSameCityName)
			{
				System.out.println(
					address2.getZipcode().getZipcode() + " " + address2.getZipcode().getCity());
			}
		}
	}

	@Test(enabled = false)
	public void valideAddressZipcodes() throws IOException
	{
		final File smr = PathFinder.getSrcMainResourcesDir();
		final File deDir = PathFinder.getRelativePath(smr, "zipcodes", "de");
		final File input = new File(deDir, "DeZipcodes.xml");
		final String notPrZipcodes = ReadFileExtensions.readFromFile(input);
		final List<DeZipcodeBean> deZipcodeBeanList = XmlExtensions
			.toObjectWithXStream(notPrZipcodes);

		final Countries country = countriesService.find("DE");
		for (final DeZipcodeBean bean : deZipcodeBeanList)
		{
			final List<Addresses> addresses = addressesService.find(country, bean.getZipcode(),
				bean.getCity());
			if (1 < addresses.size())
			{
				System.out.println("ambiguous:" + bean);
				continue;
			}
			final Addresses addressInDb = ListExtensions.getFirst(addresses);

			if (addressInDb != null)
			{
				final double latDb = Double.parseDouble(addressInDb.getLatitude());
				final double lngDb = Double.parseDouble(addressInDb.getLongitude());
				double latBean;
				double lngBean;
				try
				{
					latBean = Double.parseDouble(bean.getLatitude());
					lngBean = Double.parseDouble(bean.getLongitude());
				}
				catch (final NumberFormatException e1)
				{
					// TODO Auto-generated catch block
					System.out.println("=================================");
					System.out.println("This bean will is not processed:" + bean);
					System.out.println("=================================");
					e1.printStackTrace();
					break;
				}

				final double distance = DistanceCalculator.distance(latDb, lngDb, latBean, lngBean,
					MeasuringUnit.KILOMETER);
				if (30.0 < distance)
				{
					GeoHashPoint point;
					try
					{
						point = new GeoHashPoint(bean.getLatitude(), bean.getLongitude());
					}
					catch (final Exception e)
					{
						// TODO Auto-generated catch block
						System.out.println("=================================");
						System.out.println("This bean will is not processed:" + bean);
						System.out.println("=================================");
						e.printStackTrace();
						break;
					}
					final Addresses addressToValide = addressesService.get(addressInDb.getId());
					addressToValide.setAddressComment("validated");
					addressToValide.setLatitude(bean.getLatitude());
					addressToValide.setLongitude(bean.getLongitude());
					addressToValide.setGeohash(point.getGeohash());
					addressesService.merge(addressToValide);
				}
			}
		}
	}

}
