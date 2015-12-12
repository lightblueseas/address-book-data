package de.alpharogroup.address.book.mapper;

import org.springframework.stereotype.Component;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.db.entitymapper.AbstractEntityDOMapper;
@Component
public class CountriesMapper extends AbstractEntityDOMapper<Countries, Country> {

}
