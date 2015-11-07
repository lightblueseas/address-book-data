package de.alpharogroup.address.book.service.mapper;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.AddressesDao;
import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.mapper.AddressesMapper;
import de.alpharogroup.address.book.service.mapper.api.AddressService;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;

@Transactional
@Service("addressesMapperService")
public class AddressesBusinessMapperService extends
		AbstractBusinessMapperService<Integer, Address, Addresses, AddressesDao, AddressesMapper> implements AddressService {
	
	@Autowired
	public void setAddressesDao(AddressesDao addressesDao) {
		setDao(addressesDao);
	}
	
	@Autowired
	public void setAddressesMapper(AddressesMapper addressesMapper) {
		setMapper(addressesMapper);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Address> find(String geohash)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.geohash like :geohash");
		final String hqlString = sb.toString();
		final Query query = getDao().getQuery(hqlString);
		query.setParameter("geohash", geohash + "%");
		List<Addresses> entities = query.getResultList();
		List<Address>addresses = getMapper().toBusinessObjects(entities);
		return addresses;
	}
}
