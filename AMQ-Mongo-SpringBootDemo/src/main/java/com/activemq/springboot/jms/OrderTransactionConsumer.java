package com.activemq.springboot.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.activemq.springboot.model.OrderTransaction;
import com.activemq.springboot.repository.OrderTransactionRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderTransactionConsumer {

	private final OrderTransactionRepository transactionRepository;

	@Autowired
	public OrderTransactionConsumer(OrderTransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}

	private int count = 1;

	@JmsListener(destination = "OrderTransactionQueue", containerFactory = "myFactory")
	public void receiveMessage(OrderTransaction transaction) {
		log.info("[Consumer-Queue] <" + count + "> Received <" + transaction + ">");
		
		// throw new RuntimeException();
		log.info("[Consumer-Queue] <" + count + "> Successfully Saved to Mongo Db!! <" + transaction + ">");
		transactionRepository.save(transaction);
		
		OrderTransaction ot = transactionRepository.findById(transaction.getId()).get();
		log.info("[MongoDb] Transaction Record fetched from Db: [{}]\n", ot);
		
		count++;
	}

}
