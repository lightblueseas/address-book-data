package de.alpharogroup.address.book.rest.client;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.client.WebClient;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import de.alpharogroup.address.book.rest.api.AddressesResource;
import de.alpharogroup.address.book.rest.api.CountriesResource;
import de.alpharogroup.address.book.rest.api.FederalstatesResource;
import de.alpharogroup.address.book.rest.api.ZipcodesResource;
import lombok.Getter;

/**
 * The class {@link AddressBookRestClient} is a rest client for accessing the rest services from the address-book database.
 */
public class AddressBookRestClient
{

	/** The Constant DEFAULT_HTTP_PORT. */
	public static final int DEFAULT_HTTP_PORT = 8080;

	/** The Constant BASE_URL_PREFIX. */
	public static final String BASE_URL_PREFIX = "http://localhost";

	/** The Constant DEFAULT_BASE_URL. */
	public static final String DEFAULT_BASE_URL = BASE_URL_PREFIX + ":" + DEFAULT_HTTP_PORT;
	
	/**
	 * The {@link AddressesResource}.
	 */
	@Getter
	private final AddressesResource addressesResource;

	/**
	 * The {@link CountriesResource}.
	 */
	@Getter
	private final CountriesResource countriesResource;

	/**
	 * The {@link FederalstatesResource}.
	 */
	@Getter
	private final FederalstatesResource federalstatesResource;

	/**
	 * The {@link ZipcodesResource}.
	 */
	@Getter
	private final ZipcodesResource zipcodesResource;

	/**
	 * Instantiates a new {@link AddressBookRestClient} with the default base url.
	 */
	public AddressBookRestClient() {
		this(DEFAULT_BASE_URL);
	}
	/**
	 * Instantiates a new {@link AddressBookRestClient}.
	 *
	 * @param baseUrl the base url
	 */
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
