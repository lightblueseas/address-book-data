package de.alpharogroup.address.book.domain;

import de.alpharogroup.domain.BaseDomainObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper=true)
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Zipcode extends BaseDomainObject<Integer> {
	/** The serial Version UID */
	private static final long serialVersionUID = 1L;
	/** The country of this zipcode object. */
	private Country country;
	/** The zipcode from the city. */
	private String zipcode;
	/** The name from the city. */
	private String city;

}
