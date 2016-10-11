package de.alpharogroup.address.book.rest.beanparams;

import de.alpharogroup.address.book.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class {@link AddressSearchCriteria}.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressSearchCriteria {

	/** The country. */
	private Country country;
	
	/** The zipcode. */
	private String zipcode;
	
	/** The city. */
	private String city;

}
