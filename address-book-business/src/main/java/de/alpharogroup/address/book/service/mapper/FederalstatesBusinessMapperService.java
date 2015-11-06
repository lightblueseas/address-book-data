package de.alpharogroup.address.book.service.mapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.FederalstatesDao;
import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.entities.Federalstates;
import de.alpharogroup.address.book.mapper.FederalstatesMapper;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;

@Transactional
@Service("federalstatesMapperService")
public class FederalstatesBusinessMapperService extends
	AbstractBusinessMapperService<Integer, Federalstate, Federalstates, FederalstatesDao, FederalstatesMapper>
	implements FederalstateService
{

}
