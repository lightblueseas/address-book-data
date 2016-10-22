package de.alpharogroup.address.book.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The class {@link LocationSearchModel}.
 *
 * @param <T> the generic type
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationSearchModel<Address> {
	
	/** The model object. */
	private LocationAddressModel location;
	
	/** The zipcode. */
	private String zipcode;
	
	/** The error key. */
	private String errorKey;
}
