package de.alpharogroup.address.book.service.mapper.api;

import java.util.List;

import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.db.service.entitymapper.BusinessMapperService;

public interface ZipcodeService extends BusinessMapperService<Integer, Zipcode>
{
	List<Zipcode> find(String country, String zipcode, String city);
}
