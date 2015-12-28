package de.alpharogroup.address.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.FederalstatesService;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class FederalstatesBusinessServiceTest  extends AbstractTestNGSpringContextTests {

	@Autowired
	private CountriesService countriesService;
	@Autowired
	private FederalstatesService federalstatesService;
	@Test(enabled = false)
	public void testFindFederalstatesFromCountryCountriesString() {
		final Countries germany = countriesService.find("DE");
		List<Federalstates> federalstates = federalstatesService.findFederalstatesFromCountry(germany, "Hamburg");
		AssertJUnit.assertEquals(1, federalstates.size());
		final Federalstates federalstate = federalstatesService.findFederalstate(germany, "Hamburg");
		AssertJUnit.assertNotNull(federalstate);
		AssertJUnit.assertEquals("Hamburg", federalstate.getName());
		federalstates = federalstatesService.findFederalstatesFromCountry(germany);
		System.out.println(federalstates);

	}
	@Test(enabled = false)
	public void testFindFederalstatesFromCountryCountries() {
		final Countries germany = countriesService.find("DE");
		final List<Federalstates> federalstates = federalstatesService.findFederalstatesFromCountry(germany);
		AssertJUnit.assertEquals(16, federalstates.size());
	}

}
