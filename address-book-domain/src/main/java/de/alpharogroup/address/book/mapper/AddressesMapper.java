package de.alpharogroup.address.book.mapper;

import org.springframework.stereotype.Component;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.db.entitymapper.AbstractEntityBOMapper;

@Component
public class AddressesMapper extends AbstractEntityBOMapper<Addresses, Address> {
}
