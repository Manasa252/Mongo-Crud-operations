package com.codedecode.mongoCrud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codedecode.mongoCrud.MongoCrudApplication;
import com.codedecode.mongoCrud.model.Department2;
import com.codedecode.mongoCrud.model.Employee;
import com.codedecode.mongoCrud.model.OutputModel;
import com.codedecode.mongoCrud.repository.EmployeeRepository;
import com.codedecode.mongoCrud.service.EmployeeService;
import com.codedecode.mongoCrud.service.SequenceGeneratorService;

@RestController
@EnableCaching
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeRepository repo;

	@Autowired
	private SequenceGeneratorService seqservice;
	Logger logger = LoggerFactory.getLogger(MongoCrudApplication.class);
	
	

	@PostMapping("/emp")
	public ResponseEntity<Employee> saveOrUpdate(@RequestBody Employee emp) {
		emp.setId("DSCE-" + String.valueOf(seqservice.getSequenceNumber(Employee.SEQUENCE_NAME)));
		return new ResponseEntity<Employee>(employeeService.saveOrUpdate(emp), HttpStatus.ACCEPTED);
	}

	@GetMapping("/emp")
	public ResponseEntity<List<Employee>> findAll() {
		return new ResponseEntity<List<Employee>>(employeeService.findAll(), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/emp/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		employeeService.delete(id);
		return new ResponseEntity<String>("Record Deleted", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/emp/deptname/{id}")
	@Cacheable(key="#id", value="Employee")
    public OutputModel getOutput(@PathVariable String id) {
		Employee emp = repo.findById(id).get();
        String uri = "http://localhost:8089/dept/{deptid}";
        Map<String,Integer> uriparam = new HashMap<>();
        uriparam.put("deptid", emp.getDeptid());
        RestTemplate restTemplate = new RestTemplate();
        Department2 res = restTemplate.getForObject(uri,Department2.class, uriparam );
        logger.info(res.toString());
        OutputModel om = new OutputModel();
        om.setId(emp.getId());
        om.setAddress(emp.getAddress());
        om.setDeptid(emp.getDeptid());
        om.setName(emp.getName());
        om.setDname(res.getDname()); 
        return om;
    }
}
