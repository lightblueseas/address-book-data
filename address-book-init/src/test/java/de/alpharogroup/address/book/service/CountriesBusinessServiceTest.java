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
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import de.alpharogroup.address.book.application.geocoding.Geocoder;
import de.alpharogroup.address.book.application.model.GeoPointZipcode;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.factories.AddressBookFactory;
import de.alpharogroup.address.book.init.GermanZipcodeBean;
import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.address.book.service.api.ZipcodesService;
import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.collections.pairs.KeyValuesPair;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.file.write.WriteFileExtensions;
import de.alpharogroup.jgeohash.GeoHashPoint;
import de.alpharogroup.random.RandomExtensions;
import de.alpharogroup.xml.XmlExtensions;

@ContextConfiguration(locations = "classpath:test-h2-applicationContext.xml")
public class CountriesBusinessServiceTest extends AbstractTestNGSpringContextTests
{
	@Autowired
	private CountriesService countriesService;
	@Autowired
	private ZipcodesService zipcodesService;
	@Autowired
	private AddressesService addressesService;
	@Autowired
	private FederalstatesService federalstatesService;

	@Test(enabled = false)
	public void fillNotProcessedZipcodesWithGeocodeData() throws IOException
	{
		final List<Zipcodes> npZipcodes = getNotProcessedList();
		System.out.println("Not processed:" + npZipcodes.size());
		final List<Zipcodes> newNpZipcodes = new ArrayList<>();
		int count = 1;
		for (Zipcodes zipcode : npZipcodes)
		{
			if (addressesService.contains(zipcode) != null)
			{
				System.out.println(
					"np" + (count++) + "). " + zipcode.getZipcode() + " " + zipcode.getCity());
				continue;
			}
			final Addresses found = findNextAddressToZipcode(zipcode);
			if (found == null)
			{
				newNpZipcodes.add(zipcode);
				continue;
			}

			Addresses address;
			zipcode = zipcodesService.get(zipcode.getId());
			address = AddressBookFactory.getInstance().newAddresses(null, null, found.getGeohash(),
				null, found.getLatitude(), found.getLongitude(), null, null, zipcode);
			System.out.println((count++) + "). " + zipcode.getZipcode() + " " + zipcode.getCity());

			addressesService.merge(address);
			if (found.getFederalstate() != null)
			{
				final Federalstates fs = federalstatesService.get(found.getFederalstate().getId());
				address.setFederalstate(fs);
			}
			addressesService.merge(address);
		}
		final String xmlNP = XmlExtensions.toXmlWithXStream(newNpZipcodes);
		WriteFileExtensions.string2File(getNotProcessedFile(), xmlNP);
	}

	public Set<Zipcodes> findExistingZipcodesFromAddresses()
	{
		final List<Addresses> addresses = addressesService.findAll();
		final Set<Zipcodes> processed = new HashSet<>();
		for (final Addresses address : addresses)
		{
			final Zipcodes zc = address.getZipcode();
			if (zc != null)
			{
				processed.add(address.getZipcode());
			}
		}
		return processed;
	}

	public Addresses findNextAddressToZipcode(final Zipcodes zipcode)
	{
		final String zcString = zipcode.getZipcode();
		List<Addresses> addresses = addressesService.find(zipcode.getCountry(),
			zipcode.getZipcode());
		Addresses found = ListExtensions.getFirst(addresses);
		if (found != null)
		{
			return found;
		}
		int zcInt = Integer.valueOf(zcString) - 1;
		final Countries country = countriesService.get(zipcode.getCountry().getId());
		int count = 0;
		while (found == null)
		{
			count++;
			if (count == 3)
			{
				break;
			}
			final List<Zipcodes> zcs = zipcodesService.findAll(country, zcInt + "", null);
			final Zipcodes zc = ListExtensions.getFirst(zcs);
			if (zc != null)
			{
				addresses = addressesService.find(zc);
				found = ListExtensions.getFirst(addresses);
				if (found != null)
				{
					break;
				}
				if (found == null)
				{
					System.out.println(zcInt);
					zcInt--;
				}
			}
		}
		return found;
	}

