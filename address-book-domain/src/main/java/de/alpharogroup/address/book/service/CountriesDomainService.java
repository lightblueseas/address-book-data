package de.alpharogroup.address.book.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.daos.CountriesDao;
import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.mapper.CountriesMapper;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.CountryService;
import de.alpharogroup.lang.ObjectExtensions;
import de.alpharogroup.service.domain.AbstractDomainService;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link CountriesDomainService}.
 */
@Transactional
@Service("countriesDomainService")
public class CountriesDomainService extends
AbstractDomainService<Integer, Country, Countries, CountriesDao, CountriesMapper> implements CountryService
{

	/** The {@link CountriesService}. */
	@Autowired
	@Getter
	@Setter
	private CountriesService countriesService;

	/** The country to zipcode map. */
	private Map<Country, List<Zipcode>> countryToZipcodeMap;
	
	/** The country to federalstate map. */
	private Map<Country, List<Federalstate>> countryToFederalstateMap;
	
	/** The german country to zipcode map. */
	private Map<Country, List<Zipcode>> germanCountryToZipcodeMap;
	
	/**
	 * Sets the specific {@link CountriesDao}.
	 *
	 * @param countriesDao
	 *            the new {@link CountriesDao}.
	 */
	@Autowired
	public void setCountriesDao(CountriesDao countriesDao){
		setDao(countriesDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Country, List<Federalstate>> getCountriesToFederalstatesMap() {
		if(this.countryToFederalstateMap == null) {
			this.countryToFederalstateMap = new LinkedHashMap<>();
			final Map<Countries, List<Federalstates>> countriesToFederalstatesMap = countriesService.getCountriesToFederalstatesMap();
			final CountriesMapper mapper = getMapper();
			for(Entry<Countries, List<Federalstates>> entry : countriesToFederalstatesMap.entrySet()) {
				Countries countries = entry.getKey();
				List<Federalstates> fss = entry.getValue();
				if(countries !=null) {
					Country country = mapper.toDomainObject(countries);
					List<Federalstate> federalstates = mapper.map(fss, Federalstate.class);
					this.countryToFederalstateMap.put(country, federalstates);
				} else {
					System.err.println(fss);
				}				
			}			
		}
		return this.countryToFederalstateMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getCountriesToFederalstatesAsStringMap() {
		return countriesService.getCountriesToFederalstatesAsStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Country, List<Zipcode>> getCountriesToZipcodesMap() {
		if(this.countryToZipcodeMap == null) {
			this.countryToZipcodeMap = new LinkedHashMap<>();
			final Map<Countries, List<Zipcodes>> countriesToZipcodesMap = countriesService.getCountriesToZipcodesMap();
			for (Entry<Countries, List<Zipcodes>> entry : countriesToZipcodesMap.entrySet()) {
				countryToZipcodeMap.put(getMapper().toDomainObject(entry.getKey()), getMapper().map(entry.getValue(), Zipcode.class));
			}
		}
		return this.countryToZipcodeMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getCountriesToZipcodesAsStringMap() {
		return countriesService.getCountriesToZipcodesAsStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Country, List<Zipcode>> getGermanCountriesToZipcodesMap() {
		if(this.germanCountryToZipcodeMap == null) {
			this.germanCountryToZipcodeMap = new LinkedHashMap<>();
			Map<Countries, List<Zipcodes>> germanCountriesToZipcodesMap = countriesService.getGermanCountriesToZipcodesMap();
			for (Entry<Countries, List<Zipcodes>> entry : germanCountriesToZipcodesMap.entrySet()) {
				this.germanCountryToZipcodeMap.put(getMapper().toDomainObject(entry.getKey()), getMapper().map(entry.getValue(), Zipcode.class));				
			}
		}
		return this.germanCountryToZipcodeMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getGermanCountriesToZipcodesAsStringMap() {
		return countriesService.getGermanCountriesToZipcodesAsStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getCountriesToZipcodesAndCitiesAsStringMap() {
		return countriesService.getCountriesToZipcodesAndCitiesAsStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getGermanCountriesToZipcodesAndCitiesAsStringMap() {
		return countriesService.getGermanCountriesToZipcodesAndCitiesAsStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Country> findAll(String iso3166a2name, String iso3166a3name, String iso3166Number, String name) {
		return getMapper().toDomainObjects(countriesService.findAll(iso3166a2name, iso3166a3name, iso3166Number, name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Country find(String iso3166a2name) {
		return getMapper().toDomainObject(countriesService.find(iso3166a2name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Country findByName(String name) {
		return getMapper().toDomainObject(countriesService.findByName(name));
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String setLocationModel(LocationModel<Address> modelObject, String zc) {
		LocationModel<Addresses> locationModel = getMapper().map(modelObject, LocationModel.class);
		String result = countriesService.setLocationModel(locationModel, zc);
		ObjectExtensions.copyQuietly(modelObject, locationModel);
		return result;
	}
}
