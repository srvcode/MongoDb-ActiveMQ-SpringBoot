package com.activemq.springboot.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer age;
	private String department;
}
