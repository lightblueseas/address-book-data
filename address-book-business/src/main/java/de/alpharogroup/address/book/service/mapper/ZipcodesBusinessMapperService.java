package de.alpharogroup.address.book.service.mapper;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.ZipcodesDao;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.mapper.ZipcodesMapper;
import de.alpharogroup.address.book.service.api.ZipcodesService;
import de.alpharogroup.address.book.service.mapper.api.ZipcodeService;
import de.alpharogroup.address.book.service.util.HqlStringCreator;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link ZipcodesBusinessMapperService}.
 */
@Transactional
@Service("zipcodesMapperService")
public class ZipcodesBusinessMapperService extends
	AbstractBusinessMapperService<Integer, Zipcode, Zipcodes, ZipcodesDao, ZipcodesMapper>
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
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Zipcode> find(String country, String zipcode, String city)
	{
		final String hqlString = HqlStringCreator.forZipcodes(country, zipcode, city);
		final Query query = getDao().getQuery(hqlString);
		if(country != null){
			query.setParameter("country", country);			
		}
		if(zipcode != null && !zipcode.isEmpty()){
			query.setParameter("zipcode", zipcode);			
		}
		if(city != null && !city.isEmpty()){
			query.setParameter("city", city);
			
		}

		final List<Zipcodes> entities = query.getResultList();
		final List<Zipcode> bos = getMapper().toBusinessObjects(entities);		
		return bos;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findAll(Country country, String zipcode, String city) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toBusinessObjects(zipcodesService.findAll(c, zipcode, city));
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
		return getMapper().toBusinessObjects(zipcodesService.findZipcodes(zipcode));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zipcode getZipcode(String zipcode, String city) {
		return getMapper().toBusinessObject(zipcodesService.getZipcode(zipcode, city));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> find(Country country) {
		return getMapper().toBusinessObjects(zipcodesService.find(getMapper().map(country, Countries.class)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zipcode findCityFromZipcode(Country country, String zipcode) {
		return getMapper().toBusinessObject(zipcodesService.findCityFromZipcode(getMapper().map(country, Countries.class), zipcode));
	}
}
