package de.alpharogroup.address.book.service.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.FederalstatesDao;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.mapper.FederalstatesMapper;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.address.book.service.mapper.api.FederalstateService;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link FederalstatesBusinessMapperService}.
 */
@Transactional
@Service("federalstatesMapperService")
public class FederalstatesBusinessMapperService extends
		AbstractBusinessMapperService<Integer, Federalstate, Federalstates, FederalstatesDao, FederalstatesMapper>
		implements FederalstateService {
	
	/** The {@link FederalstatesService}. */
	@Autowired @Getter @Setter
	private FederalstatesService federalstatesService;

	/**
	 * Sets the specific {@link FederalstatesDao}.
	 *
	 * @param federalstatesDao
	 *            the new {@link FederalstatesDao}.
	 */
	@Autowired
	public void setFederalstatesDao(final FederalstatesDao federalstatesDao) {
		setDao(federalstatesDao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate findFederalstateFromIso3166A2code(final String iso3166a2code) {
		return getMapper().toBusinessObject(federalstatesService.findFederalstateFromIso3166A2code(iso3166a2code));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String findFederalstateNameFromIso3166A2code(String iso3166a2code) {
		return federalstatesService.findFederalstateNameFromIso3166A2code(iso3166a2code);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Federalstate> findFederalstatesFromCountry(Country country) {
		Countries c = getMapper().getMapper().map(country, Countries.class);
		return getMapper().toBusinessObjects(federalstatesService.findFederalstatesFromCountry(c));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Federalstate> findFederalstatesFromCountry(Country country, String name) {
		Countries c = getMapper().getMapper().map(country, Countries.class);
		return getMapper().toBusinessObjects(federalstatesService.findFederalstatesFromCountry(c, name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate findFederalstate(Country country, String name) {
		return getMapper().toBusinessObject(
				federalstatesService.findFederalstate(getMapper().getMapper().map(country, Countries.class), name));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Federalstate getFederalstate(String string) {
		return getMapper().toBusinessObject(federalstatesService.getFederalstate(string));
	}

}