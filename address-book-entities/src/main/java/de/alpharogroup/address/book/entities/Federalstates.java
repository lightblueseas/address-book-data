package de.alpharogroup.address.book.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.alpharogroup.db.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Entity class {@link Federalstates} is keeping the information
 * for the federal states from the countries.
 */
@Entity
@Table(name = "federalstates")
@Getter
@Setter
@NoArgsConstructor
public class Federalstates 
extends BaseEntity<Integer>
implements Cloneable {

	/** The serial Version UID */
	private static final long serialVersionUID = -2105692517269551804L;
	/** The reference to the country in wich the federal state exists. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_FEDERAL_STATES_COUNTRY_ID"))
	private Countries country;
	/** The iso3166 code with two characters. */
	@Column(name = "iso3166_a2code", length = 6)
	private String iso3166A2code;
	/** The name of the federal state. */
	@Column(length = 128)
	private String name;
	@Column(name = "subdivision_category", length = 128)
	/** The category from the subivision. */
	private String subdivisionCategory;
	/** The name from the subdivision. */
	@Column(name = "subdivision_name", length = 56)
	private String subdivisionName;

}
