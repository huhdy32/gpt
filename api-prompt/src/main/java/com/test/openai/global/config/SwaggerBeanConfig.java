package com.test.openai.global.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class SwaggerBeanConfig {

	public SwaggerBeanConfig(final MappingJackson2HttpMessageConverter converter) {
		final List<MediaType> mediaTypes = new ArrayList<>(converter.getSupportedMediaTypes());
		mediaTypes.add(new MediaType("application", "octet-stream"));
		converter.setSupportedMediaTypes(mediaTypes);
	}

}
