package de.alpharogroup.address.book.service.api;

import java.util.List;

import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.db.service.jpa.BusinessService;

public interface FederalstatesService extends BusinessService<Federalstates, Integer>{

	/**
	 * Find federal state from iso3166 a2code.
	 * 
	 * @param iso3166A2code
	 *            the iso3166 a2code
	 * @return the federal states
	 */
	Federalstates findFederalstateFromIso3166A2code(final String iso3166A2code);

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
	List<Federalstates> findFederalstatesFromCountry(final Countries country);

	/**
	 * Find federal states from country.
	 * 
	 * @param country
	 *            the country
	 * @param name the name of the federal state
	 * @return the list of found federal states.
	 */
	List<Federalstates> findFederalstatesFromCountry(final Countries country, String name);

	/**
	 * Find the first federal state from country.
	 * 
	 * @param country
	 *            the country
	 * @param name the name of the federal state
	 * @return the the first federal state or null if not found.
	 */
	Federalstates findFederalstate(final Countries country, String name);

	/**
	 * Gets the Federalstates from the given String object. Example for the
	 * format for the given String is(without the double quotes): "gr.grc=&gt;gr.a"
	 * or "de.deu=&gt;de.bw"
	 *
	 * @param string a concat string from country and an optional state string code.
	 * @return the federalstate
	 */
	Federalstates getFederalstate(final String string);
}