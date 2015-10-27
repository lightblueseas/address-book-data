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
public class Federalstate extends BaseBusinessObject<Integer> {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/** The reference to the country in wich the federal state exists. */
	private Country country;
	/** The iso3166 code with two characters. */
	private String iso3166A2code;
	/** The name of the federal state. */
	private String name;
	/** The category from the subivision. */
	private String subdivisionCategory;
	/** The name from the subdivision. */
	private String subdivisionName;

}
