package de.alpharogroup.address.book.rest;

import java.util.List;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.rest.api.AddressesResource;
import de.alpharogroup.address.book.service.domain.api.AddressService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

public class AddressesRestResource extends AbstractRestfulResource<Integer, Address, AddressService>
	implements AddressesResource
{
	
	public List<Address> find(String geohash) {
		List<Address> addresses = getDomainService().find(geohash);
		return addresses;		
	}
}
