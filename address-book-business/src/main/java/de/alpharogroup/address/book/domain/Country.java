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
public class Country extends BaseBusinessObject<Integer> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** The iso3166 name with two characters. */
	private String iso3166A2name;
	/** The iso3166 name with three characters. */
	private String iso3166A3name;
	/** The iso3166 number with three characters. */
	private String iso3166Number;
	/** The name of the country. */
	private String name;
}
