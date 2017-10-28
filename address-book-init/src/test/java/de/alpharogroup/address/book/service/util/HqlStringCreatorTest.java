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
package de.alpharogroup.address.book.service.util;

import static org.testng.AssertJUnit.assertEquals;

import org.testng.annotations.Test;

public class HqlStringCreatorTest
{

	@Test
	public void testWhereOrAnd() {
		String expected;
		String actual;
		expected = "where";
		actual = HqlStringCreator.whereOrAnd();
		assertEquals(expected, actual);

		expected = "and";
		actual = HqlStringCreator.whereOrAnd(true);
		assertEquals(expected, actual);

		expected = "where";
		actual = HqlStringCreator.whereOrAnd(false, false);
		assertEquals(expected, actual);

		expected = "and";
		actual = HqlStringCreator.whereOrAnd(true, false);
		assertEquals(expected, actual);

		expected = "and";
		actual = HqlStringCreator.whereOrAnd(true, false, false);
		assertEquals(expected, actual);

		expected = "and";
		actual = HqlStringCreator.whereOrAnd(true, true, false);
		assertEquals(expected, actual);

		expected = "where";
		actual = HqlStringCreator.whereOrAnd(false, false, false);
		assertEquals(expected, actual);
	}

	@Test(enabled = true)
	public void testForCountries()
	{
		String actual;
		String expected;

		actual = HqlStringCreator.forCountries(null, null, null, null);
		expected = "select c from Countries c";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forCountries("GR", null, null, null);
		expected = "select c from Countries c where c.iso3166A2name=:iso3166A2name";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forCountries("GR", "GRC", null, null);
		expected = "select c from Countries c where c.iso3166A2name=:iso3166A2name and c.iso3166A3name=:iso3166A3name";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forCountries("GR", "GRC", "300", null);
		expected = "select c from Countries c where c.iso3166A2name=:iso3166A2name and c.iso3166A3name=:iso3166A3name and c.iso3166Number=:iso3166Number";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forCountries("GR", "GRC", "300", "gr.grc");
		expected = "select c from Countries c where c.iso3166A2name=:iso3166A2name and c.iso3166A3name=:iso3166A3name and c.iso3166Number=:iso3166Number and c.name=:name";
		assertEquals(expected, actual);

	}

	@Test(enabled = false)
	public void testForZipcodes()
	{
		String actual;
		String expected;

		actual = HqlStringCreator.forZipcodes((String)null, null, null);
		expected = "select zc from Zipcodes zc";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forZipcodes("IT", null, null);
		expected = "select zc from Zipcodes zc where zc.country=:country";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forZipcodes("IT", "", null);
		expected = "select zc from Zipcodes zc where zc.country=:country";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forZipcodes("IT", null, "");
		expected = "select zc from Zipcodes zc where zc.country=:country";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forZipcodes("IT", "", "");
		expected = "select zc from Zipcodes zc where zc.country=:country";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forZipcodes("IT", "10827", null);
		expected = "select zc from Zipcodes zc where zc.country=:country and zc.zipcode=:zipcode";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forZipcodes((String)null, "10827", null);
		expected = "select zc from Zipcodes zc where zc.zipcode=:zipcode";
		assertEquals(expected, actual);

		actual = HqlStringCreator.forZipcodes("IT", "10827", "Berlin");
		expected = "select zc from Zipcodes zc where zc.country=:country and zc.zipcode=:zipcode and zc.city=:city";
		assertEquals(expected, actual);
	}

}
