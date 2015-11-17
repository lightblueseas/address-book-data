package de.alpharogroup.address.book.service.domain.api;

import java.util.List;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.service.domain.DomainService;

public interface FederalstateService extends DomainService<Integer, Federalstate>
{

	/**
	 * Find federal state from iso3166 a2code.
	 * 
	 * @param iso3166A2code
	 *            the iso3166 a2code
	 * @return the federal states
	 */
	Federalstate findFederalstateFromIso3166A2code(final String iso3166A2code);

	/**
	 * Find federalstate name from iso3166 a2code.
	 * 
	 * @param iso3166A2code
	 *            the iso3166 a2code
	 * @return the string
	 */
	String findFederalstateNameFromIso3166A2code(final String iso3166A2code);

	/**
	 * Find federalstates from country.
	 * 
	 * @param country
	 *            the country
	 * @return the list
	 */
	List<Federalstate> findFederalstatesFromCountry(final Country country);

	/**
	 * Find federal states from country.
	 * 
	 * @param country
	 *            the country
	 * @param name the name of the federal state
	 * @return the list of found federal states.
	 */
	List<Federalstate> findFederalstatesFromCountry(final Country country, String name);

	/**
	 * Find the first federal state from country.
	 * 
	 * @param country
	 *            the country
	 * @param name the name of the federal state
	 * @return the the first federal state or null if not found.
	 */
	Federalstate findFederalstate(final Country country, String name);

	/**
	 * Gets the Federalstates from the given String object. Example for the
	 * format for the given String is(without the double quotes): "gr.grc=&gt;gr.a"
	 * or "de.deu=&gt;de.bw"
	 *
	 * @param string a concat string from country and an optional state string code.
	 * @return the federalstate
	 */
	Federalstate getFederalstate(final String string);
}
