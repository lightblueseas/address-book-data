package de.alpharogroup.address.book.service.util;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class HqlStringCreatorTest {

	@Test(enabled=false)
	public void testForZipcodes() {
		String actual;
		String expected;

		actual = HqlStringCreator.forZipcodes((String)null, null, null);
		expected = "select zc from Zipcodes zc";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forZipcodes("IT", null, null);
		expected = "select zc from Zipcodes zc where zc.country=:country";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forZipcodes("IT", "", null);
		expected = "select zc from Zipcodes zc where zc.country=:country";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forZipcodes("IT", null, "");
		expected = "select zc from Zipcodes zc where zc.country=:country";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forZipcodes("IT", "", "");
		expected = "select zc from Zipcodes zc where zc.country=:country";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forZipcodes("IT", "10827", null);
		expected = "select zc from Zipcodes zc where zc.country=:country and zc.zipcode=:zipcode";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forZipcodes((String)null, "10827", null);
		expected = "select zc from Zipcodes zc where zc.zipcode=:zipcode";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forZipcodes("IT", "10827", "Berlin");
		expected = "select zc from Zipcodes zc where zc.country=:country and zc.zipcode=:zipcode and zc.city=:city";		
		AssertJUnit.assertEquals(expected, actual);		
	}
	
	@Test(enabled=true)
	public void testForCountries() {
		String actual;
		String expected;
		
		actual = HqlStringCreator.forCountries(null, null, null, null);
		expected = "select c from Countries c";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forCountries("GR", null, null, null);
		expected = "select c from Countries c where c.iso3166A2name=:iso3166A2name";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forCountries("GR", "GRC", null, null);
		expected = "select c from Countries c where c.iso3166A2name=:iso3166A2name and c.iso3166A3name=:iso3166A3name";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forCountries("GR", "GRC", "300", null);
		expected = "select c from Countries c where c.iso3166A2name=:iso3166A2name and c.iso3166A3name=:iso3166A3name and c.iso3166Number=:iso3166Number";		
		AssertJUnit.assertEquals(expected, actual);
		
		actual = HqlStringCreator.forCountries("GR", "GRC", "300", "gr.grc");
		expected = "select c from Countries c where c.iso3166A2name=:iso3166A2name and c.iso3166A3name=:iso3166A3name and c.iso3166Number=:iso3166Number and c.name=:name";		
		AssertJUnit.assertEquals(expected, actual);
			
	}

}
