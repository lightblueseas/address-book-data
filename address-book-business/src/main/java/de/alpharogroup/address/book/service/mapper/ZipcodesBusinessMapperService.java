package de.alpharogroup.address.book.service.mapper;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.ZipcodesDao;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.mapper.ZipcodesMapper;
import de.alpharogroup.address.book.service.mapper.api.ZipcodeService;
import de.alpharogroup.address.book.service.util.HqlStringCreator;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;

@Transactional
@Service("zipcodesMapperService")
public class ZipcodesBusinessMapperService extends
	AbstractBusinessMapperService<Integer, Zipcode, Zipcodes, ZipcodesDao, ZipcodesMapper>
	implements ZipcodeService
{

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
}
