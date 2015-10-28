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
import de.alpharogroup.db.service.jpa.AbstractBusinessService;


@Transactional
@Service("countriesService")
public class CountriesBusinessService extends
		AbstractBusinessService<Countries, Integer, CountriesDao> implements
		CountriesService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The Federal states business service. */
	@Autowired
	private FederalstatesService federalstatesService;
	/** The Zipcodes business service. */
	@Autowired
	private ZipcodesService zipcodesService;
	/** The Addresses business service. */
	@Autowired
	private AddressesService addressesService;

	public AddressesService getAddressesService() {
		return addressesService;
	}

	public void setAddressesService(AddressesService addressesService) {
		this.addressesService = addressesService;
	}

	public ZipcodesService getZipcodesService() {
		return zipcodesService;
	}

	public void setZipcodesService(ZipcodesService zipcodesService) {
		this.zipcodesService = zipcodesService;
	}

	public FederalstatesService getFederalstatesService() {
		return federalstatesService;
	}

	public void setFederalstatesService(
			FederalstatesService federalstatesService) {
		this.federalstatesService = federalstatesService;
	}

	@Autowired
	public void setCountriesDao(CountriesDao countriesDao) {
		setDao(countriesDao);
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<Countries, List<Federalstates>> getCountriesToFederalstatesMap() {
		Map<Countries, List<Federalstates>> countriesMap = new LinkedHashMap<Countries, List<Federalstates>>();
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
			countriesMap.put(country, federalstates);
		}
		return countriesMap;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<String>> getCountriesToFederalstatesAsStringMap() {
		final Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<Countries, List<Federalstates>> countriesToFederalstatesMap = getCountriesToFederalstatesMap();

		for (Entry<Countries, List<Federalstates>> entry : countriesToFederalstatesMap
				.entrySet()) {
			Countries country = entry.getKey();
			List<Federalstates> federalstates = entry.getValue();
			List<String> fd = new ArrayList<String>();
			for (Federalstates federalstate : federalstates) {
				fd.add(federalstate.getIso3166A2code());
			}
			map.put(country.getName(), fd);
		}
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<Countries, List<Zipcodes>> getCountriesToZipcodesMap() {
		Map<Countries, List<Zipcodes>> countriesToZipcodeMap = new LinkedHashMap<Countries, List<Zipcodes>>();
		List<Countries> countries = findAll();
		Collections.sort(countries, new Comparator<Countries>() {
			@Override
			public int compare(Countries o1, Countries o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		for (Countries country : countries) {
			List<Zipcodes> zipcodes = zipcodesService.find(country);
			countriesToZipcodeMap.put(country, zipcodes);
		}
		return countriesToZipcodeMap;
	}

	/**
	 * {@inheritDoc}
	 */	
	public Map<Countries, List<Zipcodes>> getGermanCountriesToZipcodesMap() {
		Map<Countries, List<Zipcodes>> countriesToZipcodeMap = new LinkedHashMap<Countries, List<Zipcodes>>();
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
			countriesToZipcodeMap.put(country, zipcodes);
		}
		return countriesToZipcodeMap;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<String>> getGermanCountriesToZipcodesAsStringMap() {
		final Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<Countries, List<Zipcodes>> countriesToZipcodeMap = getGermanCountriesToZipcodesMap();
		for (Entry<Countries, List<Zipcodes>> entry : countriesToZipcodeMap
				.entrySet()) {
			Countries country = entry.getKey();
			List<Zipcodes> zipcodes = entry.getValue();
			List<String> zc = new ArrayList<String>();
			for (Zipcodes zipcode : zipcodes) {
				zc.add(zipcode.getZipcode());
			}
			map.put(country.getName(), zc);
		}
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<String>> getCountriesToZipcodesAsStringMap() {
		final Map<String, List<String>> map = new HashMap<String, List<String>>();
		Map<Countries, List<Zipcodes>> countriesToZipcodeMap = getCountriesToZipcodesMap();
		for (Entry<Countries, List<Zipcodes>> entry : countriesToZipcodeMap
				.entrySet()) {
			Countries country = entry.getKey();
			List<Zipcodes> zipcodes = entry.getValue();
			List<String> zc = new ArrayList<String>();
			for (Zipcodes zipcode : zipcodes) {
				zc.add(zipcode.getZipcode());
			}
			map.put(country.getName(), zc);
		}
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<String>> getCountriesToZipcodesAndCitiesAsStringMap() {
		final Map<String, List<String>> map = new HashMap<String, List<String>>();
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
			map.put(country.getName(), zc);
		}
		return map;
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, List<String>> getGermanCountriesToZipcodesAndCitiesAsStringMap() {
		final Map<String, List<String>> map = new HashMap<String, List<String>>();
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
			map.put(country.getName(), zc);
		}
		return map;
	}
	
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
	
	public Countries find(String iso3166A2name) {
		List<Countries> countries = findAll(iso3166A2name, null, null, null);
		if(countries != null && !countries.isEmpty()){
			return countries.get(0);
		}
		return null;
	}
	
	public Countries findByName(String name) {
		List<Countries> countries = findAll(null, null, null, name);
		if(countries != null && !countries.isEmpty()){
			return countries.get(0);
		}
		return null;
	}
	

	public String setLocationModel(LocationModel modelObject, String zc) {
		String errorKey = null;
		if(zc == null) {
			errorKey = "global.location.error.label";						
		} else {
			Countries country = findByName(modelObject.getSelectedCountryName());
			Zipcodes zipcode = getZipcodesService().findCityFromZipcode(country, zc);
			if(zipcode != null){
				List<Addresses> addresses = getAddressesService().find(zipcode);
				Addresses address = null;
				if(addresses != null && !addresses.isEmpty()){
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