	@SuppressWarnings("static-access")
	@Test(enabled = false)
	public void getGeoHashCodesWithGermanZipcodes()
		throws MalformedURLException, IOException, ParserConfigurationException, SAXException
	{
		final File processedDir = getProcessedDir();

		List<Zipcodes> processed = new ArrayList<>(findExistingZipcodesFromAddresses());
		System.out.println("Already processed:" + processed.size());
		List<Zipcodes> notProcessed = new ArrayList<>();

		final File npZipcodesFile = new File(processedDir, "npZipcodes.xml");
		processed = new ArrayList<>(findExistingZipcodesFromAddresses());

		final Countries germany = countriesService.find("SK");
		final List<Zipcodes> germanZipcodes = zipcodesService.find(germany);
		germanZipcodes.removeAll(processed);
		final String notPrZipcodes = ReadFileExtensions.readFromFile(npZipcodesFile);
		notProcessed = XmlExtensions.toObjectWithXStream(notPrZipcodes);
		System.out.println("Not processed:" + notProcessed.size());
		germanZipcodes.removeAll(notProcessed);
		int count = 1;
		System.out.println("Left to process:" + germanZipcodes.size());
		for (int i = 0; i < 2800; i++)
		{
			final Zipcodes zc = germanZipcodes.get(i);
			Addresses address;
			address = addressesService.contains(zc);
			GeoHashPoint point = null;
			if (address == null)
			{
				point = Geocoder.getGeocodingData(zc);
				if (point == null)
				{
					notProcessed.add(zc);
					continue;
				}
			}
			else
			{
				continue;
			}
			try
			{
				address = addressesService.contains(point.getLat().toString().trim(),
					point.getLng().toString().trim());
			}
			catch (final Exception e1)
			{
				notProcessed.add(zc);
				continue;
			}
			if (address == null)
			{
				address = AddressBookFactory.getInstance().newAddresses(null, null,
					point.getGeohash(), point.getLat(), point.getLng(), null, null, zc);
				addressesService.merge(address);
				System.out.println((count++) + "). " + zc.getZipcode() + " " + zc.getCity());
			}
			try
			{
				Thread.currentThread().sleep(RandomExtensions.randomIntBetween(1000, 1500));
			}
			catch (final InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		final String xmlNP = XmlExtensions.toXmlWithXStream(notProcessed);
		WriteFileExtensions.string2File(npZipcodesFile, xmlNP);
	}

	private List<GeoPointZipcode> getGeoPointZipcodesList() throws IOException
	{
		final File processedDir = getProcessedDir();

		final File geoZipcodesFile = new File(processedDir, "geoZipcodes.xml");
		final String geoZipcodes = ReadFileExtensions.readFromFile(geoZipcodesFile);
		final List<GeoPointZipcode> geoPointZipcodes = XmlExtensions
			.toObjectWithXStream(geoZipcodes);
		return geoPointZipcodes;
	}

	private List<GermanZipcodeBean> getGermanZipcodeBeanList() throws IOException
	{
		final File smr = PathFinder.getSrcMainResourcesDir();
		final File deDir = PathFinder.getRelativePath(smr, "zipcodes", "de");

		final File germanZipcodesXmlFile = new File(deDir, "GermanZipcodes.xml");
		final String notPrZipcodes = ReadFileExtensions.readFromFile(germanZipcodesXmlFile);
		final List<GermanZipcodeBean> list = XmlExtensions.toObjectWithXStream(notPrZipcodes);
		return list;
	}

	@Test(enabled = false)
	public void getLatestNotProcessedZipcodes()
	{
		final List<Zipcodes> processed = new ArrayList<>(
			findExistingZipcodesFromAddresses());
		final Countries germany = countriesService.find("SK");
		final List<Zipcodes> allGermanZipcodes = zipcodesService.findAll(germany, null, null);
		allGermanZipcodes.removeAll(processed);

		final String xmlNP = XmlExtensions.toXmlWithXStream(allGermanZipcodes);
		WriteFileExtensions.string2File(getNotProcessedFile(), xmlNP);
	}

	private File getNotProcessedFile()
	{
		final File processedDir = getProcessedDir();

		final File npZipcodesFile = new File(processedDir, "npZipcodes.xml");
		return npZipcodesFile;
	}

	private List<Zipcodes> getNotProcessedList() throws IOException
	{
		final File npZipcodesFile = getNotProcessedFile();
		final String notPrZipcodes = ReadFileExtensions.readFromFile(npZipcodesFile);
		final List<Zipcodes> notProcessed = XmlExtensions.toObjectWithXStream(notPrZipcodes);
		return notProcessed;
	}

	private File getProcessedDir()
	{
		final File smr = PathFinder.getSrcMainResourcesDir();
		final File processedDir = PathFinder.getRelativePath(smr, "zipcodes", "processed");
		return processedDir;
	}

	@Test(enabled = false)
	public void loadFromGeoZipcodesFileAndSaveToDb() throws IOException
	{
		final File processedDir = getProcessedDir();

		final File zipcodesFile = new File(processedDir, "zipcodes.xml");
		final List<Zipcodes> processed = new ArrayList<>(
			findExistingZipcodesFromAddresses());

		final File geoZipcodesFile = new File(processedDir, "geoZipcodes.xml");
		final List<GeoPointZipcode> geoPointZipcodes = getGeoPointZipcodesList();
		int count = 1;
		System.out.println("geoPointZipcodes size is:" + geoPointZipcodes.size());
		for (int i = 0; i < geoPointZipcodes.size(); i++)
		{
			final GeoPointZipcode geoPointZipcode = geoPointZipcodes.get(i);
			final Zipcodes zc = geoPointZipcode.getZipcode();
			final GeoHashPoint point = geoPointZipcode.getGeoHashPoint();
			Addresses address;
			address = addressesService.contains(zc);

			if (address == null)
			{

				address = AddressBookFactory.getInstance().newAddresses(null, null,
					point.getGeohash(), point.getLat(), point.getLng(), null, null, zc);
				System.out.println((count++) + "). " + zc.getZipcode() + " " + zc.getCity());

				addressesService.merge(address);
			}
			processed.add(zc);
		}
		final String xml = XmlExtensions.toXmlWithXStream(processed);
		WriteFileExtensions.string2File(zipcodesFile, xml);
		final String xmlGeo = XmlExtensions.toXmlWithXStream(geoPointZipcodes);
		WriteFileExtensions.string2File(geoZipcodesFile, xmlGeo);
		System.out.println("Finished at:" + new Date(System.currentTimeMillis()));
	}

	@SuppressWarnings("static-access")
	@Test(enabled = false)
	public void saveGeoZipcodesToFile() throws IOException
	{

		final File processedDir = getProcessedDir();
		final File geoZipcodesFile = new File(processedDir, "geoZipcodes.xml");

		final List<Zipcodes> processed = new ArrayList<>(
			findExistingZipcodesFromAddresses());
		System.out.println("Already processed:" + processed.size());

		final List<Zipcodes> notProcessed = getNotProcessedList();

		final List<Zipcodes> countryZipcodes = zipcodesService.findAll();
		countryZipcodes.removeAll(processed);
		System.out.println("Not processed:" + notProcessed.size());
		final List<GeoPointZipcode> geopoints = new ArrayList<>();
		countryZipcodes.removeAll(notProcessed);
		int iterations = 2600;
		if (countryZipcodes.size() < iterations)
		{
			iterations = countryZipcodes.size();
		}
		int count = 1;
		try
		{
			for (int i = 0; i < iterations; i++)
			{
				final int c = i + 1;
				System.out.println(c + ").loop");
				final Zipcodes zc = countryZipcodes.get(i);
				Addresses address;
				address = addressesService.contains(zc);
				GeoHashPoint point = null;
				GeoPointZipcode geopoint = null;
				if (address == null)
				{
					point = Geocoder.getGeocodingData(zc);
					if (point == null)
					{
						notProcessed.add(zc);
						continue;
					}
					geopoint = GeoPointZipcode.builder().zipcode(zc).geoHashPoint(point).build();
					System.out.println((count++) + "). " + zc.getZipcode() + " " + zc.getCity());
				}
				else
				{
					continue;
				}

				try
				{
					address = addressesService.contains(point.getLat().toString().trim(),
						point.getLng().toString().trim());
				}
				catch (final Exception e1)
				{
					continue;
				}
				if (geopoint != null)
				{
					geopoints.add(geopoint);
				}
				try
				{
					Thread.currentThread().sleep(RandomExtensions.randomIntBetween(1000, 1500));
				}
				catch (final InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		final String xmlNP = XmlExtensions.toXmlWithXStream(notProcessed);
		WriteFileExtensions.string2File(getNotProcessedFile(), xmlNP);

		final String xmlGeoPoints = XmlExtensions.toXmlWithXStream(geopoints);
		WriteFileExtensions.string2File(geoZipcodesFile, xmlGeoPoints);
		System.out.println("Finished at:" + new Date(System.currentTimeMillis()));
	}

	@Test(enabled = false)
	public void testDeleteDuplicateEntries()
	{
		final List<Zipcodes> allZipcodes = zipcodesService.findAll();
		final int size = allZipcodes.size();
		System.out.println(size);
		for (int i = 0; i < size; i++)
		{
			final Zipcodes zc = allZipcodes.get(i);
			final List<Addresses> addresses = addressesService.find(zc);
			for (int j = 1; j < addresses.size(); j++)
			{
				System.out.println(zc.getZipcode() + " " + zc.getCity());
				final Addresses addr = addressesService.get(addresses.get(j).getId());
				addr.setZipcode(null);
				addr.setFederalstate(null);
				addressesService.merge(addr);
				addressesService.delete(addr);
			}
		}
	}

	@Test(enabled = false)
	public void testFindUsers()
	{
		final List<KeyValuesPair<String, String>> map = countriesService
			.getCountriesToZipcodesAsStringList();
		for (final KeyValuesPair<String, String> entry : map)
		{
			final String country = entry.getKey();
			if (0 < entry.getValues().size())
			{
				System.out.println(country);
				for (final String zipcode : entry.getValues())
				{
					System.out.println(zipcode);
				}
				System.out.println("=====================================================");
				System.out.println("=====================================================");
				System.out.println("=====================================================");
			}
		}
		System.out.println(map);
	}

	@Test(enabled = false)
	public void testNotProcessed() throws MalformedURLException, IOException
	{
		final File processedDir = getProcessedDir();

		List<Zipcodes> notProcessed = new ArrayList<>();

		final File npZipcodesFile = new File(processedDir, "npZipcodes.xml");

		final String notPrZipcodes = ReadFileExtensions.readFromFile(npZipcodesFile);
		notProcessed = XmlExtensions.toObjectWithXStream(notPrZipcodes);

		System.out.println("Not processed zipcodes:" + notProcessed.size());
		for (final Zipcodes zc : notProcessed)
		{
			System.out.println(zc.getZipcode() + " " + zc.getCity());
		}
	}

	@Test(enabled = false)
	public void verifyNotProcessed() throws IOException
	{
		final List<GermanZipcodeBean> list = getGermanZipcodeBeanList();
		final Map<String, GermanZipcodeBean> zipcodeToBeanMap = new HashMap<>();
		for (final GermanZipcodeBean germanZipcodeBean : list)
		{
			zipcodeToBeanMap.put(germanZipcodeBean.getZipcode(), germanZipcodeBean);
		}

		final List<Zipcodes> npZipcodes = getNotProcessedList();
		System.out.println("Not processed:" + npZipcodes.size());
		int count = 0;
		for (final Zipcodes zipcode : npZipcodes)
		{
			if (zipcodeToBeanMap.containsKey(zipcode.getZipcode()))
			{
				count++;
				System.out.println(zipcode.getZipcode() + " " + zipcode.getCity());
			}
		}
		System.out.println("count:" + count);
	}

	@Test(enabled = false)
	protected void verifyZipcodes() throws IOException
	{
		// alien id, native id
		final Map<String, String> federalStateMap = new HashMap<>();
		federalStateMap.put("08", "2631");// Baden-Württemberg
		federalStateMap.put("09", "2632");// Bayern
		federalStateMap.put("11", "2633");// Berlin
		federalStateMap.put("12", "2634");// Brandenburg
		federalStateMap.put("04", "2635");// Bremen
		federalStateMap.put("02", "2636");// Hamburg
		federalStateMap.put("06", "2637");// Hessen
		federalStateMap.put("13", "2638");// Mecklenburg-Vorpommern
		federalStateMap.put("03", "2639");// Niedersachsen
		federalStateMap.put("05", "2640");// Nordrhein-Westfalen
		federalStateMap.put("07", "2641");// Rheinland-Pfalz
		federalStateMap.put("10", "2642");// Saarland
		federalStateMap.put("14", "2643");// Sachsen
		federalStateMap.put("15", "2644");// Sachsen-Anhalt
		federalStateMap.put("01", "2645");// Schleswig-Holstein
		federalStateMap.put("16", "2646");// Thüringen

		final List<GermanZipcodeBean> list = getGermanZipcodeBeanList();
		final Countries germany = countriesService.find("DE");

		for (final GermanZipcodeBean bean : list)
		{
			final String zipcode = bean.getZipcode();
			final String federalStateKey = bean.getFederalStateKey();
			Addresses address = addressesService.findFirst(germany, zipcode);
			if (address != null)
			{
				final Integer id = Integer.valueOf(federalStateMap.get(federalStateKey));
				final Federalstates federalstate = federalstatesService.get(id);
				address = addressesService.get(address.getId());
				System.out.println(id);
				address.setFederalstate(federalstate);
				addressesService.merge(address);
			}
		}
	}

}
