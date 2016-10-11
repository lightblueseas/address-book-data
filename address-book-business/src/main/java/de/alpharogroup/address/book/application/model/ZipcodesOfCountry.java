package de.alpharogroup.address.book.application.model;

import java.util.List;

import de.alpharogroup.address.book.entities.Countries;
import de.alpharogroup.address.book.entities.Zipcodes;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ZipcodesOfCountry {

	private Countries country;
	
	private List<Zipcodes> zipcodes;
	
}
