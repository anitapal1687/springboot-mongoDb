package com.anita.springbootmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.anita.springbootmongo.model.Book;

public interface BookRepository extends MongoRepository<Book, Integer>{

}
