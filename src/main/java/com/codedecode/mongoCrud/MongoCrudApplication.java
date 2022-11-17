package com.codedecode.mongoCrud;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.codedecode.mongoCrud.model.Employee;
import com.codedecode.mongoCrud.repository.EmpRepository;
import com.codedecode.mongoCrud.service.SequenceGeneratorService;

@SpringBootApplication
@RestController

public class MongoCrudApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MongoCrudApplication.class, args);
		Logger logger = LoggerFactory.getLogger(MongoCrudApplication.class);
		logger.info("Hello World");
		logger.debug("Debug log message");
//        logger.warn("Warn log message");
//        logger.error("Error log message");
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
