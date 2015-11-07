package de.alpharogroup.address.book.service.mapper.api;

import java.util.List;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.db.service.entitymapper.BusinessMapperService;

public interface AddressService extends BusinessMapperService<Integer, Address>{
	
	List<Address> find(String geohash);
}
