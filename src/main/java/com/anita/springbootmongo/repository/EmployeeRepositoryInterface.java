package com.anita.springbootmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anita.springbootmongo.model.Book;
import com.anita.springbootmongo.model.Employee;

public interface EmployeeRepositoryInterface extends MongoRepository<Employee, Integer>{

	List<Employee> findByBooks(Book book);
}
