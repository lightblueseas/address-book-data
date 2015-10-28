package de.alpharogroup.address.book.comparators;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.alpharogroup.address.book.entities.Addresses;


public class AddressesComparator implements Comparator<Addresses> {

	@Override
	public int compare(final Addresses o1, final Addresses o2) {
		return new CompareToBuilder()
		.append(o1.getId(), o2.getId())
		.append(o1.getAddressComment(), o2.getAddressComment())
		.append(o1.getStreetnumber(), o2.getStreetnumber())
		.append(o1.getFederalstate(), o2.getFederalstate())
		.append(o1.getZipcode(), o2.getZipcode())
		.append(o1.getStreet(), o2.getStreet())
		.toComparison();
	}

}