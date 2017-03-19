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

import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.jgeohash.Adjacent;

/**
 * The class {@link HqlStringCreator}.
 */
public class HqlStringCreator {

	/**
	 * Generates hql script for addresses.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @return the generated hql string
	 */
	public static String forAddresses(Countries country, String zipcode, String city) {
		final StringBuilder sb = new StringBuilder();
		sb.append("select a from " + Addresses.class.getSimpleName() + " a");
		final boolean countryIsNotNull = country != null;
		if (countryIsNotNull) {
			sb.append(" ");
			sb.append("where a.zipcode.country=:country");
		}
		final boolean zipcodeIsNotNull = zipcode != null && !zipcode.isEmpty();
		if (zipcodeIsNotNull) {
			sb.append(" ");
			if (!countryIsNotNull) {
				sb.append("where a.zipcode.zipcode=:zipcode");
			} else {
				sb.append("and a.zipcode.zipcode=:zipcode");
			}
		}

		final boolean cityIsNotNull = city != null && !city.isEmpty();
		if (cityIsNotNull) {
			sb.append(" ");
			if (!countryIsNotNull && !zipcodeIsNotNull) {
				sb.append("where a.zipcode.city like :city");
			} else {
				sb.append("and a.zipcode.city like :city");
			}
		}

		return sb.toString().trim();
	}

	/**
	 * Generates hql script for countries.
	 *
	 * @param iso3166A2name
	 *            the iso3166 a2name
	 * @param iso3166A3name
	 *            the iso3166 a3name
	 * @param iso3166Number
	 *            the iso3166 number
	 * @param name
	 *            the name
	 * @return the string
	 */
	public static String forCountries(final String iso3166A2name, final String iso3166A3name,
			final String iso3166Number, final String name) {
		final StringBuilder sb = new StringBuilder();
		sb.append("select c from " + Countries.class.getSimpleName() + " c");
		final boolean iso3166A2nameIsNotNull = iso3166A2name != null && !iso3166A2name.isEmpty();
		if (iso3166A2nameIsNotNull) {
			sb.append(" ");
			sb.append("where c.iso3166A2name=:iso3166A2name");
		}
		final boolean iso3166A3nameIsNotNull = iso3166A3name != null && !iso3166A3name.isEmpty();
		if (iso3166A3nameIsNotNull) {
			sb.append(" ");
			if (!iso3166A2nameIsNotNull) {
				sb.append("where c.iso3166A3name=:iso3166A3name");
			} else {
				sb.append("and c.iso3166A3name=:iso3166A3name");
			}
		}
		final boolean iso3166NumberIsNotNull = iso3166Number != null && !iso3166Number.isEmpty();
		if (iso3166NumberIsNotNull) {
			sb.append(" ");
			if (!iso3166A2nameIsNotNull && !iso3166A3nameIsNotNull) {
				sb.append("where c.iso3166Number=:iso3166Number");
			} else {
				sb.append("and c.iso3166Number=:iso3166Number");
			}
		}
		final boolean nameIsNotNull = name != null && !name.isEmpty();
		if (nameIsNotNull) {
			sb.append(" ");
			if (!iso3166A2nameIsNotNull && !iso3166A3nameIsNotNull && !iso3166NumberIsNotNull) {
				sb.append("where c.name=:name");
			} else {
				sb.append("and c.name=:name");
			}
		}
		return sb.toString().trim();
	}

	/**
	 * Generates hql script for zipcodes.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @return the string
	 */
	public static String forZipcodes(final Countries country, final String zipcode, final String city) {
		final StringBuilder sb = new StringBuilder();
		sb.append("select zc from " + Zipcodes.class.getSimpleName() + " zc");
		final boolean countryIsNotNull = country != null;
		if (countryIsNotNull) {
			sb.append(" ");
			sb.append("where zc.country=:country");
		}
		final boolean zipcodeIsNotNull = zipcode != null && !zipcode.isEmpty();
		if (zipcodeIsNotNull) {
			sb.append(" ");
			if (!countryIsNotNull) {
				sb.append("where zc.zipcode=:zipcode");
			} else {
				sb.append("and zc.zipcode=:zipcode");
			}
		}
		final boolean cityIsNotNull = city != null && !city.isEmpty();
		if (cityIsNotNull) {
			sb.append(" ");
			if (!countryIsNotNull && !zipcodeIsNotNull) {
				sb.append("where zc.city=:city");
			} else {
				sb.append("and zc.city=:city");
			}
		}
		return sb.toString().trim();
	}

	/**
	 * Generates hql script for zipcodes.
	 *
	 * @param country
	 *            the country
	 * @param zipcode
	 *            the zipcode
	 * @param city
	 *            the city
	 * @return the string
	 */
	public static String forZipcodes(final String country, final String zipcode, final String city) {
		final StringBuilder sb = new StringBuilder();
		sb.append("select zc from " + Zipcodes.class.getSimpleName() + " zc");
		final boolean countryIsNotNull = country != null && !country.isEmpty();
		if (countryIsNotNull) {
			sb.append(" ");
			sb.append("where zc.country=:country");
		}
		final boolean zipcodeIsNotNull = zipcode != null && !zipcode.isEmpty();
		if (zipcodeIsNotNull) {
			sb.append(" ");
			if (!countryIsNotNull) {
				sb.append("where zc.zipcode=:zipcode");
			} else {
				sb.append("and zc.zipcode=:zipcode");
			}
		}
		final boolean cityIsNotNull = city != null && !city.isEmpty();
		if (cityIsNotNull) {
			sb.append(" ");
			if (!countryIsNotNull && !zipcodeIsNotNull) {
				sb.append("where zc.city=:city");
			} else {
				sb.append("and zc.city=:city");
			}
		}
		return sb.toString().trim();
	}

