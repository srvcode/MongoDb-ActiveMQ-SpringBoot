package com.activemq.springboot.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.activemq.springboot.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsProducer {

	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${activemq.topic}")
	private String topic;
	
	public void sendMessage(Employee employee) {
		
		log.info("[Consumer-Topic]: Sending to Topic name: [{}]", topic);
        try {
            String jsonObj = new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(employee);
            log.info("[Producer-Topic]: Sending Content to Topic [{}]: \n{}", topic, jsonObj);
            jmsTemplate.send(topic, messageCreator -> {
                TextMessage message = messageCreator.createTextMessage();
                message.setText(jsonObj);
                return message;
            });
            log.info("[Producer-Topic]: Sent to Topic name: [{}]!!\n", topic);
        }
        catch (Exception ex) {
            log.error("[Producer-Topic]: ERROR in sending message to Topic: [{}]\n", topic);
        }
        
	}
}
