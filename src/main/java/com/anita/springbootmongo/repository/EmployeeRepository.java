package com.anita.springbootmongo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.anita.springbootmongo.model.Book;
import com.anita.springbootmongo.model.Employee;
import com.anita.springbootmongo.model.EmployeeBook;

@Service
public class EmployeeRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	// find by name and dept
	public List<Employee> findAllEmployee (String empName, String dept){
		
		 Query query = new Query(Criteria.where("empName").is(empName).andOperator(Criteria.where("dept").is(dept)));
		 List<Employee>  emp= mongoTemplate.find(query, Employee.class);
		 
		 
		 return emp;
	}
	
	
    public List<Book>  lookupOperation(){
    LookupOperation lookupOperation = LookupOperation.newLookup()
                        .from("Book")
                        .localField("bookName")
                        .foreignField("bookName")
                        .as("ssss");

    Aggregation aggregation = Aggregation.newAggregation(lookupOperation);
        List<Book> results = mongoTemplate.aggregate(aggregation, Employee.class, Book.class).getMappedResults();
    return results;
    }
	
}
