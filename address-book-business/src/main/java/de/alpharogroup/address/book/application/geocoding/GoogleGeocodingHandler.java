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

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import de.alpharogroup.xml.sax.handler.BreakParsingException;

public class GoogleGeocodingHandler extends DefaultHandler {

	/** The Constant LOCATION. */
	protected static final String LOCATION = "location";

	public static final String LAT = "lat";
	public static final String LNG = "lng";
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(final String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: cmd filename");
			System.exit(1);
		}
		Map<String, String> map = new HashMap<>();
		// Use an instance of ourselves as the SAX event handler
		DefaultHandler handler = new GoogleGeocodingHandler(map);
		// Use the default (non-validating) parser
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try { // Parse the input
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(new File(args[0]), handler);
		} catch (Throwable t) {
			if (t instanceof BreakParsingException) {
				// ignore...
				System.out.println("Parsing is purposely break.");
			} else {
				t.printStackTrace();
			}
		}
		System.out.println(map);
		System.exit(0);
	}

	/** The data. */
	private final Map<String, String> data;

	/** The found attr. */
	boolean location = false;

	boolean lat = false;

	boolean lng = false;

	public GoogleGeocodingHandler(final Map<String, String> data) {
		this.data = data;
	}

	/**
	 * Characters.
	 *
	 * @param buf
	 *            the buf
	 * @param offset
	 *            the offset
	 * @param len
	 *            the len
	 * @throws SAXException
	 *             the sAX exception {@inheritDoc}
	 */
	@Override
	public void characters(final char[] buf, final int offset, final int len) throws SAXException {
		String s = new String(buf, offset, len);
		if (location) {
			if (lat) {
				data.put(LAT, s);
				lat = false;
			}
			if (lng) {
				data.put(LNG, s);
				lng = false;
			}
		}
	}

	/**
	 * Start element.
	 *
	 * @param namespaceURI
	 *            the namespace uri
	 * @param simpleName
	 *            the simple name
	 * @param qualifiedName
	 *            the qualified name
	 * @param attributes
	 *            the attributes
	 * @throws SAXException
	 *             the sAX exception {@inheritDoc}
	 */
	@Override
	public void startElement(final String namespaceURI, final String simpleName, final String qualifiedName,
			final Attributes attributes) throws SAXException {
		String elementName = simpleName;

		if ("".equals(elementName)) {
			elementName = qualifiedName;
		}
		if (elementName.equals(LOCATION)) {
			location = true;
		}
		if (elementName.equals(LAT)) {
			lat = true;
		}
		if (elementName.equals(LNG)) {
			lng = true;
		}

		if (elementName.equals("location_type")) {
			throw new BreakParsingException("Stopped Parsing file...");
		}
	}

}
