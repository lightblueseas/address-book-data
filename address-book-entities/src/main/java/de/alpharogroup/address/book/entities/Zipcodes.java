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
 * The Entity class {@link Zipcodes} is keeping the information for the cities
 * and the corresponding zipcodes.
 */
@Entity
@Table(name = "zipcodes")
@Getter
@Setter
@NoArgsConstructor
public class Zipcodes extends BaseEntity<Integer> implements Cloneable {

	/** The serial Version UID */
	private static final long serialVersionUID = 1L;
	/** The country of this zipcode object. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ZIP_CODES_COUNTRY_ID"))
	private Countries country;
	/** The zipcode from the city. */
	@Column(nullable = false, length = 10)
	private String zipcode;
	/** The name from the city. */
	@Column(length = 128)
	private String city;

}
