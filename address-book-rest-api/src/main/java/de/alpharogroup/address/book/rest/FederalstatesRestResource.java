package de.alpharogroup.address.book.rest;

import java.util.List;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.rest.api.FederalstatesResource;
import de.alpharogroup.address.book.service.api.FederalstateService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

public class FederalstatesRestResource extends AbstractRestfulResource<Integer, Federalstate, FederalstateService>
	implements FederalstatesResource
{

	@Override
	public Federalstate findFederalstateFromIso3166A2code(String iso3166a2code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findFederalstateNameFromIso3166A2code(String iso3166a2code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Federalstate> findFederalstatesFromCountry(Country country) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Federalstate> findFederalstatesFromCountry(Country country, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Federalstate findFederalstate(Country country, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Federalstate getFederalstate(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
