package de.alpharogroup.address.book.comparators;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.alpharogroup.address.book.entities.Zipcodes;

public class ZipcodesComparator implements Comparator<Zipcodes> {

	@Override
	public int compare(final Zipcodes o1, final Zipcodes o2) {
		return new CompareToBuilder()
		.append(o1.getCountry(), o2.getCountry())
		.append(o1.getCity(), o2.getCity())
		.append(o1.getId(), o2.getId())
		.append(o1.getZipcode(), o2.getZipcode())
		.toComparison();
	}

}