package de.alpharogroup.address.book.rest;

import java.util.List;

import de.alpharogroup.address.book.application.model.LocationSearchModel;
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
	public List<KeyValuesPair<Country, Federalstate>> getCountriesToFederalstatesList() {
		return getDomainService().getCountriesToFederalstatesList();
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
	public List<KeyValuesPair<String, String>> getCountriesToFederalstatesAsStringList() {
		return getDomainService().getCountriesToFederalstatesAsStringList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<Country, Zipcode>> getCountriesToZipcodesList() {
		return getDomainService().getCountriesToZipcodesList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getCountriesToZipcodesAsStringList() {
		return getDomainService().getCountriesToZipcodesAsStringList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<Country, Zipcode>> getGermanCountriesToZipcodesList() {
		return getDomainService().getGermanCountriesToZipcodesList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAsStringList() {
		return getDomainService().getGermanCountriesToZipcodesAsStringList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getCountriesToZipcodesAndCitiesAsStringList() {
		return getDomainService().getCountriesToZipcodesAndCitiesAsStringList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<KeyValuesPair<String, String>> getGermanCountriesToZipcodesAndCitiesAsStringList() {
		return getDomainService().getGermanCountriesToZipcodesAndCitiesAsStringList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public LocationSearchModel<Address> setLocationSearchModel(LocationSearchModel<Address> modelObject) {
		return getDomainService().setLocationSearchModel(modelObject);
	}

}
