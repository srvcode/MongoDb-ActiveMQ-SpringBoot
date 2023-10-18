package com.activemq.springboot.jms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JmsConsumer {

	@Value("${activemq.topic}")
    String topic;
	
    @JmsListener(destination = "${activemq.topic}")
	public void receiveTopicMessage(Message message) throws JMSException, InterruptedException {
    	Thread.sleep(2000L);
    	String messageData = null;
        log.info("[Consumer-Topic]: Received message from [{}]: \n{}", topic, message);
        if(message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage)message;
            messageData = textMessage.getText();
            log.info("[Consumer-Topic]: [{}] Content: {}\n", topic, messageData);
        }

    }

}
