package de.alpharogroup.address.book.rest;

import java.util.List;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.rest.api.FederalstatesResource;
import de.alpharogroup.address.book.service.api.FederalstateService;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link FederalstatesRestResource} provides an implementation of the inteface {@link FederalstatesResource}.
 */
public class FederalstatesRestResource extends AbstractRestfulResource<Integer, Federalstate, FederalstateService>
	implements FederalstatesResource
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate findFederalstateFromIso3166A2code(String iso3166a2code) {
		return getDomainService().findFederalstateFromIso3166A2code(iso3166a2code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String findFederalstateNameFromIso3166A2code(String iso3166a2code) {
		return getDomainService().findFederalstateNameFromIso3166A2code(iso3166a2code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Federalstate> findFederalstatesFromCountry(Country country) {
		return getDomainService().findFederalstatesFromCountry(country);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Federalstate> findFederalstatesFromCountry(final KeyValuePair<Country, String> countryWithName) {		
		return getDomainService().findFederalstatesFromCountry(countryWithName.getKey(), countryWithName.getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate findFederalstate(final KeyValuePair<Country, String> countryWithName) {
		return getDomainService().findFederalstate(countryWithName.getKey(), countryWithName.getValue());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate getFederalstate(String countrystatecode) {
		return getDomainService().getFederalstate(countrystatecode);
	}
}
