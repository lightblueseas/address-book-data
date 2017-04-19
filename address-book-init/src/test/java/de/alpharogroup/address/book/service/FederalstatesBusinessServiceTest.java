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
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.FederalstatesService;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class FederalstatesBusinessServiceTest extends AbstractTestNGSpringContextTests
{

	@Autowired
	private CountriesService countriesService;
	@Autowired
	private FederalstatesService federalstatesService;

	@Test(enabled = false)
	public void testFindFederalstatesFromCountryCountries()
	{
		final Countries germany = countriesService.find("DE");
		final List<Federalstates> federalstates = federalstatesService
			.findFederalstatesFromCountry(germany);
		AssertJUnit.assertEquals(16, federalstates.size());
	}

	@Test(enabled = false)
	public void testFindFederalstatesFromCountryCountriesString()
	{
		final Countries germany = countriesService.find("DE");
		List<Federalstates> federalstates = federalstatesService
			.findFederalstatesFromCountry(germany, "Hamburg");
		AssertJUnit.assertEquals(1, federalstates.size());
		final Federalstates federalstate = federalstatesService.findFederalstate(germany,
			"Hamburg");
		AssertJUnit.assertNotNull(federalstate);
		AssertJUnit.assertEquals("Hamburg", federalstate.getName());
		federalstates = federalstatesService.findFederalstatesFromCountry(germany);
		System.out.println(federalstates);

	}

}
