package de.alpharogroup.address.book.mapper;

import org.springframework.stereotype.Component;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.db.entitymapper.AbstractEntityDOMapper;

@Component
public class AddressesMapper extends AbstractEntityDOMapper<Addresses, Address> {
}
