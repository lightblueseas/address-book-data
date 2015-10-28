package de.alpharogroup.address.book.service.locator;

import de.alpharogroup.address.book.service.api.AddressesService;
import de.alpharogroup.address.book.service.api.CountriesService;
import de.alpharogroup.address.book.service.api.FederalstatesService;
import de.alpharogroup.address.book.service.api.ZipcodesService;

public interface ServiceLocator {
	/**
	 * Gets the addresses business service.
	 * 
	 * @return the addresses business service
	 */
	AddressesService getAddressesService();

	/**
	 * Gets the countries business service.
	 * 
	 * @return the countries business service
	 */
	CountriesService getCountriesService();

	/**
	 * Gets the federalstates business service.
	 * 
	 * @return the federalstates business service
	 */
	FederalstatesService getFederalstatesService();

	/**
	 * Gets the zipcodes business service.
	 * 
	 * @return the zipcodes business service
	 */
	ZipcodesService getZipcodesService();

	/**
	 * Sets the addresses business service.
	 * 
	 * @param addressesService
	 *            the new addresses business service
	 */
	void setAddressesService(AddressesService addressesService);

	/**
	 * Sets the countries business service.
	 * 
	 * @param countriesService
	 *            the new countries business service
	 */
	void setCountriesService(CountriesService countriesService);

	/**
	 * Sets the federalstates business service.
	 * 
	 * @param federalstatesService
	 *            the new federalstates business service
	 */
	void setFederalstatesService(FederalstatesService federalstatesService);

	/**
	 * Sets the zipcodes business service.
	 * 
	 * @param zipcodesService
	 *            the new zipcodes business service
	 */
	void setZipcodesService(ZipcodesService zipcodesService);

}
