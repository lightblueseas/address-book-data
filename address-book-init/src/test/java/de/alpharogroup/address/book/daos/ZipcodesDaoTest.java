package de.alpharogroup.address.book.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.daos.ZipcodesDao;
import de.alpharogroup.address.book.entities.Zipcodes;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class ZipcodesDaoTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private ZipcodesDao zipcodesDao;

	@Test(enabled=false)
	public List<Zipcodes> testFindAll() {
		return zipcodesDao.findAll();
	}

}
