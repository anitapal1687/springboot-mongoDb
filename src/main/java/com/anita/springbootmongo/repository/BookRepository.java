package com.anita.springbootmongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


import com.anita.springbootmongo.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{
	//@Query(value="{bookName:Inception}", fields="{bookName : 1, id : 0}", sort="{DESC}")
	
	List<Book> findByBookName(String bookName);
	void deleteByBookName(String bookName);
	@Query(value="{}", fields="{}", sort="{price:-1}")
	List<Book> sortByPrice();
	
}
