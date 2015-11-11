package de.alpharogroup.address.book.rest;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.address.book.rest.api.CountriesResource;
import de.alpharogroup.address.book.service.mapper.api.CountryService;
import de.alpharogroup.service.rs.AbstractRestfulResource;

public class CountriesRestResource extends AbstractRestfulResource<Integer, Country, CountryService>
	implements CountriesResource
{
}
