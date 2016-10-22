package de.alpharogroup.address.book.domain.model;

import de.alpharogroup.address.book.application.model.LocationModel;
import de.alpharogroup.address.book.domain.Address;
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
public class LocationAddressModel implements LocationModel<Address> {

	private static final long serialVersionUID = 1L;
	private String location;
	private String selectedCountryName;
	private Address address;

}
