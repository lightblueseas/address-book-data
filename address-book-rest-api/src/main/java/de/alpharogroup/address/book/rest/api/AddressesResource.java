package de.alpharogroup.address.book.rest.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.alpharogroup.address.book.domain.Address;
import de.alpharogroup.service.rs.RestfulResource;

@Path("/address/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AddressesResource extends RestfulResource<Integer, Address>
{
	@GET
	@Path("/geohash/{geohash}/")
	List<Address> find(@PathParam("geohash") String geohash);
}
