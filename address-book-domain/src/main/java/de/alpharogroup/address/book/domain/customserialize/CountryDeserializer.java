package de.alpharogroup.address.book.domain.customserialize;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import de.alpharogroup.address.book.domain.Country;
import de.alpharogroup.xml.json.JsonTransformer;

public class CountryDeserializer extends JsonDeserializer<Country> {

	@Override
	public Country deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);		
		return JsonTransformer.getObjectMapper().treeToValue(node, Country.class);
	}

}
