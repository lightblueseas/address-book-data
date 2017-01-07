package de.alpharogroup.address.book.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import de.alpharogroup.db.entity.BaseEntity;

/**
 * The entity class {@link Countries} is keeping the information for all
 * countries in the world.
 */
@Entity
@Table(name = "countries")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class Countries 
extends BaseEntity<Integer>
implements Cloneable {

	/** The serial Version UID. */
	private static final long serialVersionUID = 1L;
	/** The iso3166 name with two characters. */
	@Column(name = "iso3166_a2name", length = 2)
	private String iso3166A2name;
	/** The iso3166 name with three characters. */
	@Column(name = "iso3166_a3name", length = 3)
	private String iso3166A3name;
	/** The iso3166 number with three characters. */
	@Column(name = "iso3166_number", length = 3)
	private String iso3166Number;
	/** The name of the country. */
	@Column(length = 128)
	private String name;
	
}
