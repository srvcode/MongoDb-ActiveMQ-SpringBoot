package com.activemq.springboot.config;

import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.util.ErrorHandler;

import jakarta.jms.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ActiveMQConfig {

	// Serialize message content to json using TextMessage
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	// Only required due to defining myFactory in the receiver
	@Bean
	public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {

		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();

		// anonymous class
		factory.setErrorHandler(new ErrorHandler() {

			@Override
			public void handleError(Throwable t) {
				log.warn("An error has occurred in the transaction", t);
			}
		});

		// lambda function
		factory.setErrorHandler(t -> log.warn("An error has occurred in the transaction", t));

		configurer.configure(factory, connectionFactory);
		return factory;
	}
}
