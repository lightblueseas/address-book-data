package de.alpharogroup.address.book.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.AddressesDao;
import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.mapper.AddressesMapper;
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
}
