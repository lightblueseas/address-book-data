package de.alpharogroup.address.book.rest;

import java.util.List;

import javax.ws.rs.core.Response;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.rest.api.ZipcodesResource;
import de.alpharogroup.address.book.service.api.ZipcodeService;
import de.alpharogroup.collections.pairs.KeyValuePair;
import de.alpharogroup.collections.pairs.Triple;
import de.alpharogroup.service.rs.AbstractRestfulResource;

/**
 * The class {@link ZipcodesRestResource} provides an implementation of the inteface {@link ZipcodesResource}.
 */
public class ZipcodesRestResource extends AbstractRestfulResource<Integer, Zipcode, ZipcodeService>
	implements ZipcodesResource
{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findAll(Triple<Country, String, String> searchCriteria) {
		return getDomainService().findAll(searchCriteria.getLeft(), searchCriteria.getMiddle(), searchCriteria.getRight());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Response existsZipcode(String zipcode) {
		return Response.ok(getDomainService().existsZipcode(zipcode)).build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> findZipcodes(String zipcode) {
		return getDomainService().findZipcodes(zipcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zipcode getZipcode(String zipcode, String city) {
		return getDomainService().getZipcode(zipcode, city);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Zipcode> find(final Country country) {
		return getDomainService().find(country);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Zipcode findCityFromZipcode(KeyValuePair<Country, String> countryWithZipcode) {
		return getDomainService().findCityFromZipcode(countryWithZipcode.getKey(), countryWithZipcode.getValue());
	}

}
