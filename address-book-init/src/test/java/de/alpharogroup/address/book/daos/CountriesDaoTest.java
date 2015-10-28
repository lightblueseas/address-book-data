package de.alpharogroup.address.book.daos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import de.alpharogroup.address.book.daos.CountriesDao;
import de.alpharogroup.address.book.entities.Countries;

@ContextConfiguration(locations = "classpath:test-applicationContext.xml")
public class CountriesDaoTest extends AbstractTestNGSpringContextTests {
	@Autowired
	private CountriesDao countriesDao;

	@Test(enabled=false)
	public List<Countries> testFindAll() {
		return countriesDao.findAll();
	}
}
