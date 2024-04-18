package it.unife.jarvis.backend.components;

import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.client.RestTemplateBuilder;

@Component
public class RESTConsumer {

	RestTemplate restTemplate;

	@Autowired
	public RESTConsumer(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}
	
	public <E> E consumeRESTParse(String uri, Class<E> cls) {
		E parsedObject = restTemplate.getForObject(uri, cls);
		return parsedObject;
	}

	public String consumeREST (String uri) {
		String rawJSON = restTemplate.getForObject(uri, String.class);
		return rawJSON;
	}
}
