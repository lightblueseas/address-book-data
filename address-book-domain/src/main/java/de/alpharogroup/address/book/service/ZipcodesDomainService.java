package de.alpharogroup.address.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.ZipcodesDao;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.mapper.ZipcodesMapper;
import de.alpharogroup.address.book.service.api.ZipcodeService;
import de.alpharogroup.address.book.service.api.ZipcodesService;
import de.alpharogroup.service.domain.AbstractDomainService;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link ZipcodesDomainService}.
 */
@Transactional
@Service("zipcodesDomainService")
public class ZipcodesDomainService extends
AbstractDomainService<Integer, Zipcode, Zipcodes, ZipcodesDao, ZipcodesMapper>
	implements ZipcodeService
{
	
	/** The {@link ZipcodesService}. */
	@Autowired
	@Getter
	@Setter
	private ZipcodesService zipcodesService;

	/**
	 * Sets the specific {@link ZipcodesDao}.
	 *
	 * @param zipcodesDao
	 *            the new {@link ZipcodesDao}.
	 */
	@Autowired
	public void setZipcodesDao(ZipcodesDao zipcodesDao){
		setDao(zipcodesDao);
	}
	/**
	 * Sets the specific {@link ZipcodesMapper}.
	 *
	 * @param mapper
	 *            the new {@link ZipcodesMapper}.
	 */
	@Autowired
	public void setZipcodesMapper(ZipcodesMapper mapper) {
		setMapper(mapper);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findAll(Country country, String zipcode, String city) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(zipcodesService.findAll(c, zipcode, city));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAllZipcodes() {
		zipcodesService.deleteAllZipcodes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsZipcode(String zipcode) {
		return zipcodesService.existsZipcode(zipcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findZipcodes(String zipcode) {
		return getMapper().toDomainObjects(zipcodesService.findZipcodes(zipcode));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zipcode getZipcode(String zipcode, String city) {
		return getMapper().toDomainObject(zipcodesService.getZipcode(zipcode, city));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> find(Country country) {
		List<Zipcode> zcs = new ArrayList<>();
		if(country != null) {
			Countries countries = getMapper().map(country, Countries.class);
			return getMapper().toDomainObjects(zipcodesService.find(countries));			
		}
		return zcs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zipcode findCityFromZipcode(Country country, String zipcode) {
		return getMapper().toDomainObject(zipcodesService.findCityFromZipcode(getMapper().map(country, Countries.class), zipcode));
	}
}
