package de.alpharogroup.address.book.application.geocoding;


import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.Test;

import de.alpharogroup.address.book.application.geocoding.Geocoder;


public class GeocoderTest {

	@Test(enabled=false)
	public void testSetGeocodingData() {
	}

	@Test(enabled=false)
	public void testGetGeocodingData() {
	}

	@Test(enabled=true)
	public void testGetGeolocationAsXml() throws MalformedURLException, IOException {
		String response = null;
		// Send data again...
		response = Geocoder.getGeolocationAsXml("64658 Faustenbach");
		System.out.println(response);
	}

	@Test(enabled=false)
	public void testGetGoogleXmlResponse() throws MalformedURLException, IOException {
		String response = null;
		// Construct data
		String params = Geocoder.getUrlParams("10779 Berlin", false);
		// Send data
		response = Geocoder.getGoogleXmlResponse(params);
		System.out.println("Google:"+response);
	}

	@Test(enabled=false)
	public void testGetJsonResponse() {
	}

	@Test(enabled=false)
	public void testGetUrlParams() {
	}

	@Test(enabled=false)
	public void testGetUrlParameters() {
	}

	@Test(enabled=false)
	public void testGetPlainTextResponse() throws MalformedURLException, IOException {
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

}
