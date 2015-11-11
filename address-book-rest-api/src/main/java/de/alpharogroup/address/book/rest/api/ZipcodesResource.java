package de.alpharogroup.address.book.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.alpharogroup.address.book.domain.Zipcode;
import de.alpharogroup.service.rs.RestfulResource;

@Path("/country/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface ZipcodesResource extends RestfulResource<Integer, Zipcode>
{
}
