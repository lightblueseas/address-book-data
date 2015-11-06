package de.alpharogroup.address.book.rest.client;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;

import de.alpharogroup.address.book.rest.api.AddressesResource;
import de.alpharogroup.address.book.rest.api.CountriesResource;
import de.alpharogroup.address.book.rest.api.FederalstatesResource;
import de.alpharogroup.address.book.rest.api.ZipcodesResource;
import lombok.Getter;

public class AddressBookRestClient
{
	@Getter
	private final AddressesResource addressesResource;
	
	@Getter
	private final CountriesResource countriesResource;
	
	@Getter
	private final FederalstatesResource federalstatesResource;
	
	@Getter
	private final ZipcodesResource zipcodesResource;

	public AddressBookRestClient(String baseUrl)
	{
		List<Object> providers = new ArrayList<>();
		providers.add(new JacksonJsonProvider());		

		addressesResource  = JAXRSClientFactory.create(baseUrl, AddressesResource.class, providers);
        WebClient.client(addressesResource).accept(MediaType.APPLICATION_JSON);
        WebClient.client(addressesResource).type(MediaType.APPLICATION_JSON);

        countriesResource  = JAXRSClientFactory.create(baseUrl, CountriesResource.class, providers);
        WebClient.client(countriesResource).accept(MediaType.APPLICATION_JSON);
        WebClient.client(countriesResource).type(MediaType.APPLICATION_JSON);

        federalstatesResource  = JAXRSClientFactory.create(baseUrl, FederalstatesResource.class, providers);
        WebClient.client(federalstatesResource).accept(MediaType.APPLICATION_JSON);
        WebClient.client(federalstatesResource).type(MediaType.APPLICATION_JSON);

        zipcodesResource  = JAXRSClientFactory.create(baseUrl, ZipcodesResource.class, providers);
        WebClient.client(zipcodesResource).accept(MediaType.APPLICATION_JSON);
        WebClient.client(zipcodesResource).type(MediaType.APPLICATION_JSON);
	}

}
