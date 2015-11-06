package de.alpharogroup.address.book.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.alpharogroup.address.book.domain.Federalstate;
import de.alpharogroup.db.service.rs.RestfulResource;

@Path("/federalstate/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface FederalstatesResource extends RestfulResource<Integer, Federalstate>
{
}
