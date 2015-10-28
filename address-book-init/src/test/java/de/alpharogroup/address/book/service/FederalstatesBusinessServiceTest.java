package de.alpharogroup.address.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.address.book.service.api.ZipcodesService;

@ContextConfiguration(locations = "classpath:test-em-applicationContext.xml")
public class FederalstatesBusinessServiceTest  extends AbstractTestNGSpringContextTests {

	@Autowired
	private CountriesService countriesService;
	@Autowired
	private ZipcodesService zipcodesService;
	@Autowired
	private AddressesService addressesService;
	@Autowired
	private FederalstatesService federalstatesService;
	@Test
	public void testFindFederalstatesFromCountryCountriesString() {
		Countries germany = countriesService.find("DE");
		List<Federalstates> federalstates = federalstatesService.findFederalstatesFromCountry(germany, "Hamburg");
		AssertJUnit.assertEquals(1, federalstates.size());
		Federalstates federalstate = federalstatesService.findFederalstate(germany, "Hamburg");
		AssertJUnit.assertNotNull(federalstate);
		AssertJUnit.assertEquals("Hamburg", federalstate.getName());
		
	}

}
