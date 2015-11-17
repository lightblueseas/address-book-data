package de.alpharogroup.address.book.service.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.daos.CountriesDao;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.mapper.CountriesMapper;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.mapper.api.CountryService;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link CountriesBusinessMapperService}.
 */
@Transactional
@Service("countriesMapperService")
public class CountriesBusinessMapperService extends
AbstractBusinessMapperService<Integer, Country, Countries, CountriesDao, CountriesMapper> implements CountryService
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
			for(Entry<Countries, List<Federalstates>> entry : countriesToFederalstatesMap.entrySet()) {
				this.countryToFederalstateMap.put(getMapper().toBusinessObject(entry.getKey()), getMapper().map(entry.getValue(), Federalstate.class));
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
				countryToZipcodeMap.put(getMapper().toBusinessObject(entry.getKey()), getMapper().map(entry.getValue(), Zipcode.class));
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
				this.germanCountryToZipcodeMap.put(getMapper().toBusinessObject(entry.getKey()), getMapper().map(entry.getValue(), Zipcode.class));				
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
		return getMapper().toBusinessObjects(countriesService.findAll(iso3166a2name, iso3166a3name, iso3166Number, name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Country find(String iso3166a2name) {
		return getMapper().toBusinessObject(countriesService.find(iso3166a2name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Country findByName(String name) {
		return getMapper().toBusinessObject(countriesService.findByName(name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String setLocationModel(LocationModel modelObject, String zc) {
		return countriesService.setLocationModel(modelObject, zc);
	}
}
