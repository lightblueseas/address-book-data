package de.alpharogroup.address.book.application.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FederalStateNamesOfCountryName {

	private String country;
	
	private List<String> federalstates;
	
}
