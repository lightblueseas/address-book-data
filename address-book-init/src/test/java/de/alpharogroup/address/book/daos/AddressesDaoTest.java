package de.alpharogroup.address.book.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.daos.AddressesDao;
import de.alpharogroup.address.book.entities.Addresses;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class AddressesDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private AddressesDao addressesDao;

	@Test(enabled=false)
	public void testSaveAddresses() {
		List<Addresses> all = addressesDao.findAll();
		System.out.println(all.size());
	}

}
