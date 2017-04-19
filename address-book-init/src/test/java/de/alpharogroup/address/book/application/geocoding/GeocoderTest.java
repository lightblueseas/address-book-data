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
package de.alpharogroup.address.book.application.geocoding;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class GeocoderTest
{

	@Test(enabled = false)
	public void testGetGeocodingData()
	{
	}

	@Test(enabled = true)
	public void testGetGeolocationAsXml() throws MalformedURLException, IOException
	{
		String response = null;
		// Send data again...
		response = Geocoder.getGeolocationAsXml("64658 Faustenbach");
		System.out.println(response);
	}

	@Test(enabled = false)
	public void testGetGoogleXmlResponse() throws MalformedURLException, IOException
	{
		String response = null;
		// Construct data
		String params = Geocoder.getUrlParams("10779 Berlin", false);
		// Send data
		response = Geocoder.getGoogleXmlResponse(params);
		System.out.println("Google:" + response);
	}

	@Test(enabled = false)
	public void testGetJsonResponse()
	{
	}

	@Test(enabled = false)
	public void testGetPlainTextResponse() throws MalformedURLException, IOException
	{
		// Does not work...
		String response = null;
		Map<String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("q", "Münchener Straße 24, 10779 Berlin");
		parameters.put("format", "url");
		parameters.put("redirect", "0");
		String parms = Geocoder.getUrlParameters(parameters);
		System.out.println(parms);
		response = Geocoder.getPlainTextResponse(parms);
		System.out.println("============");
		System.out.println(response);
	}

	@Test(enabled = false)
	public void testGetUrlParameters()
	{
	}

	@Test(enabled = false)
	public void testGetUrlParams()
	{
	}

	@Test(enabled = false)
	public void testSetGeocodingData()
	{
	}

}
