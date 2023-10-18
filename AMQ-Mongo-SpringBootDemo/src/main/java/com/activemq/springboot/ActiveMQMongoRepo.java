package com.activemq.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
@EnableMongoRepositories(basePackages = "com.activemq.springboot")
public class ActiveMQMongoRepo {

	public static void main(String[] args) {
		SpringApplication.run(ActiveMQMongoRepo.class, args);
	}

}
