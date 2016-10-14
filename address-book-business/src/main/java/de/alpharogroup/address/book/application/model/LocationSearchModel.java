package de.alpharogroup.address.book.application.model;

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
public class LocationSearchModel<T> {
	
	/** The model object. */
	private LocationModel<T> location;
	
	/** The zipcode. */
	private String zipcode;
	
	/** The error key. */
	private String errorKey;
}
