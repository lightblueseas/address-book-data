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
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

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
import de.alpharogroup.file.read.ReadFileUtils;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.file.write.WriteFileUtils;
import de.alpharogroup.random.RandomExtensions;
import de.alpharogroup.xml.XmlUtils;
import de.alpharogroup.jgeohash.GeoHashPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
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

	@Test(enabled = true)
	public void testFindUsers()
	{
		Map<String, List<String>> map = countriesService.getCountriesToZipcodesAsStringMap();
		for (Entry<String, List<String>> entry : map.entrySet())
		{
			String country = entry.getKey();
			if (0 < entry.getValue().size())
			{
				System.out.println(country);
				for (String zipcode : entry.getValue())
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
		File processedDir = getProcessedDir();

		List<Zipcodes> notProcessed = new ArrayList<Zipcodes>();

		File npZipcodesFile = new File(processedDir, "npZipcodes.xml");

		String notPrZipcodes = ReadFileUtils.readFromFile(npZipcodesFile);
		notProcessed = XmlUtils.toObjectWithXStream(notPrZipcodes);

		System.out.println("Not processed zipcodes:" + notProcessed.size());
		for (Zipcodes zc : notProcessed)
		{
			System.out.println(zc.getZipcode() + " " + zc.getCity());
		}
	}

	@SuppressWarnings("static-access")
	@Test(enabled = false)
	public void getGeoHashCodesWithGermanZipcodes() throws MalformedURLException, IOException,
		ParserConfigurationException, SAXException
	{
		File processedDir = getProcessedDir();

		List<Zipcodes> processed = new ArrayList<Zipcodes>(findExistingZipcodesFromAddresses());
		System.out.println("Already processed:" + processed.size());
		List<Zipcodes> notProcessed = new ArrayList<Zipcodes>();

		File npZipcodesFile = new File(processedDir, "npZipcodes.xml");
		processed = new ArrayList<Zipcodes>(findExistingZipcodesFromAddresses());

		Countries germany = countriesService.find("SK");
		List<Zipcodes> germanZipcodes = zipcodesService.find(germany);
		germanZipcodes.removeAll(processed);
		String notPrZipcodes = ReadFileUtils.readFromFile(npZipcodesFile);
		notProcessed = XmlUtils.toObjectWithXStream(notPrZipcodes);
		System.out.println("Not processed:" + notProcessed.size());
		germanZipcodes.removeAll(notProcessed);
		int count = 1;
		System.out.println("Left to process:" + germanZipcodes.size());
		for (int i = 0; i < 2800; i++)
		{
			Zipcodes zc = germanZipcodes.get(i);
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
				address = addressesService.contains(point.getLat().toString().trim(), point
					.getLng().toString().trim());
			}
			catch (Exception e1)
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
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		String xmlNP = XmlUtils.toXmlWithXStream(notProcessed);
		WriteFileUtils.string2File(npZipcodesFile, xmlNP);
	}

	public Set<Zipcodes> findExistingZipcodesFromAddresses()
	{
		List<Addresses> addresses = addressesService.findAll();
		Set<Zipcodes> processed = new HashSet<Zipcodes>();
		for (Addresses address : addresses)
		{
			Zipcodes zc = address.getZipcode();
			if (zc != null)
			{
				processed.add(address.getZipcode());
			}
		}
		return processed;
	}

	@SuppressWarnings("static-access")
	@Test(enabled = false)
	public void saveGeoZipcodesToFile() throws IOException
	{

		File processedDir = getProcessedDir();
		File geoZipcodesFile = new File(processedDir, "geoZipcodes.xml");

		List<Zipcodes> processed = new ArrayList<Zipcodes>(findExistingZipcodesFromAddresses());
		System.out.println("Already processed:" + processed.size());

		List<Zipcodes> notProcessed = getNotProcessedList();

		List<Zipcodes> countryZipcodes = zipcodesService.findAll();
		countryZipcodes.removeAll(processed);
		System.out.println("Not processed:" + notProcessed.size());
		List<GeoPointZipcode> geopoints = new ArrayList<GeoPointZipcode>();
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
				int c = i + 1;
				System.out.println(c + ").loop");
				Zipcodes zc = countryZipcodes.get(i);
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
					geopoint = new GeoPointZipcode(zc, point);
					System.out.println((count++) + "). " + zc.getZipcode() + " " + zc.getCity());
				}
				else
				{
					continue;
				}

				try
				{
					address = addressesService.contains(point.getLat().toString().trim(), point
						.getLng().toString().trim());
				}
				catch (Exception e1)
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
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String xmlNP = XmlUtils.toXmlWithXStream(notProcessed);
		WriteFileUtils.string2File(getNotProcessedFile(), xmlNP);

		String xmlGeoPoints = XmlUtils.toXmlWithXStream(geopoints);
		WriteFileUtils.string2File(geoZipcodesFile, xmlGeoPoints);
		System.out.println("Finished at:" + new Date(System.currentTimeMillis()));
	}

	private File getProcessedDir()
	{
		File smr = PathFinder.getSrcMainResourcesDir();
		File processedDir = PathFinder.getRelativePath(smr, "zipcodes", "processed");
		return processedDir;
	}

	@Test(enabled = false)
	public void loadFromGeoZipcodesFileAndSaveToDb() throws IOException
	{
		File processedDir = getProcessedDir();

		File zipcodesFile = new File(processedDir, "zipcodes.xml");
		List<Zipcodes> processed = new ArrayList<Zipcodes>(findExistingZipcodesFromAddresses());

		File geoZipcodesFile = new File(processedDir, "geoZipcodes.xml");
		List<GeoPointZipcode> geoPointZipcodes = getGeoPointZipcodesList();
		int count = 1;
		System.out.println("geoPointZipcodes size is:" + geoPointZipcodes.size());
		for (int i = 0; i < geoPointZipcodes.size(); i++)
		{
			GeoPointZipcode geoPointZipcode = geoPointZipcodes.get(i);
			Zipcodes zc = geoPointZipcode.getZipcode();
			GeoHashPoint point = geoPointZipcode.getGeoHashPoint();
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
		String xml = XmlUtils.toXmlWithXStream(processed);
		WriteFileUtils.string2File(zipcodesFile, xml);
		String xmlGeo = XmlUtils.toXmlWithXStream(geoPointZipcodes);
		WriteFileUtils.string2File(geoZipcodesFile, xmlGeo);
		System.out.println("Finished at:" + new Date(System.currentTimeMillis()));
	}

	@Test(enabled = false)
	public void testDeleteDuplicateEntries()
	{
		List<Zipcodes> allZipcodes = zipcodesService.findAll();
		int size = allZipcodes.size();
		System.out.println(size);
		for (int i = 0; i < size; i++)
		{
			Zipcodes zc = allZipcodes.get(i);
			List<Addresses> addresses = addressesService.find(zc);
			for (int j = 1; j < addresses.size(); j++)
			{
				System.out.println(zc.getZipcode() + " " + zc.getCity());
				Addresses addr = addressesService.get(addresses.get(j).getId());
				addr.setZipcode(null);
				addr.setFederalstate(null);
				addressesService.merge(addr);
				addressesService.delete(addr);
			}
		}
	}

	@Test(enabled = false)
	public void getLatestNotProcessedZipcodes()
	{
		List<Zipcodes> processed = new ArrayList<Zipcodes>(findExistingZipcodesFromAddresses());
		Countries germany = countriesService.find("SK");
		List<Zipcodes> allGermanZipcodes = zipcodesService.findAll(germany, null, null);
		allGermanZipcodes.removeAll(processed);

		String xmlNP = XmlUtils.toXmlWithXStream(allGermanZipcodes);
		WriteFileUtils.string2File(getNotProcessedFile(), xmlNP);
	}

	private List<GermanZipcodeBean> getGermanZipcodeBeanList() throws IOException
	{
		File smr = PathFinder.getSrcMainResourcesDir();
		File deDir = PathFinder.getRelativePath(smr, "zipcodes", "de");

		File germanZipcodesXmlFile = new File(deDir, "GermanZipcodes.xml");
		String notPrZipcodes = ReadFileUtils.readFromFile(germanZipcodesXmlFile);
		List<GermanZipcodeBean> list = XmlUtils.toObjectWithXStream(notPrZipcodes);
		return list;
	}

	private List<Zipcodes> getNotProcessedList() throws IOException
	{
		File npZipcodesFile = getNotProcessedFile();
		String notPrZipcodes = ReadFileUtils.readFromFile(npZipcodesFile);
		List<Zipcodes> notProcessed = XmlUtils.toObjectWithXStream(notPrZipcodes);
		return notProcessed;
	}

	private File getNotProcessedFile()
	{
		File processedDir = getProcessedDir();

		File npZipcodesFile = new File(processedDir, "npZipcodes.xml");
		return npZipcodesFile;
	}

	private List<GeoPointZipcode> getGeoPointZipcodesList() throws IOException
	{
		File processedDir = getProcessedDir();

		File geoZipcodesFile = new File(processedDir, "geoZipcodes.xml");
		String geoZipcodes = ReadFileUtils.readFromFile(geoZipcodesFile);
		List<GeoPointZipcode> geoPointZipcodes = XmlUtils.toObjectWithXStream(geoZipcodes);
		return geoPointZipcodes;
	}

	@Test(enabled = false)
	protected void verifyZipcodes() throws IOException
	{
		// alien id, native id
		Map<String, String> federalStateMap = new HashMap<String, String>();
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

		List<GermanZipcodeBean> list = getGermanZipcodeBeanList();
		Countries germany = countriesService.find("DE");

		for (GermanZipcodeBean bean : list)
		{
			String zipcode = bean.getZipcode();
			String federalStateKey = bean.getFederalStateKey();
			Addresses address = addressesService.findFirst(germany, zipcode);
			if (address != null)
			{
				Integer id = Integer.valueOf(federalStateMap.get(federalStateKey));
				Federalstates federalstate = federalstatesService.get(id);
				address = addressesService.get(address.getId());
				System.out.println(id);
				address.setFederalstate(federalstate);
				addressesService.merge(address);
			}
		}
	}

	@Test(enabled = false)
	public void verifyNotProcessed() throws IOException
	{
		List<GermanZipcodeBean> list = getGermanZipcodeBeanList();
		Map<String, GermanZipcodeBean> zipcodeToBeanMap = new HashMap<String, GermanZipcodeBean>();
		for (GermanZipcodeBean germanZipcodeBean : list)
		{
			zipcodeToBeanMap.put(germanZipcodeBean.getZipcode(), germanZipcodeBean);
		}

		List<Zipcodes> npZipcodes = getNotProcessedList();
		System.out.println("Not processed:" + npZipcodes.size());
		int count = 0;
		for (Zipcodes zipcode : npZipcodes)
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
	public void fillNotProcessedZipcodesWithGeocodeData() throws IOException
	{
		List<Zipcodes> npZipcodes = getNotProcessedList();
		System.out.println("Not processed:" + npZipcodes.size());
		List<Zipcodes> newNpZipcodes = new ArrayList<Zipcodes>();
		int count = 1;
		for (Zipcodes zipcode : npZipcodes)
		{
			if (addressesService.contains(zipcode) != null)
			{
				System.out.println("np" + (count++) + "). " + zipcode.getZipcode() + " "
					+ zipcode.getCity());
				continue;
			}
			Addresses found = findNextAddressToZipcode(zipcode);
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
				Federalstates fs = federalstatesService.get(found.getFederalstate().getId());
				address.setFederalstate(fs);
			}
			addressesService.merge(address);
		}
		String xmlNP = XmlUtils.toXmlWithXStream(newNpZipcodes);
		WriteFileUtils.string2File(getNotProcessedFile(), xmlNP);
	}

	public Addresses findNextAddressToZipcode(Zipcodes zipcode)
	{
		String zcString = zipcode.getZipcode();
		List<Addresses> addresses = addressesService.find(zipcode.getCountry(),
			zipcode.getZipcode());
		Addresses found = null;
		if (addresses != null && !addresses.isEmpty())
		{
			found = addresses.get(0);
		}
		if (found != null)
		{
			return found;
		}
		int zcInt = Integer.valueOf(zcString) - 1;
		Countries country = countriesService.get(zipcode.getCountry().getId());
		int count = 0;
		while (found == null)
		{
			count++;
			if (count == 3)
			{
				break;
			}
			List<Zipcodes> zcs = zipcodesService.findAll(country, zcInt + "", null);
			if (zcs != null && !zcs.isEmpty())
			{
				Zipcodes zc = zcs.get(0);
				addresses = addressesService.find(zc);
				if (addresses != null && !addresses.isEmpty())
				{
					found = addresses.get(0);
				}
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

}
