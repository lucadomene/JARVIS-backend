package it.unife.jarvis.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.boot.CommandLineRunner;

import it.unife.jarvis.backend.components.RESTConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BackendApplication {

	private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(RESTConsumer consumer) throws Exception {
		return args -> {
			String output = consumer.consumeREST("https://3d4b2335-1631-475f-ab52-03bf26a3d594.mock.pstmn.io/test");
			log.info(output);
		};
	}
}
