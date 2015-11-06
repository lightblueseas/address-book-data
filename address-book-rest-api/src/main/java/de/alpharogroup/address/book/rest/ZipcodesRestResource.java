package de.alpharogroup.address.book.rest;

import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.address.book.rest.api.ZipcodesResource;
import de.alpharogroup.address.book.service.mapper.ZipcodeService;
import de.alpharogroup.db.service.rs.AbstractRestfulResource;

public class ZipcodesRestResource extends AbstractRestfulResource<Integer, Zipcode, ZipcodeService>
	implements ZipcodesResource
{
}
