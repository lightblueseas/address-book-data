package de.alpharogroup.address.book.service;


import java.util.List;

import de.alpharogroup.address.book.business.service.api.AddressesService;
import de.alpharogroup.address.book.business.service.api.CountriesService;
import de.alpharogroup.address.book.business.service.api.FederalstatesService;
import de.alpharogroup.address.book.business.service.api.ZipcodesService;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.jgeohash.GeoHashPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class ReadUnitedKingdomCountriesBusinessServiceTest extends AbstractTestNGSpringContextTests
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

}
