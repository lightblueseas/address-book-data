package de.alpharogroup.address.book.application.model;

import java.io.Serializable;

import de.alpharogroup.address.book.entities.Addresses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationAddressesModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String location;
	private String selectedCountryName;
	private Addresses address;

}
