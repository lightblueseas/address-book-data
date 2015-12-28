package de.alpharogroup.address.book.service.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.service.api.AddressService;

@ContextConfiguration(locations = "classpath:test-em-applicationContext.xml")
public class AddressesBusinessMapperServiceTest extends AbstractTestNGSpringContextTests
{
	@Autowired
	private AddressService addressService;

	@Test(enabled = false)
	public void testRead()
	{
		final Address address = addressService.read(1);
		System.out.println(address);
	}

	@Test(enabled = false)
	public void testCreate()
	{
		Address address = Address.builder()
			.addressComment("")
			.geohash("u34er25463")
			.street("Hamburger Chaussee")
			.build();
		 address = addressService.create(address);
		System.out.println(address);
	}

}
