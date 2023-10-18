package com.activemq.springboot.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class OrderTransaction {

	@Id
	private String id;
	private String from;
	private String to;
	private BigDecimal amount;
}
