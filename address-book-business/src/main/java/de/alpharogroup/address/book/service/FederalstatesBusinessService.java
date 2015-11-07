package de.alpharogroup.address.book.service;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.FederalstatesDao;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.collections.ListExtensions;
import de.alpharogroup.db.service.jpa.AbstractBusinessService;


@Transactional
@Service("federalstatesService")
public class FederalstatesBusinessService extends AbstractBusinessService<Federalstates, Integer, FederalstatesDao> implements FederalstatesService {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	public void setFederalstatesDao(FederalstatesDao federalstatesDao) {
		setDao(federalstatesDao);
	}
	

	/**
	 * {@inheritDoc}
	 */
	public Federalstates findFederalstateFromIso3166A2code(
			final String iso3166A2code) {
		final String hqlString = "select fs from Federalstates fs where fs.iso3166A2code=:iso3166A2code";
		final Query query = getQuery(hqlString);
		query.setParameter("iso3166A2code", iso3166A2code);
		@SuppressWarnings("unchecked")
		List<Federalstates> federalstates = query.getResultList();
		if(federalstates != null && !federalstates.isEmpty()){
			federalstates.get(0);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String findFederalstateNameFromIso3166A2code(
			final String iso3166A2code) {
		final String hqlString = "select fs.name from Federalstates fs where fs.iso3166A2code=:iso3166A2code";
		final Query query = getQuery(hqlString);
		query.setParameter("iso3166A2code", iso3166A2code);
		@SuppressWarnings("unchecked")
		List<String> names = query.getResultList();
		if(names != null && !names.isEmpty()){
			names.get(0);
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Federalstates> findFederalstatesFromCountry(
			final Countries country) {
		final TypedQuery<Federalstates> typedQuery = getDao()
			.getEntityManager()
			.createNamedQuery(Federalstates.FIND_FEDERALSTATES_FROM_COUNTRY, Federalstates.class)
			.setParameter("country", country);
		List<Federalstates> federalstates = typedQuery.getResultList();
		return federalstates;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Federalstates> findFederalstatesFromCountry(final Countries country, String name) {
		final TypedQuery<Federalstates> typedQuery = getDao()
			.getEntityManager()
			.createNamedQuery(Federalstates.FIND_FEDERALSTATE_FROM_COUNTRY_AND_NAME, Federalstates.class)
			.setParameter("country", country)
			.setParameter("name", name);
		List<Federalstates> federalstates = typedQuery.getResultList();
		return federalstates;
	}

	/**
	 * {@inheritDoc}
	 */
	public Federalstates findFederalstate(final Countries country, String name) {
		return ListExtensions.getFirst(findFederalstatesFromCountry(country, name));
	}

	/**
	 * {@inheritDoc}
	 */
	public Federalstates getFederalstate(final String string) {
		String seperatedString = "=>";
		String[] splittedString = string.split(seperatedString);
		Federalstates federalstate;
		if (splittedString.length == 1) {
			String coutryString = splittedString[0];
			int dot = coutryString.indexOf('.');
			String iso3166A3code = coutryString.substring(dot + 1,
					coutryString.length()).toUpperCase();
			// find if the country have federal states...
			federalstate = findFederalstateFromIso3166A2code(iso3166A3code);
		} else {
			String federalStateString = splittedString[1];
			federalstate = findFederalstateFromIso3166A2code(federalStateString);
		}
		return federalstate;
	}

}