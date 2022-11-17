package com.example.department2.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.department2.model.Department2;

public interface Repository extends MongoRepository<Department2, Integer>{

}
