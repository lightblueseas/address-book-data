package de.alpharogroup.address.book.service;


import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.business.service.api.AddressesService;
import de.alpharogroup.address.book.business.service.api.CountriesService;
import de.alpharogroup.address.book.business.service.api.FederalstatesService;
import de.alpharogroup.address.book.business.service.api.ZipcodesService;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.init.DeZipcodeBean;
import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.file.read.ReadFileUtils;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.jgeohash.GeoHashPoint;
import de.alpharogroup.jgeohash.distance.DistanceCalculator;
import de.alpharogroup.jgeohash.distance.MeasuringUnit;
import de.alpharogroup.xml.XmlUtils;


@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class AddressesBusinessServiceTest extends AbstractTestNGSpringContextTests
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
	public void testCreateAddress()
	{
		Addresses addr = addressesService.createAddress("Alter Teichweg", "188", "", "22049",
			"Hamburg", "Hamburg");
		System.out.println(addr);
		addr = addressesService
			.createAddress("Alter Teichweg", "188", "", "22049", "Hamburg", null);
		System.out.println(addr);
	}

	@Test(enabled = false)
	public void testFindGeohashNull()
	{
		List<Addresses> addresses = addressesService.findGeohashIsNull();
		for (Addresses address : addresses)
		{
			String l = address.getLatitude();
			String longtidude = address.getLongitude();
			double lat = Double.parseDouble(l);
			double lng = Double.parseDouble(longtidude);
			GeoHashPoint geoHashPoint = new GeoHashPoint(lat, lng);
			Addresses addr = addressesService.get(address.getId());
			addr.setGeohash(geoHashPoint.getGeohash());
			addressesService.merge(addr);
		}
	}

	@Test(enabled = false)
	public void getAllSwitzerlandAddresses()
	{
		Countries country = countriesService.find("DE");
		List<Zipcodes> countryZipcodes = zipcodesService.find(country);
		System.out.println("All zipcodes from austria:" + countryZipcodes.size());
		List<Zipcodes> zipcodesChInDb = addressesService.findAllAddressesWithCountry(country);
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

	@Test(enabled = false)
	public void validateAddressesWithSameCityname()
	{
		Countries country = countriesService.find("DE");
		List<Addresses> addresses = addressesService.findAll(country);
		for (Iterator<Addresses> iterator = addresses.iterator(); iterator.hasNext();)
		{
			Addresses address = iterator.next();
			List<Addresses> addressesSameCityName = addressesService.findAddressesWithSameCityname(
				address.getZipcode().getCountry(), address.getZipcode().getCity());
			if (4 < addressesSameCityName.size())
			{
				continue;
			}
			for (Addresses address2 : addressesSameCityName)
			{
				System.out.println(address2.getZipcode().getZipcode() + " "
					+ address2.getZipcode().getCity());
			}
		}
	}

	@Test(enabled = false)
	public void testGetInvalidAddresses()
	{
		Countries country = countriesService.find("DE");
		System.out.println(country.getName());
		List<Addresses> addresses = addressesService.findInvalidAddresses(country, "u4", false);
		System.out.println("addresses size:" + addresses.size());
		for (Addresses address : addresses)
		{

			System.out.println("processed :id:" + address.getId() + ":"
				+ address.getZipcode().getZipcode() + " " + address.getZipcode().getCity());
		}
		for (Addresses address : addresses)
		{
			System.out.println("to process:" + address.getZipcode().getZipcode() + " "
				+ address.getZipcode().getCity());
			List<Addresses> addressesSameCityName = addressesService.findAddressesWithSameCityname(
				address.getZipcode().getCountry(), address.getZipcode().getCity());
			// List<Addresses> addressesSameCityName =
			// addressesService.findAddressesWithSameZipcode(address.getZipcode().getCountry(),
			// address.getZipcode().getZipcode());
			addressesSameCityName.remove(address);
			for (Addresses address2 : addressesSameCityName)
			{
				if (address2.getGeohash().startsWith("u"))
				{
					Addresses freshFromDb = addressesService.get(address.getId());
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
		// +freshFromDb.getZipcode().getZipcode()+" "+freshFromDb.getZipcode().getCity());
		// }
	}

	@Test(enabled = false)
	public void valideAddressZipcodes() throws IOException
	{
		File smr = PathFinder.getSrcMainResourcesDir();
		File deDir = PathFinder.getRelativePath(smr, "zipcodes", "de");
		File input = new File(deDir, "DeZipcodes.xml");
		String notPrZipcodes = ReadFileUtils.readFromFile(input);
		List<DeZipcodeBean> deZipcodeBeanList = XmlUtils.toObjectWithXStream(notPrZipcodes);

		Countries country = countriesService.find("DE");
		for (DeZipcodeBean bean : deZipcodeBeanList)
		{
			List<Addresses> addresses = addressesService.find(country, bean.getZipcode(),
				bean.getCity());
			if (1 < addresses.size())
			{
				System.out.println("ambiguous:" + bean);
				continue;
			}
			Addresses addressInDb = ListExtensions.getFirst(addresses);

			if (addressInDb != null)
			{
				double latDb = Double.parseDouble(addressInDb.getLatitude());
				double lngDb = Double.parseDouble(addressInDb.getLongitude());
				double latBean;
				double lngBean;
				try
				{
					latBean = Double.parseDouble(bean.getLatitude());
					lngBean = Double.parseDouble(bean.getLongitude());
				}
				catch (NumberFormatException e1)
				{
					// TODO Auto-generated catch block
					System.out.println("=================================");
					System.out.println("This bean will is not processed:" + bean);
					System.out.println("=================================");
					e1.printStackTrace();
					break;
				}

				double distance = DistanceCalculator.distance(latDb, lngDb, latBean, lngBean,
					MeasuringUnit.KILOMETER);
				if (30.0 < distance)
				{
					GeoHashPoint point;
					try
					{
						point = new GeoHashPoint(bean.getLatitude(), bean.getLongitude());
					}
					catch (Exception e)
					{
						// TODO Auto-generated catch block
						System.out.println("=================================");
						System.out.println("This bean will is not processed:" + bean);
						System.out.println("=================================");
						e.printStackTrace();
						break;
					}
					Addresses addressToValide = addressesService.get(addressInDb.getId());
					addressToValide.setAddressComment("validated");
					addressToValide.setLatitude(bean.getLatitude());
					addressToValide.setLongitude(bean.getLongitude());
					addressToValide.setGeohash(point.getGeohash());
					addressesService.merge(addressToValide);
				}
			}
		}
	}


	protected File getProcessedDir()
	{
		File smr = PathFinder.getSrcMainResourcesDir();
		File processedDir = PathFinder.getRelativePath(smr, "zipcodes", "processed");
		return processedDir;
	}

}
