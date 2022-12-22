package com.baeldung.sbdocker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoPsgApplication {

	private static Logger LOG = LoggerFactory.getLogger(DemoPsgApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =
		SpringApplication.run(DemoPsgApplication.class, args);
		String dsUri = ctx.getEnvironment().getProperty("spring.datasource.url");
		LOG.info("Connected to Datasource: " + dsUri);
	}

}
