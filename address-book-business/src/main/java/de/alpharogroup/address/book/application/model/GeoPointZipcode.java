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
package de.alpharogroup.address.book.application.model;

import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.jgeohash.GeoHashPoint;

/**
 * The Class GeoPointZipcode associate a Zipcodes Object with a GeoHashPoint
 * Object.
 */
public class GeoPointZipcode {

	/** The geo hash point. */
	private GeoHashPoint geoHashPoint;

	/** The zipcode. */
	private Zipcodes zipcode;

	/**
	 * Instantiates a new geo point zipcode.
	 *
	 * @param zipcode
	 *            the zipcode
	 * @param geoHashPoint
	 *            the geo hash point
	 */
	public GeoPointZipcode(Zipcodes zipcode, GeoHashPoint geoHashPoint) {
		super();
		this.zipcode = zipcode;
		this.geoHashPoint = geoHashPoint;
	}

	/**
	 * Gets the geo hash point.
	 *
	 * @return the geo hash point
	 */
	public GeoHashPoint getGeoHashPoint() {
		return geoHashPoint;
	}

	/**
	 * Gets the zipcode.
	 *
	 * @return the zipcode
	 */
	public Zipcodes getZipcode() {
		return zipcode;
	}

	/**
	 * Sets the geo hash point.
	 *
	 * @param geoHashPoint
	 *            the new geo hash point
	 */
	public void setGeoHashPoint(GeoHashPoint geoHashPoint) {
		this.geoHashPoint = geoHashPoint;
	}

	/**
	 * Sets the zipcode.
	 *
	 * @param zipcode
	 *            the new zipcode
	 */
	public void setZipcode(Zipcodes zipcode) {
		this.zipcode = zipcode;
	}
}
