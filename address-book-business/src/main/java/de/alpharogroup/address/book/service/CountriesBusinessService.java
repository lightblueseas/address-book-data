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
package de.alpharogroup.address.book.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.application.model.AddressesSearchModel;
import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.daos.CountriesDao;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.address.book.service.api.ZipcodesService;
import de.alpharogroup.address.book.service.util.HqlStringCreator;
import de.alpharogroup.collections.list.ListExtensions;
import de.alpharogroup.collections.pairs.KeyValuesPair;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link CountriesBusinessService}.
 */
@Transactional
@Service("countriesService")
public class CountriesBusinessService
	extends
		AbstractBusinessService<Countries, Integer, CountriesDao>
	implements
		CountriesService
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The {@link FederalstatesService}. */
	@Autowired
	@Getter
	@Setter
	private FederalstatesService federalstatesService;
	/** The {@link ZipcodesService}. */
	@Autowired
	@Getter
	@Setter
	private ZipcodesService zipcodesService;
	/** The {@link AddressesService}. */
	@Autowired
	@Getter
	@Setter
	private AddressesService addressesService;
	/** The countries to federalstates map. */
	@Deprecated
	private Map<Countries, List<Federalstates>> countriesToFederalstatesMap;
	/** The countries to federalstates list. */
	private List<KeyValuesPair<Countries, Federalstates>> countriesToFederalstatesList;
	/** The countries to federalstates as string map. */
	@Deprecated
	private Map<String, List<String>> countriesToFederalstatesAsStringMap;
	/** The countries to federalstates as string list. */
	private List<KeyValuesPair<String, String>> countriesToFederalstatesAsStringList;
	/** The countries to zipcodes map. */
	@Deprecated
	private Map<Countries, List<Zipcodes>> countriesToZipcodesMap;
	/** The countries to zipcodes list. */
	private List<KeyValuesPair<Countries, Zipcodes>> countriesToZipcodesList;
	/** The countries to zipcodes as string map. */
	@Deprecated
	private Map<String, List<String>> countriesToZipcodesAsStringMap;
	/** The countries to federalstates as string list. */
	private List<KeyValuesPair<String, String>> countriesToZipcodesAsStringList;
	/** The german speaking countries to zipcodes map. */
	@Deprecated
	private Map<Countries, List<Zipcodes>> germanCountriesToZipcodesMap;
	/** The german speaking countries to zipcodes list. */
	private List<KeyValuesPair<Countries, Zipcodes>> germanCountriesToZipcodesList;
	/** The german countries to zipcodes as string map. */
	@Deprecated
	private Map<String, List<String>> germanCountriesToZipcodesAsStringMap;
	/** The german countries to zipcodes as string list. */
	private List<KeyValuesPair<String, String>> germanCountriesToZipcodesAsStringList;
	/** The countries to zipcodes and cities as string map. */
	@Deprecated
	private Map<String, List<String>> countriesToZipcodesAndCitiesAsStringMap;
	/** The countries to zipcodes and cities as string map. */
	private List<KeyValuesPair<String, String>> countriesToZipcodesAndCitiesAsStringList;
	/** The german countries to zipcodes and cities as string map. */
	@Deprecated
	private Map<String, List<String>> germanCountriesToZipcodesAndCitiesAsStringMap;
	/** The german countries to zipcodes and cities as string list. */
	private List<KeyValuesPair<String, String>> germanCountriesToZipcodesAndCitiesAsStringList;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Countries find(String iso3166A2name)
	{
		return ListExtensions.getFirst(findAll(iso3166A2name, null, null, null));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Countries> findAll(String iso3166A2name, String iso3166A3name, String iso3166Number,
		String name)
	{
		final String hqlString = HqlStringCreator.forCountries(iso3166A2name, iso3166A3name,
			iso3166Number, name);
		final Query query = getQuery(hqlString);
		if (iso3166A2name != null && !iso3166A2name.isEmpty())
		{
			query.setParameter("iso3166A2name", iso3166A2name);
		}
		if (iso3166A3name != null && !iso3166A3name.isEmpty())
		{
			query.setParameter("iso3166A3name", iso3166A3name);
		}
		if (iso3166Number != null && !iso3166Number.isEmpty())
		{
			query.setParameter("iso3166Number", iso3166Number);
		}
		if (name != null && !name.isEmpty())
		{
			query.setParameter("name", name);
		}
		final List<Countries> countries = query.getResultList();
		return countries;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Countries findByName(String name)
	{
		return ListExtensions.getFirst(findAll(null, null, null, name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getCountriesToFederalstatesAsStringList()
	{
		if (this.countriesToFederalstatesAsStringList == null)
		{
			this.countriesToFederalstatesAsStringList = new ArrayList<>();
			final List<KeyValuesPair<Countries, Federalstates>> countriesToFederalstatesList = getCountriesToFederalstatesList();

			for (final KeyValuesPair<Countries, Federalstates> entry : countriesToFederalstatesList)
			{
				final Countries country = entry.getKey();
				final Collection<Federalstates> federalstates = entry.getValues();
				final List<String> fd = new ArrayList<>();
				for (final Federalstates federalstate : federalstates)
				{
					fd.add(federalstate.getIso3166A2code());
				}

				this.countriesToFederalstatesAsStringList.add(KeyValuesPair
					.<String, String> builder().key(country.getName()).values(fd).build());
			}
		}
		return this.countriesToFederalstatesAsStringList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Map<String, List<String>> getCountriesToFederalstatesAsStringMap()
	{
		if (this.countriesToFederalstatesAsStringMap == null)
		{
			this.countriesToFederalstatesAsStringMap = new HashMap<>();
			final Map<Countries, List<Federalstates>> countriesToFederalstatesMap = getCountriesToFederalstatesMap();

			for (final Entry<Countries, List<Federalstates>> entry : countriesToFederalstatesMap
				.entrySet())
			{
				final Countries country = entry.getKey();
				final List<Federalstates> federalstates = entry.getValue();
				final List<String> fd = new ArrayList<>();
				for (final Federalstates federalstate : federalstates)
				{
					fd.add(federalstate.getIso3166A2code());
				}
				this.countriesToFederalstatesAsStringMap.put(country.getName(), fd);
			}
		}
		return this.countriesToFederalstatesAsStringMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<Countries, Federalstates>> getCountriesToFederalstatesList()
	{
		if (this.countriesToFederalstatesList == null)
		{
			this.countriesToFederalstatesList = new ArrayList<>();
			final List<Countries> countries = findAll();
			Collections.sort(countries, new Comparator<Countries>()
			{
				@Override
				public int compare(Countries o1, Countries o2)
				{
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (final Countries country : countries)
			{
				this.countriesToFederalstatesList.add(KeyValuesPair
					.<Countries, Federalstates> builder().key(country)
					.values(federalstatesService.findFederalstatesFromCountry(country)).build());
			}
		}
		return this.countriesToFederalstatesList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Map<Countries, List<Federalstates>> getCountriesToFederalstatesMap()
	{
		if (this.countriesToFederalstatesMap == null)
		{
			this.countriesToFederalstatesMap = new LinkedHashMap<>();
			final List<Countries> countries = findAll();
			Collections.sort(countries, new Comparator<Countries>()
			{
				@Override
				public int compare(Countries o1, Countries o2)
				{
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (final Countries country : countries)
			{
				final List<Federalstates> federalstates = federalstatesService
					.findFederalstatesFromCountry(country);
				this.countriesToFederalstatesMap.put(country, federalstates);
			}
		}
		return this.countriesToFederalstatesMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getCountriesToZipcodesAndCitiesAsStringList()
	{
		if (this.countriesToZipcodesAndCitiesAsStringList == null)
		{
			this.countriesToZipcodesAndCitiesAsStringList = new ArrayList<>();
			final List<KeyValuesPair<Countries, Zipcodes>> countriesToZipcodeMap = getCountriesToZipcodesList();
			for (final KeyValuesPair<Countries, Zipcodes> entry : countriesToZipcodeMap)
			{
				final Countries country = entry.getKey();
				final Collection<Zipcodes> zipcodes = entry.getValues();
				final List<String> zc = new ArrayList<>();
				for (final Zipcodes zipcode : zipcodes)
				{
					zc.add(zipcode.getZipcode() + " " + zipcode.getCity());
					zc.add(zipcode.getCity() + " " + zipcode.getZipcode());
				}
				this.countriesToZipcodesAndCitiesAsStringList.add(KeyValuesPair
					.<String, String> builder().key(country.getName()).values(zc).build());
			}
		}
		return this.countriesToZipcodesAndCitiesAsStringList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getCountriesToZipcodesAndCitiesAsStringMap()
	{
		if (this.countriesToZipcodesAndCitiesAsStringMap == null)
		{
			this.countriesToZipcodesAndCitiesAsStringMap = new HashMap<>();
			final Map<Countries, List<Zipcodes>> countriesToZipcodeMap = getCountriesToZipcodesMap();
			for (final Entry<Countries, List<Zipcodes>> entry : countriesToZipcodeMap.entrySet())
			{
				final Countries country = entry.getKey();
				final List<Zipcodes> zipcodes = entry.getValue();
				final List<String> zc = new ArrayList<>();
				for (final Zipcodes zipcode : zipcodes)
				{
					zc.add(zipcode.getZipcode() + " " + zipcode.getCity());
					zc.add(zipcode.getCity() + " " + zipcode.getZipcode());
				}
				this.countriesToZipcodesAndCitiesAsStringMap.put(country.getName(), zc);
			}
		}
		return this.countriesToZipcodesAndCitiesAsStringMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getCountriesToZipcodesAsStringList()
	{
		if (this.countriesToZipcodesAsStringList == null)
		{
			this.countriesToZipcodesAsStringList = new ArrayList<>();
			final List<KeyValuesPair<Countries, Zipcodes>> countriesToZipcodeList = getCountriesToZipcodesList();

			for (final KeyValuesPair<Countries, Zipcodes> entry : countriesToZipcodeList)
			{
				final Countries country = entry.getKey();
				final Collection<Zipcodes> zipcodes = entry.getValues();
				final List<String> zc = new ArrayList<>();
				for (final Zipcodes zipcode : zipcodes)
				{
					zc.add(zipcode.getZipcode());
				}
				;
				this.countriesToZipcodesAsStringList.add(KeyValuesPair.<String, String> builder()
					.key(country.getName()).values(zc).build());
			}
		}
		return this.countriesToZipcodesAsStringList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Map<String, List<String>> getCountriesToZipcodesAsStringMap()
	{
		if (this.countriesToZipcodesAsStringMap == null)
		{
			this.countriesToZipcodesAsStringMap = new HashMap<>();
			final Map<Countries, List<Zipcodes>> countriesToZipcodeMap = getCountriesToZipcodesMap();
			for (final Entry<Countries, List<Zipcodes>> entry : countriesToZipcodeMap.entrySet())
			{
				final Countries country = entry.getKey();
				final List<Zipcodes> zipcodes = entry.getValue();
				final List<String> zc = new ArrayList<>();
				for (final Zipcodes zipcode : zipcodes)
				{
					zc.add(zipcode.getZipcode());
				}
				this.countriesToZipcodesAsStringMap.put(country.getName(), zc);
			}
		}
		return this.countriesToZipcodesAsStringMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<Countries, Zipcodes>> getCountriesToZipcodesList()
	{
		if (this.countriesToZipcodesList == null)
		{
			this.countriesToZipcodesList = new ArrayList<>();
			final List<Countries> countries = findAll();
			Collections.sort(countries, new Comparator<Countries>()
			{
				@Override
				public int compare(Countries o1, Countries o2)
				{
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (final Countries country : countries)
			{
				final List<Zipcodes> zipcodes = zipcodesService.find(country);
				this.countriesToZipcodesList.add(KeyValuesPair.<Countries, Zipcodes> builder()
					.key(country).values(zipcodes).build());
			}
		}
		return this.countriesToZipcodesList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Map<Countries, List<Zipcodes>> getCountriesToZipcodesMap()
	{
		if (this.countriesToZipcodesMap == null)
		{
			this.countriesToZipcodesMap = new LinkedHashMap<>();
			final List<Countries> countries = findAll();
			Collections.sort(countries, new Comparator<Countries>()
			{
				@Override
				public int compare(Countries o1, Countries o2)
				{
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (final Countries country : countries)
			{
				final List<Zipcodes> zipcodes = zipcodesService.find(country);
				this.countriesToZipcodesMap.put(country, zipcodes);
			}
		}
		return this.countriesToZipcodesMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAndCitiesAsStringList()
	{
		if (this.germanCountriesToZipcodesAndCitiesAsStringList == null)
		{
			this.germanCountriesToZipcodesAndCitiesAsStringList = new ArrayList<>();
			final List<KeyValuesPair<Countries, Zipcodes>> countriesToZipcodeMap = getGermanCountriesToZipcodesList();
			for (final KeyValuesPair<Countries, Zipcodes> entry : countriesToZipcodeMap)
			{
				final Countries country = entry.getKey();
				final Collection<Zipcodes> zipcodes = entry.getValues();
				final List<String> zc = new ArrayList<>();
				for (final Zipcodes zipcode : zipcodes)
				{
					zc.add(zipcode.getZipcode() + " " + zipcode.getCity());
					zc.add(zipcode.getCity() + " " + zipcode.getZipcode());
				}
				this.germanCountriesToZipcodesAndCitiesAsStringList.add(KeyValuesPair
					.<String, String> builder().key(country.getName()).values(zc).build());
			}
		}
		return this.germanCountriesToZipcodesAndCitiesAsStringList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getGermanCountriesToZipcodesAndCitiesAsStringMap()
	{
		if (this.germanCountriesToZipcodesAndCitiesAsStringMap == null)
		{
			this.germanCountriesToZipcodesAndCitiesAsStringMap = new HashMap<>();
			final Map<Countries, List<Zipcodes>> countriesToZipcodeMap = getGermanCountriesToZipcodesMap();
			for (final Entry<Countries, List<Zipcodes>> entry : countriesToZipcodeMap.entrySet())
			{
				final Countries country = entry.getKey();
				final List<Zipcodes> zipcodes = entry.getValue();
				final List<String> zc = new ArrayList<>();
				for (final Zipcodes zipcode : zipcodes)
				{
					zc.add(zipcode.getZipcode() + " " + zipcode.getCity());
					zc.add(zipcode.getCity() + " " + zipcode.getZipcode());
				}
				this.germanCountriesToZipcodesAndCitiesAsStringMap.put(country.getName(), zc);
			}
		}
		return this.germanCountriesToZipcodesAndCitiesAsStringMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAsStringList()
	{
		if (this.germanCountriesToZipcodesAsStringList == null)
		{
			this.germanCountriesToZipcodesAsStringList = new ArrayList<>();
			final List<KeyValuesPair<Countries, Zipcodes>> countriesToZipcodeMap = getGermanCountriesToZipcodesList();
			for (final KeyValuesPair<Countries, Zipcodes> entry : countriesToZipcodeMap)
			{
				final Countries country = entry.getKey();
				final Collection<Zipcodes> zipcodes = entry.getValues();
				final List<String> zc = new ArrayList<>();
				for (final Zipcodes zipcode : zipcodes)
				{
					zc.add(zipcode.getZipcode());
				}
				this.germanCountriesToZipcodesAsStringList.add(KeyValuesPair
					.<String, String> builder().key(country.getName()).values(zc).build());
			}
		}
		return this.germanCountriesToZipcodesAsStringList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Map<String, List<String>> getGermanCountriesToZipcodesAsStringMap()
	{
		if (this.germanCountriesToZipcodesAsStringMap == null)
		{
			this.germanCountriesToZipcodesAsStringMap = new HashMap<>();
			final Map<Countries, List<Zipcodes>> countriesToZipcodeMap = getGermanCountriesToZipcodesMap();
			for (final Entry<Countries, List<Zipcodes>> entry : countriesToZipcodeMap.entrySet())
			{
				final Countries country = entry.getKey();
				final List<Zipcodes> zipcodes = entry.getValue();
				final List<String> zc = new ArrayList<>();
				for (final Zipcodes zipcode : zipcodes)
				{
					zc.add(zipcode.getZipcode());
				}
				this.germanCountriesToZipcodesAsStringMap.put(country.getName(), zc);
			}
		}
		return this.germanCountriesToZipcodesAsStringMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<Countries, Zipcodes>> getGermanCountriesToZipcodesList()
	{
		if (this.germanCountriesToZipcodesList == null)
		{
			this.germanCountriesToZipcodesList = new ArrayList<>();
			final List<Countries> countries = new ArrayList<>();
			countries.add(find("DE"));
			countries.add(find("AT"));
			countries.add(find("CH"));
			Collections.sort(countries, new Comparator<Countries>()
			{
				@Override
				public int compare(Countries object, Countries compareWithObject)
				{
					// Check if one of the objects are null
					if ((object != null) && (compareWithObject == null))
					{
						return 1;// compareWithObject is null so its bigger
					}
					if ((object == null) && (compareWithObject != null))
					{
						return -1; // object is null so its smaller
					}
					if (object == compareWithObject)
					{
						return 0;// it is the same Object
					}
					// Null check completed so we can compare the objects
					return object.getName().compareTo(compareWithObject.getName());
				}
			});
			for (final Countries country : countries)
			{
				final List<Zipcodes> zipcodes = zipcodesService.find(country);
				this.germanCountriesToZipcodesList.add(KeyValuesPair.<Countries, Zipcodes> builder()
					.key(country).values(zipcodes).build());
			}
		}
		return this.germanCountriesToZipcodesList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Map<Countries, List<Zipcodes>> getGermanCountriesToZipcodesMap()
	{
		if (this.germanCountriesToZipcodesMap == null)
		{
			this.germanCountriesToZipcodesMap = new LinkedHashMap<>();
			final List<Countries> countries = new ArrayList<>();
			countries.add(find("DE"));
			countries.add(find("AT"));
			countries.add(find("CH"));
			Collections.sort(countries, new Comparator<Countries>()
			{
				@Override
				public int compare(Countries object, Countries compareWithObject)
				{
					// Check if one of the objects are null
					if ((object != null) && (compareWithObject == null))
					{
						return 1;// compareWithObject is null so its bigger
					}
					if ((object == null) && (compareWithObject != null))
					{
						return -1; // object is null so its smaller
					}
					if (object == compareWithObject)
					{
						return 0;// it is the same Object
					}
					// Null check completed so we can compare the objects
					return object.getName().compareTo(compareWithObject.getName());
				}
			});
			for (final Countries country : countries)
			{
				final List<Zipcodes> zipcodes = zipcodesService.find(country);
				this.germanCountriesToZipcodesMap.put(country, zipcodes);
			}
		}
		return this.germanCountriesToZipcodesMap;
	}

	/**
	 * Sets the specific {@link CountriesDao}.
	 *
	 * @param countriesDao
	 *            the new {@link CountriesDao}.
	 */
	@Autowired
	public void setCountriesDao(CountriesDao countriesDao)
	{
		setDao(countriesDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@Deprecated
	@Override
	public String setLocationModel(LocationModel<Addresses> modelObject, String zc)
	{
		String errorKey = null;
		if (zc == null)
		{
			errorKey = "global.location.error.label";
		}
		else
		{
			final Countries country = findByName(modelObject.getSelectedCountryName());
			final Zipcodes zipcode = getZipcodesService().findCityFromZipcode(country, zc);
			if (zipcode != null)
			{
				final List<Addresses> addresses = getAddressesService().find(zipcode);
				final Addresses address = ListExtensions.getFirst(addresses);
				if (address != null)
				{
					modelObject.setAddress(address);
				}
				else
				{
					errorKey = "global.location.error.label";
				}
			}
			else
			{
				errorKey = "global.location.error.label";
			}
		}
		return errorKey;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AddressesSearchModel setLocationSearchModel(AddressesSearchModel modelObject)
	{
		String errorKey = null;
		if (modelObject.getZipcode() == null)
		{
			errorKey = "global.location.error.label";
		}
		else
		{
			final Countries country = findByName(modelObject.getLocation().getSelectedCountryName());
			final Zipcodes zipcode = getZipcodesService().findCityFromZipcode(country,
				modelObject.getZipcode());
			if (zipcode != null)
			{
				final List<Addresses> addresses = getAddressesService().find(zipcode);
				final Addresses address = ListExtensions.getFirst(addresses);
				if (address != null)
				{
					modelObject.getLocation().setAddress(address);
				}
				else
				{
					errorKey = "global.location.error.label";
				}
			}
			else
			{
				errorKey = "global.location.error.label";
			}
			modelObject.setErrorKey(errorKey);
		}
		return modelObject;
	}

}