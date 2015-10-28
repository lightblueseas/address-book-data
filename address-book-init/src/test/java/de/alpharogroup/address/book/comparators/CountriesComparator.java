package de.alpharogroup.address.book.comparators;

import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;

import de.alpharogroup.address.book.entities.Countries;


public class CountriesComparator implements Comparator<Countries> {

	@Override
	public int compare(final Countries o1, final Countries o2) {
		return new CompareToBuilder()
		.append(o1.getId(), o2.getId())
		.append(o1.getName(), o2.getName())
		.append(o1.getIso3166A3name(), o2.getIso3166A3name())
		.append(o1.getIso3166A2name(), o2.getIso3166A2name())
		.append(o1.getIso3166Number(), o2.getIso3166Number())
		.toComparison();
	}

}