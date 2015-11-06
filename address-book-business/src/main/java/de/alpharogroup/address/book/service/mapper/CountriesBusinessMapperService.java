package de.alpharogroup.address.book.service.mapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.CountriesDao;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.mapper.CountriesMapper;
import de.alpharogroup.db.service.entitymapper.AbstractBusinessMapperService;


@Transactional
@Service("countriesMapperService")
public class CountriesBusinessMapperService extends
AbstractBusinessMapperService<Integer, Country, Countries, CountriesDao, CountriesMapper> implements CountryService
{

}
