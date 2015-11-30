package de.alpharogroup.address.book.application.geocoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import de.alpharogroup.xml.XmlExtensions;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.jgeohash.GeoHashPoint;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The Class Geocoder gets data from google and geohash.org.
 */
public class Geocoder
{

	/** The Constant HTTP_MAPS_GOOGLE_COM_MAPS_API_GEOCODE_JSON. */
	public static final String HTTP_MAPS_GOOGLE_COM_MAPS_API_GEOCODE_JSON = "http://maps.google.com/maps/api/geocode/json";

	/** The Constant HTTP_MAPS_GOOGLE_COM_MAPS_API_GEOCODE_XML. */
	public static final String HTTP_MAPS_GOOGLE_COM_MAPS_API_GEOCODE_XML = "http://maps.google.com/maps/api/geocode/xml";

	/** The Constant HTTP_GEOHASH_ORG. */
	public static final String HTTP_GEOHASH_ORG = "http://geohash.org/";

	/**
	 * Sets the geocoding data.
	 * 
	 * @param address
	 *            the new geocoding data
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws SAXException
	 *             Signals that an SAX error or warning has occurred.
	 * @throws ParserConfigurationException
	 *             Indicates a serious configuration error.
	 */
	public static void setGeocodingData(final Addresses address) throws MalformedURLException,
		IOException, ParserConfigurationException, SAXException
	{
		String responseFromGoogle = Geocoder.getGeolocationAsXml(address.getStreet() + " "
			+ address.getStreetnumber() + " " + address.getZipcode().getZipcode() + " "
			+ address.getZipcode().getCity());
		GeoHashPoint geoHashModel = Geocoder.getGeocodingData(responseFromGoogle);
		if (geoHashModel != null)
		{
			address.setGeohash(geoHashModel.getGeohash());
			address.setLatitude(geoHashModel.getLat().toString());
			address.setLongitude(geoHashModel.getLng().toString());
		}
	}

	/**
	 * Gets the geo code data from the given Zipcodes Object. This method invokes in backround the
	 * 
	 * @param address
	 *            the address
	 * @return the geocoding data
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws SAXException
	 *             Signals that an SAX error or warning has occurred.
	 * @throws ParserConfigurationException
	 *             Indicates a serious configuration error.
	 */
	public static GeoHashPoint getGeocodingData(final Zipcodes address)
		throws MalformedURLException, IOException, ParserConfigurationException, SAXException
	{
		String responseFromGoogle = Geocoder.getGeolocationAsXml(address.getZipcode() + " "
			+ address.getCity());
		GeoHashPoint geoHashModel = Geocoder.getGeocodingData(responseFromGoogle);
		return geoHashModel;
	}

	/**
	 * Gets the geo code data from the given String that represents an xml string that is returned
	 * from the google geo code service.
	 * 
	 * @param xmldata
	 *            the xmldata
	 * @return the geocoding data as a GeoHashPoint.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws SAXException
	 *             Signals that an SAX error or warning has occurred.
	 * @throws ParserConfigurationException
	 *             Indicates a serious configuration error.
	 */
	public static GeoHashPoint getGeocodingData(final String xmldata)
		throws ParserConfigurationException, SAXException, IOException
	{
		GeoHashPoint model = null;
		Map<String, String> data = new HashMap<>();
		// Use an instance of ourselves as the SAX event handler
		DefaultHandler handler = new GoogleGeocodingHandler(data);

		// Use the default (non-validating) parser
		SAXParserFactory factory = SAXParserFactory.newInstance();

		// Parse the input
		SAXParser saxParser = factory.newSAXParser();
		saxParser.parse(XmlExtensions.getInputSource(xmldata), handler);

		if (data.size() == 2)
		{
			String l = data.get(GoogleGeocodingHandler.LAT);
			String longtidude = data.get(GoogleGeocodingHandler.LNG);
			double lat = Double.parseDouble(l);
			double lng = Double.parseDouble(longtidude);
			model = new GeoHashPoint(lat, lng);
		}
		return model;

	}

	/**
	 * Gets the geolocation as xml.
	 * 
	 * @param address
	 *            the address
	 * @return the geolocation as xml
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getGeolocationAsXml(final String address) throws MalformedURLException,
		IOException
	{
		String params = getUrlParams(address, false);
		String response = getGoogleXmlResponse(params);
		return response;
	}

	/**
	 * Gets the google xml response.
	 * 
	 * @param params
	 *            the params
	 * @return the google xml response
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getGoogleXmlResponse(final String params) throws MalformedURLException,
		IOException
	{
		URL url = new URL(HTTP_MAPS_GOOGLE_COM_MAPS_API_GEOCODE_XML + params);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);

		return getResponse(conn).toString();
	}

	/**
	 * Gets the json response.
	 * 
	 * @param params
	 *            the params
	 * @return the json response
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getJsonResponse(final String params) throws MalformedURLException,
		IOException
	{
		URL url = new URL(HTTP_MAPS_GOOGLE_COM_MAPS_API_GEOCODE_JSON + params);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);

		return getResponse(conn).toString();
	}

	private static StringBuilder getResponse(URLConnection connection) throws IOException
	{
		StringBuilder sb;
		try ( // Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream())))
		{
			String line;
			sb = new StringBuilder();
			while ((line = rd.readLine()) != null)
			{
				// Process line...
				sb.append(line).append("\n");
			}
		}
		return sb;
	}

	/**
	 * Gets the url params.
	 * 
	 * @param address
	 *            the address
	 * @param sensor
	 *            the sensor
	 * @return the url params
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public static String getUrlParams(final String address, final boolean sensor)
		throws UnsupportedEncodingException
	{
		StringBuilder sb = new StringBuilder();
		sb.append("?").append(URLEncoder.encode("address", "UTF-8")).append("=")
			.append(URLEncoder.encode(address, "UTF-8")).append("&")
			.append(URLEncoder.encode("sensor", "UTF-8")).append("=")
			.append(URLEncoder.encode(sensor + "", "UTF-8"));
		return sb.toString();
	}

	/**
	 * Gets the url parameters.
	 * 
	 * @param parameters
	 *            the parameters
	 * @return the url parameters
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public static String getUrlParameters(final Map<String, String> parameters)
		throws UnsupportedEncodingException
	{
		String result = "";
		if (parameters != null && 1 <= parameters.size())
		{
			StringBuilder sb = new StringBuilder();
			sb.append("?");
			Set<Entry<String, String>> entries = parameters.entrySet();
			for (Entry<String, String> entry : entries)
			{
				sb.append(URLEncoder.encode(entry.getKey(), "UTF-8")).append("=")
					.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
				sb.append("&");
			}
			result = sb.toString().trim().substring(0, sb.toString().trim().length() - 1);
		}
		return result;
	}

	/**
	 * Gets the plain text response.
	 * 
	 * @param geohash
	 *            the params
	 * @return the plain text response
	 * @throws MalformedURLException
	 *             the malformed url exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String getPlainTextResponse(final String geohash) throws MalformedURLException,
		IOException
	{
		URL url = new URL(HTTP_GEOHASH_ORG + geohash);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);

		StringBuilder sb;
		sb = getResponse(conn);
		return sb.toString();
	}
}
