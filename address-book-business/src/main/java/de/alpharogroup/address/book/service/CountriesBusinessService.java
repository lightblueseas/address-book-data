package de.alpharogroup.address.book.service;

import java.util.ArrayList;
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

import de.alpharogroup.address.book.application.model.ValuesOfCountryName;
import de.alpharogroup.address.book.application.model.FederalStatesOfCountry;
import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.application.model.ZipcodesOfCountry;
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
import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link CountriesBusinessService}.
 */
@Transactional
@Service("countriesService")
public class CountriesBusinessService extends
		AbstractBusinessService<Countries, Integer, CountriesDao> implements
		CountriesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** The {@link FederalstatesService}. */
	@Autowired @Getter @Setter
	private FederalstatesService federalstatesService;
	/** The {@link ZipcodesService}. */
	@Autowired @Getter @Setter
	private ZipcodesService zipcodesService;
	/** The {@link AddressesService}. */
	@Autowired @Getter @Setter
	private AddressesService addressesService;	
	/** The countries to federalstates map. */
	@Deprecated
	private Map<Countries, List<Federalstates>> countriesToFederalstatesMap;
	/** The countries to federalstates list. */
	private List<FederalStatesOfCountry> countriesToFederalstatesList;
	/** The countries to federalstates as string map. */
	@Deprecated
	private Map<String, List<String>> countriesToFederalstatesAsStringMap;
	/** The countries to federalstates as string list. */
	private List<ValuesOfCountryName> countriesToFederalstatesAsStringList;
	/** The countries to zipcodes map. */
	@Deprecated
	private Map<Countries, List<Zipcodes>> countriesToZipcodesMap;	
	/** The countries to zipcodes list. */
	private List<ZipcodesOfCountry> countriesToZipcodesList;	
	/** The countries to zipcodes as string map. */
	private Map<String, List<String>> countriesToZipcodesAsStringMap;
	/** The countries to federalstates as string list. */
	private List<ValuesOfCountryName> countriesToZipcodesAsStringList;	
	/** The german speaking countries to zipcodes map. */
	private Map<Countries, List<Zipcodes>> germanCountriesToZipcodesMap;	
	/** The german countries to zipcodes as string map. */
	private Map<String, List<String>> germanCountriesToZipcodesAsStringMap;	
	/** The countries to zipcodes and cities as string map. */
	private Map<String, List<String>> countriesToZipcodesAndCitiesAsStringMap;	
	/** The german countries to zipcodes and cities as string map. */
	private Map<String, List<String>> germanCountriesToZipcodesAndCitiesAsStringMap;

	/**
	 * Sets the specific {@link CountriesDao}.
	 *
	 * @param countriesDao the new {@link CountriesDao}.
	 */
	@Autowired
	public void setCountriesDao(CountriesDao countriesDao) {
		setDao(countriesDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Map<Countries, List<Federalstates>> getCountriesToFederalstatesMap() {
		if(this.countriesToFederalstatesMap == null) {
			this.countriesToFederalstatesMap = new LinkedHashMap<>();
			List<Countries> countries = findAll();
			Collections.sort(countries, new Comparator<Countries>() {
				@Override
				public int compare(Countries o1, Countries o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (Countries country : countries) {
				List<Federalstates> federalstates = federalstatesService
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
	public List<FederalStatesOfCountry> getCountriesToFederalstatesList() {
		if(this.countriesToFederalstatesList == null){
			this.countriesToFederalstatesList = new ArrayList<>();
			List<Countries> countries = findAll();
			Collections.sort(countries, new Comparator<Countries>() {
				@Override
				public int compare(Countries o1, Countries o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (Countries country : countries) {
				this.countriesToFederalstatesList.add(FederalStatesOfCountry.builder()
						.country(country).federalstates(federalstatesService
						.findFederalstatesFromCountry(country)).build());
			}
		}
		return this.countriesToFederalstatesList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Map<String, List<String>> getCountriesToFederalstatesAsStringMap() {
		if(this.countriesToFederalstatesAsStringMap == null) {
			this.countriesToFederalstatesAsStringMap = new HashMap<String, List<String>>();
			Map<Countries, List<Federalstates>> countriesToFederalstatesMap = getCountriesToFederalstatesMap();

			for (Entry<Countries, List<Federalstates>> entry : countriesToFederalstatesMap
					.entrySet()) {
				Countries country = entry.getKey();
				List<Federalstates> federalstates = entry.getValue();
				List<String> fd = new ArrayList<String>();
				for (Federalstates federalstate : federalstates) {
					fd.add(federalstate.getIso3166A2code());
				}
				this.countriesToFederalstatesAsStringMap.put(country.getName(), fd);
			}			
		}
		return this.countriesToFederalstatesAsStringMap;
	}	


	@Override
	public List<ValuesOfCountryName> getCountriesToFederalstatesAsStringList() {
		if(this.countriesToFederalstatesAsStringList == null) {
			this.countriesToFederalstatesAsStringList = new ArrayList<>();
			List<FederalStatesOfCountry> countriesToFederalstatesList = getCountriesToFederalstatesList();

			for (FederalStatesOfCountry entry : countriesToFederalstatesList) {
				Countries country = entry.getCountry();
				List<Federalstates> federalstates = entry.getFederalstates();
				List<String> fd = new ArrayList<String>();
				for (Federalstates federalstate : federalstates) {
					fd.add(federalstate.getIso3166A2code());
				}
				this.countriesToFederalstatesAsStringList.add(ValuesOfCountryName.builder().country(country.getName()).values(fd).build());
			}			
		}
		return this.countriesToFederalstatesAsStringList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Deprecated
	public Map<Countries, List<Zipcodes>> getCountriesToZipcodesMap() {
		if(this.countriesToZipcodesMap == null) {
			this.countriesToZipcodesMap = new LinkedHashMap<Countries, List<Zipcodes>>();
			List<Countries> countries = findAll();
			Collections.sort(countries, new Comparator<Countries>() {
				@Override
				public int compare(Countries o1, Countries o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (Countries country : countries) {
				List<Zipcodes> zipcodes = zipcodesService.find(country);
				this.countriesToZipcodesMap.put(country, zipcodes);
			}
		}
		return this.countriesToZipcodesMap;
	}

	@Override
	public List<ZipcodesOfCountry> getCountriesToZipcodesList() {
		if(this.countriesToZipcodesList == null) {
			this.countriesToZipcodesList = new ArrayList<>();
			List<Countries> countries = findAll();
			Collections.sort(countries, new Comparator<Countries>() {
				@Override
				public int compare(Countries o1, Countries o2) {
					return o1.getName().compareTo(o2.getName());
				}
			});
			for (Countries country : countries) {
				List<Zipcodes> zipcodes = zipcodesService.find(country);
				this.countriesToZipcodesList.add(
						ZipcodesOfCountry.builder()
						.country(country)
						.zipcodes(zipcodes).build());
			}
		}
		return this.countriesToZipcodesList;
	}	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getCountriesToZipcodesAsStringMap() {
		if(this.countriesToZipcodesAsStringMap == null) {
			this.countriesToZipcodesAsStringMap = new HashMap<String, List<String>>();
			Map<Countries, List<Zipcodes>> countriesToZipcodeMap = getCountriesToZipcodesMap();
			for (Entry<Countries, List<Zipcodes>> entry : countriesToZipcodeMap
					.entrySet()) {
				Countries country = entry.getKey();
				List<Zipcodes> zipcodes = entry.getValue();
				List<String> zc = new ArrayList<String>();
				for (Zipcodes zipcode : zipcodes) {
					zc.add(zipcode.getZipcode());
				}
				this.countriesToZipcodesAsStringMap.put(country.getName(), zc);
			}			
		}
		return this.countriesToZipcodesAsStringMap;
	}

	@Override
	public List<ValuesOfCountryName> getCountriesToZipcodesAsStringList() {
		if(this.countriesToZipcodesAsStringList == null) {
			this.countriesToZipcodesAsStringList = new ArrayList<>();
			List<ZipcodesOfCountry> countriesToZipcodeList = getCountriesToZipcodesList();
			
			for (ZipcodesOfCountry entry : countriesToZipcodeList) {
				Countries country = entry.getCountry();
				List<Zipcodes> zipcodes = entry.getZipcodes();
				List<String> zc = new ArrayList<>();
				for (Zipcodes zipcode : zipcodes) {
					zc.add(zipcode.getZipcode());
				}
				this.countriesToZipcodesAsStringList.add(
						ValuesOfCountryName.builder()
						.country(country.getName())
						.values(zc).build());
			}			
		}
		return this.countriesToZipcodesAsStringList;
	}
	
	/**
	 * {@inheritDoc}
	 */	
	@Override
	public Map<Countries, List<Zipcodes>> getGermanCountriesToZipcodesMap() {
		if(this.germanCountriesToZipcodesMap == null) {
			this.germanCountriesToZipcodesMap = new LinkedHashMap<>();
			List<Countries> countries = new ArrayList<Countries>();
			countries.add(find("DE"));
			countries.add(find("AT"));
			countries.add(find("CH"));
			Collections.sort(countries, new Comparator<Countries>() {
				@Override
				public int compare(Countries object, Countries compareWithObject) {
					// Check if one of the objects are null
					if ((object != null) && (compareWithObject == null)) {
						return 1;// compareWithObject is null so its bigger
					}  
					if ((object == null) && (compareWithObject != null)) {
						return -1; // object is null so its smaller
					}  
					if (object == compareWithObject) {
						return 0;// it is the same Object
					}
					// Null check completed so we can compare the objects 
					return object.getName().compareTo(compareWithObject.getName());
				}
			});
			for (Countries country : countries) {
				List<Zipcodes> zipcodes = zipcodesService.find(country);
				this.germanCountriesToZipcodesMap.put(country, zipcodes);
			}
		}		
		return this.germanCountriesToZipcodesMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getGermanCountriesToZipcodesAsStringMap() {
		if(this.germanCountriesToZipcodesAsStringMap == null) {
			this.germanCountriesToZipcodesAsStringMap = new HashMap<String, List<String>>();
			Map<Countries, List<Zipcodes>> countriesToZipcodeMap = getGermanCountriesToZipcodesMap();
			for (Entry<Countries, List<Zipcodes>> entry : countriesToZipcodeMap
					.entrySet()) {
				Countries country = entry.getKey();
				List<Zipcodes> zipcodes = entry.getValue();
				List<String> zc = new ArrayList<String>();
				for (Zipcodes zipcode : zipcodes) {
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
	public Map<String, List<String>> getCountriesToZipcodesAndCitiesAsStringMap() {
		if(this.countriesToZipcodesAndCitiesAsStringMap == null) {
			this.countriesToZipcodesAndCitiesAsStringMap = new HashMap<String, List<String>>();
			Map<Countries, List<Zipcodes>> countriesToZipcodeMap = getCountriesToZipcodesMap();
			for (Entry<Countries, List<Zipcodes>> entry : countriesToZipcodeMap
					.entrySet()) {
				Countries country = entry.getKey();
				List<Zipcodes> zipcodes = entry.getValue();
				List<String> zc = new ArrayList<String>();
				for (Zipcodes zipcode : zipcodes) {
					zc.add(zipcode.getZipcode()+ " "+zipcode.getCity());
					zc.add(zipcode.getCity()+ " "+zipcode.getZipcode());
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
	public Map<String, List<String>> getGermanCountriesToZipcodesAndCitiesAsStringMap() {
		if(this.germanCountriesToZipcodesAndCitiesAsStringMap == null) {
			this.germanCountriesToZipcodesAndCitiesAsStringMap = new HashMap<String, List<String>>();
			Map<Countries, List<Zipcodes>> countriesToZipcodeMap = getGermanCountriesToZipcodesMap();
			for (Entry<Countries, List<Zipcodes>> entry : countriesToZipcodeMap
					.entrySet()) {
				Countries country = entry.getKey();
				List<Zipcodes> zipcodes = entry.getValue();
				List<String> zc = new ArrayList<String>();
				for (Zipcodes zipcode : zipcodes) {
					zc.add(zipcode.getZipcode()+ " "+zipcode.getCity());
					zc.add(zipcode.getCity()+ " "+zipcode.getZipcode());
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
	@SuppressWarnings("unchecked")
	public List<Countries> findAll(String iso3166A2name, String iso3166A3name,
			String iso3166Number, String name) {
		final String hqlString = HqlStringCreator.forCountries(iso3166A2name,
				iso3166A3name, iso3166Number, name);
		final Query query = getQuery(hqlString);
		if (iso3166A2name != null && !iso3166A2name.isEmpty()) {
			query.setParameter("iso3166A2name", iso3166A2name);
		}
		if (iso3166A3name != null && !iso3166A3name.isEmpty()) {
			query.setParameter("iso3166A3name", iso3166A3name);
		}
		if (iso3166Number != null && !iso3166Number.isEmpty()) {
			query.setParameter("iso3166Number", iso3166Number);
		}
		if (name != null && !name.isEmpty()) {
			query.setParameter("name", name);
		}
		List<Countries> countries = query.getResultList();
		return countries;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Countries find(String iso3166A2name) {
		return ListExtensions.getFirst(findAll(iso3166A2name, null, null, null));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Countries findByName(String name) {
		return ListExtensions.getFirst(findAll(null, null, null, name));
	}	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String setLocationModel(LocationModel<Addresses> modelObject, String zc) {
		String errorKey = null;
		if(zc == null) {
			errorKey = "global.location.error.label";						
		} else {
			Countries country = findByName(modelObject.getSelectedCountryName());
			Zipcodes zipcode = getZipcodesService().findCityFromZipcode(country, zc);
			if(zipcode != null){
				List<Addresses> addresses = getAddressesService().find(zipcode);
				Addresses address = null;
				if(addresses != null && !addresses.isEmpty()) {
					address = addresses.get(0);
					modelObject.setAddress(address);
				} else {
					errorKey = "global.location.error.label";
				}
			} else {
				errorKey = "global.location.error.label";
			}
		}
		return errorKey;
	}

}