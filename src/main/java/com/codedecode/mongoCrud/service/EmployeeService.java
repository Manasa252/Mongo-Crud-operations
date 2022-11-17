package com.codedecode.mongoCrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.codedecode.mongoCrud.model.Employee;
import com.codedecode.mongoCrud.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveOrUpdate(Employee emp) {
		return employeeRepository.save(emp);
	}

	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public void delete(String id) {
		employeeRepository.deleteById(id);
	}
}
