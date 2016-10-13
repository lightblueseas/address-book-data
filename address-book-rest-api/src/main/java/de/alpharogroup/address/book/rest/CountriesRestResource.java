package de.alpharogroup.address.book.rest;

import java.util.List;
import java.util.Map;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.rest.api.CountriesResource;
import de.alpharogroup.address.book.service.api.CountryService;
import de.alpharogroup.collections.pairs.KeyValuesPair;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link CountriesRestResource}.
 */
public class CountriesRestResource extends AbstractRestfulResource<Integer, Country, CountryService>
	implements CountriesResource
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Country, List<Federalstate>> getCountriesToFederalstatesMap() {
		return getDomainService().getCountriesToFederalstatesMap();
	}
	

	@Override
	public List<KeyValuesPair<Country, Federalstate>> getCountriesToFederalstatesList() {
		return getDomainService().getCountriesToFederalstatesList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getCountriesToFederalstatesAsStringMap() {
		return getDomainService().getCountriesToFederalstatesAsStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Country, List<Zipcode>> getCountriesToZipcodesMap() {
		return getDomainService().getCountriesToZipcodesMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getCountriesToZipcodesAsStringMap() {
		return getDomainService().getCountriesToZipcodesAsStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Country, List<Zipcode>> getGermanCountriesToZipcodesMap() {
		return getDomainService().getGermanCountriesToZipcodesMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getGermanCountriesToZipcodesAsStringMap() {
		return getDomainService().getGermanCountriesToZipcodesAsStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getCountriesToZipcodesAndCitiesAsStringMap() {
		return getDomainService().getCountriesToZipcodesAndCitiesAsStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<String>> getGermanCountriesToZipcodesAndCitiesAsStringMap() {
		return getDomainService().getGermanCountriesToZipcodesAndCitiesAsStringMap();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Country> findAll(String iso3166a2name, String iso3166a3name, String iso3166Number, String name) {
		return getDomainService().findAll(iso3166a2name, iso3166a3name, iso3166Number, name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Country find(String iso3166a2name) {
		return getDomainService().find(iso3166a2name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Country findByName(String name) {
		return getDomainService().findByName(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String setLocationModel(LocationModel<Address> modelObject, String zc) {
		return getDomainService().setLocationModel(modelObject, zc);
	}

}
