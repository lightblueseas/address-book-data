package de.alpharogroup.address.book.application.model;

import java.util.List;

import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Federalstates;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FederalStatesOfCountry {

	private Countries country;
	
	private List<Federalstates> federalstates;
	
}
