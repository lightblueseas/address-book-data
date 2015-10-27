package de.alpharogroup.address.book.domain;

import de.alpharogroup.db.domain.BaseBusinessObject;
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
public class Address extends BaseBusinessObject<Integer> {
 
	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** The address comment. */
	private String addressComment;

	/**
	 * The federalstate attribute is the federal state from this
	 * {@link Address} object.
	 */
	private Federalstate federalstate;
	/** The geohash from this {@link Address} object. */
	private String geohash;
	/**
	 * The latitude from the address. Latitude is a geographical term denoting
	 * the north/south angular location of a place on a sphere.
	 */
	private String latitude;
	/** The longitude from the address. */
	private String longitude;
	/** The name of the street. */
	private String street;
	/** The streetnumber. */
	private String streetnumber;
	/** The zipcode from this {@link Address} object. */
	private Zipcode zipcode;
}
