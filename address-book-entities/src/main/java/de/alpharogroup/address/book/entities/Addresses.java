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
 * The Entity class {@link Addresses} is keeping the information for addresses.
 */
@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
public class Addresses extends BaseEntity<Integer> implements Cloneable {

	/** The serial Version UID */
	private static final long serialVersionUID = 1L;
	/** The address comment. */
	@Column(name = "address_comment", length = 100)
	private String addressComment;

	/**
	 * The federalstate attribute is the federal state from this
	 * {@link Addresses
	 * } object.
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "federalstate_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ADDRESSES_FEDERALSTATE_ID"))
	private Federalstates federalstate;
	/** The geohash from this {@link Addresses} object. */
	@Column(length = 16)
	private String geohash;
	/**
	 * The latitude from the address. Latitude is a geographical term denoting
	 * the north/south angular location of a place on a sphere.
	 */
	@Column(length = 12)
	private String latitude;
	/** The longitude from the address. */
	@Column(length = 12)
	private String longitude;
	/** The name of the street. */
	@Column(length = 64)
	private String street;
	/** The streetnumber. */
	@Column(length = 5)
	private String streetnumber;
	/** The zipcode from this {@link Addresses} object. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "zipcode_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ADDRESSES_ZIPCODE_ID"))
	private Zipcodes zipcode;

}
