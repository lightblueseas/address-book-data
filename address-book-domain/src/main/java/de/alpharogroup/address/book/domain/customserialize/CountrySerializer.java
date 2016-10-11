package de.alpharogroup.address.book.domain.customserialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.xml.json.JsonTransformer;

public class CountrySerializer extends JsonSerializer<Country> {

	@Override
	public void serialize(Country value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		String jsonCountry = JsonTransformer.toJson(value);
		gen.writeString(jsonCountry);
	}


}
