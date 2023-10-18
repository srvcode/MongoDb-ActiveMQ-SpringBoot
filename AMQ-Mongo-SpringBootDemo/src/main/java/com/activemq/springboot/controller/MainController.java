package com.activemq.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.activemq.springboot.jms.JmsProducer;
import com.activemq.springboot.model.Employee;
import com.activemq.springboot.model.OrderTransaction;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class MainController {

	@Autowired
	JmsProducer jmsProducer;
	
	@Autowired
	JmsTemplate jmsTemplate;

	@PostMapping("/api/employee")
	public Employee employeeController(@RequestBody Employee employee) {
		log.info("[Producer]: Inside Employee Controller: Employee {}", employee);
		jmsProducer.sendMessage(employee);
		return employee;
	}
	
	@PostMapping("api/orderTransaction")
	public void orderTransactionController(@RequestBody OrderTransaction transaction) throws JmsException, JsonProcessingException {
		log.info("[Producer]: Sending Order Transaction Controller: Transaction {}", transaction);
	    // Post message to the message queue named "OrderTransactionQueue"
	    jmsTemplate.convertAndSend("OrderTransactionQueue", transaction);
	}

}
