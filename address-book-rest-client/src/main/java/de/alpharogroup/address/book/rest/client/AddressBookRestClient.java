package de.alpharogroup.address.book.rest.client;

import java.util.ArrayList;
import java.util.List;


import de.alpharogroup.address.book.rest.api.AddressesResource;
import de.alpharogroup.address.book.rest.api.CountriesResource;
import de.alpharogroup.address.book.rest.api.FederalstatesResource;
import de.alpharogroup.address.book.rest.api.ZipcodesResource;
import de.alpharogroup.cxf.rest.client.AbstractRestClient;
import de.alpharogroup.service.rs.providers.CustomTypeModulesRegistrationProvider;
import lombok.Getter;

/**
 * The class {@link AddressBookRestClient} is a rest client for accessing the rest services from the
 * address-book database.
 */
public class AddressBookRestClient extends AbstractRestClient
{

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
	public AddressBookRestClient()
	{
		this(DEFAULT_BASE_URL);
	}

	/**
	 * Instantiates a new {@link AddressBookRestClient}.
	 *
	 * @param baseUrl
	 *            the base url
	 */
	public AddressBookRestClient(final String baseUrl)
	{
		super(baseUrl);
		addressesResource = newResource(AddressesResource.class);

		countriesResource = newResource(CountriesResource.class);

		federalstatesResource = newResource(FederalstatesResource.class);

		zipcodesResource =  newResource(ZipcodesResource.class);
	}
	

	/**
	 * Factory callback method for the list of providers. This method is
	 * invoked in the constructor and can be overridden so users can
	 * add specific providers for the rest client.
	 *
	 * @return the list
	 */
	protected List<Object> newProviders()
	{
		final List<Object> providers = new ArrayList<>();
		providers.add(new CustomTypeModulesRegistrationProvider());
		return providers;
	}

}
