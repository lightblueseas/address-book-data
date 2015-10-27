package de.alpharogroup.address.book.application.model;

import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.jgeohash.GeoHashPoint;

/**
 * The Class GeoPointZipcode associate a Zipcodes Object with a GeoHashPoint Object.
 */
public class GeoPointZipcode
{

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
	public GeoPointZipcode(Zipcodes zipcode, GeoHashPoint geoHashPoint)
	{
		super();
		this.zipcode = zipcode;
		this.geoHashPoint = geoHashPoint;
	}

	/**
	 * Gets the geo hash point.
	 *
	 * @return the geo hash point
	 */
	public GeoHashPoint getGeoHashPoint()
	{
		return geoHashPoint;
	}

	/**
	 * Gets the zipcode.
	 *
	 * @return the zipcode
	 */
	public Zipcodes getZipcode()
	{
		return zipcode;
	}

	/**
	 * Sets the geo hash point.
	 *
	 * @param geoHashPoint
	 *            the new geo hash point
	 */
	public void setGeoHashPoint(GeoHashPoint geoHashPoint)
	{
		this.geoHashPoint = geoHashPoint;
	}

	/**
	 * Sets the zipcode.
	 *
	 * @param zipcode
	 *            the new zipcode
	 */
	public void setZipcode(Zipcodes zipcode)
	{
		this.zipcode = zipcode;
	}
}
