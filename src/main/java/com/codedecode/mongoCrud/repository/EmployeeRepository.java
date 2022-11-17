package com.codedecode.mongoCrud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.codedecode.mongoCrud.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee,String>{
	


}
