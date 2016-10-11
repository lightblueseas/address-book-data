package de.alpharogroup.address.book.domain.customserialize;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;

import de.alpharogroup.address.book.domain.Country;


public class CountryModule extends SimpleModule {

	private static final long serialVersionUID = 1L;
	
	public CountryModule() {
		super("ObjectIdSerializerModule", new Version(0, 1, 0, "alpha", "de.alpharogroup", "address-book-domain"));
        addDeserializer(Country.class, new CountryDeserializer());
        addSerializer(Country.class, new CountrySerializer());
	}



}
