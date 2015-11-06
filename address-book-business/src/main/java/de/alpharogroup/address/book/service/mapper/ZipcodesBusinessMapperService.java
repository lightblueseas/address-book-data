package de.alpharogroup.address.book.service.mapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.ZipcodesDao;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.mapper.ZipcodesMapper;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;

@Transactional
@Service("zipcodesMapperService")
public class ZipcodesBusinessMapperService extends
	AbstractBusinessMapperService<Integer, Zipcode, Zipcodes, ZipcodesDao, ZipcodesMapper>
	implements ZipcodeService
{
}
