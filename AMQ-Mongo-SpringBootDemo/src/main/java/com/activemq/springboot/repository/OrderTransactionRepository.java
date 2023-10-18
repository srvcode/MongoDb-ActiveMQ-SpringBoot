package com.activemq.springboot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.activemq.springboot.model.OrderTransaction;

@Repository
public interface OrderTransactionRepository extends MongoRepository<OrderTransaction, String> {

}
