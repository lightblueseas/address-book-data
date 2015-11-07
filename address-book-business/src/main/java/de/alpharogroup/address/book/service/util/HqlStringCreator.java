package de.alpharogroup.address.book.service.util;

import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.jgeohash.Adjacent;

public class HqlStringCreator
{
	public static String forZipcodes(String country, String zipcode, String city)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select zc from Zipcodes zc");
		boolean countryIsNotNull = country != null;
		if (countryIsNotNull)
		{
			sb.append(" ");
			sb.append("where zc.country=:country");
		}
		boolean zipcodeIsNotNull = zipcode != null && !zipcode.isEmpty();
		if (zipcodeIsNotNull)
		{
			sb.append(" ");
			if (!countryIsNotNull)
			{
				sb.append("where zc.zipcode=:zipcode");
			}
			else
			{
				sb.append("and zc.zipcode=:zipcode");
			}
		}
		boolean cityIsNotNull = city != null && !city.isEmpty();
		if (cityIsNotNull)
		{
			sb.append(" ");
			if (!countryIsNotNull && !zipcodeIsNotNull)
			{
				sb.append("where zc.city=:city");
			}
			else
			{
				sb.append("and zc.city=:city");
			}
		}
		return sb.toString().trim();
	}

	public static String forCountries(String iso3166A2name, String iso3166A3name,
		String iso3166Number, String name)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select c from Countries c");
		boolean iso3166A2nameIsNotNull = iso3166A2name != null && !iso3166A2name.isEmpty();
		if (iso3166A2nameIsNotNull)
		{
			sb.append(" ");
			sb.append("where c.iso3166A2name=:iso3166A2name");
		}
		boolean iso3166A3nameIsNotNull = iso3166A3name != null && !iso3166A3name.isEmpty();
		if (iso3166A3nameIsNotNull)
		{
			sb.append(" ");
			if (!iso3166A2nameIsNotNull)
			{
				sb.append("where c.iso3166A3name=:iso3166A3name");
			}
			else
			{
				sb.append("and c.iso3166A3name=:iso3166A3name");
			}
		}
		boolean iso3166NumberIsNotNull = iso3166Number != null && !iso3166Number.isEmpty();
		if (iso3166NumberIsNotNull)
		{
			sb.append(" ");
			if (!iso3166A2nameIsNotNull && !iso3166A3nameIsNotNull)
			{
				sb.append("where c.iso3166Number=:iso3166Number");
			}
			else
			{
				sb.append("and c.iso3166Number=:iso3166Number");
			}
		}
		boolean nameIsNotNull = name != null && !name.isEmpty();
		if (nameIsNotNull)
		{
			sb.append(" ");
			if (!iso3166A2nameIsNotNull && !iso3166A3nameIsNotNull && !iso3166NumberIsNotNull)
			{
				sb.append("where c.name=:name");
			}
			else
			{
				sb.append("and c.name=:name");
			}
		}
		return sb.toString().trim();
	}

	public String forAddresses(
	// String addressComment,
	// Federalstates federalstate,
		String geohash, String latitude, String longitude
	// , String street, String streetnumber,
	// Zipcodes zipcode
	)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");

		return sb.toString().trim();
	}

	public static String getGeohashSubQuery()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append(
		// Subselect start...
		"(" + getGeohashQuery() + ")"
		// Subselect end...
		);
		return sb.toString().trim();
	}

	public static String getGeohashQuery()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select address.geohash from Addresses address " + "where address.geohash like :"
			+ Adjacent.CENTER + " ");
		return sb.toString().trim();
	}

	public static String getGeohashFirstRingSubQuery()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append(
		// Subselect start...
		"(" + getGeohashFirstRingQuery() + ")"
		// Subselect end...
		);
		return sb.toString().trim();
	}

	public static String getGeohashFirstRingQuery()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select address.geohash from Addresses address "
		// First ring...
			+ "where address.geohash like :" + Adjacent.CENTER + " " + "or address.geohash like :"
			+ Adjacent.TOP + " " + "or address.geohash like :" + Adjacent.TOP_RIGHT + " "
			+ "or address.geohash like :" + Adjacent.RIGHT + " " + "or address.geohash like :"
			+ Adjacent.BOTTOM_RIGHT + " " + "or address.geohash like :" + Adjacent.BOTTOM + " "
			+ "or address.geohash like :" + Adjacent.BOTTOM_LEFT + " "
			+ "or address.geohash like :" + Adjacent.LEFT + " " + "or address.geohash like :"
			+ Adjacent.TOP_LEFT + " ");
		return sb.toString().trim();
	}

	public static String getGeohashFirstAndSecondRingSubQuery()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append(
		// Subselect start...
		"(" + getGeohashFirstAndSecondRingQuery() + ")"
		// Subselect end...
		);
		return sb.toString().trim();
	}

	public static String getGeohashFirstAndSecondRingQuery()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("select address.geohash from Addresses address "
		// First ring...
			+ "where address.geohash like :" + Adjacent.CENTER + " " + "or address.geohash like :"
			+ Adjacent.TOP + " " + "or address.geohash like :" + Adjacent.TOP_RIGHT + " "
			+ "or address.geohash like :"
			+ Adjacent.RIGHT
			+ " "
			+ "or address.geohash like :"
			+ Adjacent.BOTTOM_RIGHT
			+ " "
			+ "or address.geohash like :"
			+ Adjacent.BOTTOM
			+ " "
			+ "or address.geohash like :"
			+ Adjacent.BOTTOM_LEFT
			+ " "
			+ "or address.geohash like :"
			+ Adjacent.LEFT
			+ " "
			+ "or address.geohash like :"
			+ Adjacent.TOP_LEFT
			+ " "
			// Second ring...
			+ "or address.geohash like :" + Adjacent.TOP_LEFT_TOP + " "
			+ "or address.geohash like :" + Adjacent.TOP_TOP + " " + "or address.geohash like :"
			+ Adjacent.TOP_RIGHT_TOP + " " + "or address.geohash like :"
			+ Adjacent.TOP_RIGHT_TOP_RIGHT + " " + "or address.geohash like :"
			+ Adjacent.TOP_RIGHT_RIGHT + " " + "or address.geohash like :" + Adjacent.RIGHT_RIGHT
			+ " " + "or address.geohash like :" + Adjacent.BOTTOM_RIGHT_RIGHT + " "
			+ "or address.geohash like :" + Adjacent.BOTTOM_RIGHT_BOTTOM_RIGHT + " "
			+ "or address.geohash like :" + Adjacent.BOTTOM_RIGHT_BOTTOM + " "
			+ "or address.geohash like :" + Adjacent.BOTTOM_BOTTOM + " "
			+ "or address.geohash like :" + Adjacent.BOTTOM_LEFT_BOTTOM + " "
			+ "or address.geohash like :" + Adjacent.BOTTOM_LEFT_BOTTOM_LEFT + " "
			+ "or address.geohash like :" + Adjacent.BOTTOM_LEFT_LEFT + " "
			+ "or address.geohash like :" + Adjacent.LEFT_LEFT + " " + "or address.geohash like :"
			+ Adjacent.TOP_LEFT_LEFT + " " + "or address.geohash like :"
			+ Adjacent.TOP_LEFT_TOP_LEFT + " ");
		return sb.toString().trim();
	}

}
