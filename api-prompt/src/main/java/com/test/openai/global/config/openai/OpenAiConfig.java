package com.test.openai.global.config.openai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenAiConfig {

	private final String apiKey;

	public OpenAiConfig(@Value("${openai.api.key}") final String key) {
		this.apiKey = key;
	}

	@Bean
	public OpenAiRestTemplate template() {
		final OpenAiRestTemplate restTemplate = new OpenAiRestTemplate();
		restTemplate.getInterceptors().add((request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + apiKey);
			return execution.execute(request, body);
		});
		return restTemplate;
	}

}
