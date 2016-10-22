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

/**
 * The class {@link FederalstatesBusinessService}.
 */
@Transactional
@Service("federalstatesService")
public class FederalstatesBusinessService extends AbstractBusinessService<Federalstates, Integer, FederalstatesDao> implements FederalstatesService {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Sets the specific {@link FederalstatesDao}.
	 *
	 * @param federalstatesDao the new {@link FederalstatesDao}.
	 */
	@Autowired
	public void setFederalstatesDao(final FederalstatesDao federalstatesDao) {
		setDao(federalstatesDao);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Federalstates findFederalstateFromIso3166A2code(
			final String iso3166A2code) {
		final String hqlString = "select fs from Federalstates fs where fs.iso3166A2code=:iso3166A2code";
		final Query query = getQuery(hqlString);
		query.setParameter("iso3166A2code", iso3166A2code);
		final List<Federalstates> federalstates = query.getResultList();
		return ListExtensions.getFirst(federalstates);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String findFederalstateNameFromIso3166A2code(
			final String iso3166A2code) {
		final String hqlString = "select fs.name from Federalstates fs where fs.iso3166A2code=:iso3166A2code";
		final Query query = getQuery(hqlString);
		query.setParameter("iso3166A2code", iso3166A2code);
		@SuppressWarnings("unchecked")
		final
		List<String> names = query.getResultList();
		return ListExtensions.getFirst(names);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Federalstates> findFederalstatesFromCountry(
			final Countries country) {
		final TypedQuery<Federalstates> typedQuery = getDao()
			.getEntityManager()
			.createNamedQuery(Federalstates.FIND_FEDERALSTATES_FROM_COUNTRY, Federalstates.class)
			.setParameter("country", country);
		final List<Federalstates> federalstates = typedQuery.getResultList();
		return federalstates;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Federalstates> findFederalstatesFromCountry(final Countries country, final String name) {
		final TypedQuery<Federalstates> typedQuery = getDao()
			.getEntityManager()
			.createNamedQuery(Federalstates.FIND_FEDERALSTATE_FROM_COUNTRY_AND_NAME, Federalstates.class)
			.setParameter("country", country)
			.setParameter("name", name);
		final List<Federalstates> federalstates = typedQuery.getResultList();
		return federalstates;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstates findFederalstate(final Countries country, final String name) {
		return ListExtensions.getFirst(findFederalstatesFromCountry(country, name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstates getFederalstate(final String string) {
		final String seperatedString = "=>";
		final String[] splittedString = string.split(seperatedString);
		Federalstates federalstate;
		if (splittedString.length == 1) {
			final String coutryString = splittedString[0];
			final int dot = coutryString.indexOf('.');
			final String iso3166A3code = coutryString.substring(dot + 1,
					coutryString.length()).toUpperCase();
			// find if the country have federal states...
			federalstate = findFederalstateFromIso3166A2code(iso3166A3code);
		} else {
			final String federalStateString = splittedString[1];
			federalstate = findFederalstateFromIso3166A2code(federalStateString);
		}
		return federalstate;
	}

}