	/**
	 * Gets the geohash first and second ring query.
	 *
	 * @param asAddress
	 *            the as address flag if the result shell be objects or geohash
	 *            values.
	 * @return the geohash first and second ring query
	 */
	public static String getGeohashFirstAndSecondRingQuery(final boolean asAddress) {
		String value;
		if (asAddress) {
			value = "address";
		} else {
			value = "address.geohash";
		}
		final StringBuilder sb = new StringBuilder();
		sb.append("select " + value + " from Addresses address "
		// First ring...
				+ "where address.geohash like :" + Adjacent.CENTER + " " + "or address.geohash like :" + Adjacent.TOP
				+ " " + "or address.geohash like :" + Adjacent.TOP_RIGHT + " " + "or address.geohash like :"
				+ Adjacent.RIGHT + " " + "or address.geohash like :" + Adjacent.BOTTOM_RIGHT + " "
				+ "or address.geohash like :" + Adjacent.BOTTOM + " " + "or address.geohash like :"
				+ Adjacent.BOTTOM_LEFT + " " + "or address.geohash like :" + Adjacent.LEFT + " "
				+ "or address.geohash like :" + Adjacent.TOP_LEFT + " "
				// Second ring...
				+ "or address.geohash like :" + Adjacent.TOP_LEFT_TOP + " " + "or address.geohash like :"
				+ Adjacent.TOP_TOP + " " + "or address.geohash like :" + Adjacent.TOP_RIGHT_TOP + " "
				+ "or address.geohash like :" + Adjacent.TOP_RIGHT_TOP_RIGHT + " " + "or address.geohash like :"
				+ Adjacent.TOP_RIGHT_RIGHT + " " + "or address.geohash like :" + Adjacent.RIGHT_RIGHT + " "
				+ "or address.geohash like :" + Adjacent.BOTTOM_RIGHT_RIGHT + " " + "or address.geohash like :"
				+ Adjacent.BOTTOM_RIGHT_BOTTOM_RIGHT + " " + "or address.geohash like :" + Adjacent.BOTTOM_RIGHT_BOTTOM
				+ " " + "or address.geohash like :" + Adjacent.BOTTOM_BOTTOM + " " + "or address.geohash like :"
				+ Adjacent.BOTTOM_LEFT_BOTTOM + " " + "or address.geohash like :" + Adjacent.BOTTOM_LEFT_BOTTOM_LEFT
				+ " " + "or address.geohash like :" + Adjacent.BOTTOM_LEFT_LEFT + " " + "or address.geohash like :"
				+ Adjacent.LEFT_LEFT + " " + "or address.geohash like :" + Adjacent.TOP_LEFT_LEFT + " "
				+ "or address.geohash like :" + Adjacent.TOP_LEFT_TOP_LEFT + " ");
		return sb.toString().trim();
	}

	/**
	 * Gets the geohash first and second ring sub query.
	 *
	 * @return the geohash first and second ring sub query
	 */
	public static String getGeohashFirstAndSecondRingSubQuery() {
		final StringBuilder sb = new StringBuilder();
		sb.append(
				// Subselect start...
				"(" + getGeohashFirstAndSecondRingQuery(false) + ")"
		// Subselect end...
		);
		return sb.toString().trim();
	}

	/**
	 * Gets the geohash first ring query.
	 *
	 * @param asAddress
	 *            the as address flag if the result shell be objects or geohash
	 *            values.
	 * @return the geohash first ring query
	 */
	public static String getGeohashFirstRingQuery(final boolean asAddress) {
		String value;
		if (asAddress) {
			value = "address";
		} else {
			value = "address.geohash";
		}
		final StringBuilder sb = new StringBuilder();
		sb.append("select " + value + " from Addresses address "
		// First ring...
				+ "where address.geohash like :" + Adjacent.CENTER + " " + "or address.geohash like :" + Adjacent.TOP
				+ " " + "or address.geohash like :" + Adjacent.TOP_RIGHT + " " + "or address.geohash like :"
				+ Adjacent.RIGHT + " " + "or address.geohash like :" + Adjacent.BOTTOM_RIGHT + " "
				+ "or address.geohash like :" + Adjacent.BOTTOM + " " + "or address.geohash like :"
				+ Adjacent.BOTTOM_LEFT + " " + "or address.geohash like :" + Adjacent.LEFT + " "
				+ "or address.geohash like :" + Adjacent.TOP_LEFT + " ");
		return sb.toString().trim();
	}

	/**
	 * Gets the geohash first ring sub query.
	 *
	 * @return the geohash first ring sub query
	 */
	public static String getGeohashFirstRingSubQuery() {
		final StringBuilder sb = new StringBuilder();
		sb.append(
				// Subselect start...
				"(" + getGeohashFirstRingQuery(false) + ")"
		// Subselect end...
		);
		return sb.toString().trim();
	}

	/**
	 * Gets the geohash query.
	 *
	 * @return the geohash query
	 */
	public static String getGeohashQuery() {
		final StringBuilder sb = new StringBuilder();
		sb.append("select address.geohash from " + Addresses.class.getSimpleName() + " address "
				+ "where address.geohash like :" + Adjacent.CENTER + " ");
		return sb.toString().trim();
	}

	/**
	 * Gets the geohash sub query.
	 *
	 * @return the geohash sub query
	 */
	public static String getGeohashSubQuery() {
		final StringBuilder sb = new StringBuilder();
		sb.append(
				// Subselect start...
				"(" + getGeohashQuery() + ")"
		// Subselect end...
		);
		return sb.toString().trim();
	}

}
