package de.alpharogroup.address.book.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.alpharogroup.address.book.daos.AddressesDao;
import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.entities.Addresses;
import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import de.alpharogroup.address.book.mapper.AddressesMapper;
import de.alpharogroup.address.book.service.api.AddressService;
import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.service.domain.AbstractDomainService;
import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link AddressesDomainService}.
 */
@Transactional
@Service("addressesDomainService")
public class AddressesDomainService
		extends AbstractDomainService<Integer, Address, Addresses, AddressesDao, AddressesMapper>
		implements AddressService {

	/** The {@link AddressesService}. */
	@Autowired
	@Getter
	@Setter
	private AddressesService addressesService;

	/**
	 * Sets the specific {@link AddressesDao}.
	 *
	 * @param addressesDao
	 *            the new {@link AddressesDao}.
	 */
	@Autowired
	public void setAddressesDao(AddressesDao addressesDao) {
		setDao(addressesDao);
	}

	/**
	 * Sets the specific {@link AddressesMapper}.
	 *
	 * @param addressesMapper
	 *            the new {@link AddressesMapper}.
	 */
	@Autowired
	public void setAddressesMapper(AddressesMapper addressesMapper) {
		setMapper(addressesMapper);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Address> find(String geohash) {
		StringBuilder sb = new StringBuilder();
		sb.append("select a from Addresses a");
		sb.append(" ");
		sb.append("where a.geohash like :geohash");
		final String hqlString = sb.toString();
		final Query query = getDao().getQuery(hqlString);
		query.setParameter("geohash", geohash + "%");
		List<Addresses> entities = query.getResultList();
		List<Address> addresses = getMapper().toDomainObjects(entities);
		return addresses;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(String geohash, String latitude, String longitude) {
		return getMapper().toDomainObjects(addressesService.find(geohash, latitude, longitude));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findNeighbourhood(String geohash) {		
		return getMapper().toDomainObjects(addressesService.findNeighbourhood(geohash));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findFirstRingNeighbourhood(String geohash) {
		return getMapper().toDomainObjects(addressesService.findFirstRingNeighbourhood(geohash));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findFirstAndSecondRingNeighbourhood(String geohash) {
		return 
				getMapper().toDomainObjects(addressesService.findFirstAndSecondRingNeighbourhood(geohash));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(String latitude, String longitude) {
		return 
				getMapper().toDomainObjects(addressesService.find(latitude, longitude));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address contains(String latitude, String longitude) {		
		return getMapper().toDomainObject(addressesService.contains(latitude, longitude));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address contains(Zipcode zipcode) {
		Zipcodes z = getMapper().map(zipcode, Zipcodes.class);
		return getMapper().toDomainObject(addressesService.contains(z));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(Zipcode zipcode) {
		Zipcodes z = getMapper().map(zipcode, Zipcodes.class);
		return getMapper().toDomainObjects(addressesService.find(z));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findAllAddressesWithCountry(Country country) {
		Countries c = getMapper().map(country, Countries.class);
		List<Zipcodes> zc = addressesService.findAllAddressesWithCountry(c);		
		return getMapper().map(zc, Zipcode.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findAll(Country country) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(addressesService.findAll(c));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findGeohashIsNull() {
		return getMapper().toDomainObjects(addressesService.findGeohashIsNull());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(Country country, String zipcode) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(addressesService.find(c, zipcode));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> find(Country country, String zipcode, String city) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(addressesService.find(c, zipcode, city));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Address findFirst(Country country, String zipcode) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toDomainObject(addressesService.findFirst(c, zipcode));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findInvalidAddresses(Country country, String geohash) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(addressesService.findInvalidAddresses(c, geohash));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findInvalidAddresses(Country country, String geohash, boolean not) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(addressesService.findInvalidAddresses(c, geohash, not));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findAddressesWithSameCityname(Country country, String city) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(addressesService.findAddressesWithSameCityname(c, city));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Address> findAddressesWithSameZipcode(Country country, String zipcode) {
		Countries c = getMapper().map(country, Countries.class);
		return getMapper().toDomainObjects(addressesService.find(c, zipcode));
	}
}
