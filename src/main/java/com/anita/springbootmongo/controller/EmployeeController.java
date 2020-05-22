package com.anita.springbootmongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anita.springbootmongo.model.Book;
import com.anita.springbootmongo.model.Employee;
import com.anita.springbootmongo.model.EmployeeBook;
import com.anita.springbootmongo.repository.SequenceRepository;
import com.anita.springbootmongo.repository.EmployeeRepository;
import com.anita.springbootmongo.repository.EmployeeRepositoryInterface;

@RestController
public class EmployeeController {
	
	@Autowired
	SequenceRepository repRepo;
	
	@Autowired
	EmployeeRepositoryInterface empIn;
	
	@Autowired
	EmployeeRepository emprepo;
	
	@PostMapping("/addEmployee")
	public void addEmployeeDetails(@RequestBody Employee employee){
		

		employee.setId(repRepo.generateSequence(Employee.SEQUENCE_NAME));
		empIn.save(employee);
		
	}
	
	
	@PostMapping("/findEmployee")
	public void findEmployeeDetails(@RequestBody Employee employee){
		
		emprepo.findAllEmployee(employee.getEmpName(), employee.getDept());
		
	}
	
	@PostMapping("/findByEmpBookname")
	public List<Employee> searchByBookName(@RequestBody Book book){
		return empIn.findByBooks(book);
	}
	
	
	@PostMapping("/findByEmpBookname1")
	public List<Book>  findByEmpBookname1(){
		return emprepo.lookupOperation();
	}
	
	

}
