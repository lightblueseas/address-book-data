package de.alpharogroup.address.book.comparators;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.alpharogroup.address.book.entities.Federalstates;


public class FederalstatesComparator implements Comparator<Federalstates> {

	@Override
	public int compare(final Federalstates o1, final Federalstates o2) {
		return new CompareToBuilder()
		.append(o1.getId(), o2.getId())
		.append(o1.getIso3166A2code(), o2.getIso3166A2code())
		.append(o1.getSubdivisionCategory(), o2.getSubdivisionCategory())
		.append(o1.getName(), o2.getName())
		.append(o1.getSubdivisionName(), o2.getSubdivisionName())
		.append(o1.getCountry(), o2.getCountry())
		.toComparison();
	}

}