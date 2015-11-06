package de.alpharogroup.address.book.rest;

import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.address.book.rest.api.FederalstatesResource;
import de.alpharogroup.address.book.service.mapper.FederalstateService;
import de.alpharogroup.db.service.rs.AbstractRestfulResource;

public class FederalstatesRestResource extends AbstractRestfulResource<Integer, Federalstate, FederalstateService>
	implements FederalstatesResource
{
}
