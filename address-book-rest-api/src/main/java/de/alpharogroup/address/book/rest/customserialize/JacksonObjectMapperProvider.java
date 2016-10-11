package de.alpharogroup.address.book.rest.customserialize;

import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.cfg.Annotations;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

import de.alpharogroup.address.book.domain.customserialize.CountryModule;

@Provider
public class JacksonObjectMapperProvider extends JacksonJsonProvider {

	public JacksonObjectMapperProvider() {
		super();
		initModules();
	}

	public JacksonObjectMapperProvider(Annotations... annotationsToUse) {
		super(annotationsToUse);
		initModules();
	}

	public JacksonObjectMapperProvider(ObjectMapper mapper, Annotations[] annotationsToUse) {
		super(mapper, annotationsToUse);
		initModules();
	}

	public JacksonObjectMapperProvider(ObjectMapper mapper) {
		super(mapper);
		initModules();
	}

	private void initModules() {
		ObjectMapper mapper = _mapperConfig.getConfiguredMapper();
		if (mapper != null) {
			mapper.registerModule(new CountryModule());
		} else {
			mapper = _mapperConfig.getDefaultMapper();
			mapper.registerModule(new CountryModule());
		}
	}

}