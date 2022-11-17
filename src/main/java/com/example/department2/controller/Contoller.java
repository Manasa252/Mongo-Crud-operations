package com.example.department2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.department2.model.Department2;
import com.example.department2.repository.Repository;

@RestController
public class Contoller {

	@Autowired
	Repository repo;

	@GetMapping("/dept")
	public ResponseEntity<?> getall() {
		List<Department2> dps = repo.findAll();
		return new ResponseEntity<List<Department2>>(dps, HttpStatus.OK);
	}

	@GetMapping("/dept/{deptid}")
	public ResponseEntity<?> getbyId(@PathVariable int deptid) {
		Department2 dp = repo.findById(deptid).get();
		return new ResponseEntity<Department2>(dp, HttpStatus.OK);
	}

	@PostMapping("/dept")
	public ResponseEntity<?> insertemp(@RequestBody Department2 department) {
		repo.save(department);
		return new ResponseEntity<>("Department created", HttpStatus.OK);
	}

}
