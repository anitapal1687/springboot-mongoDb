package com.anita.springbootmongo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anita.springbootmongo.model.Book;
import com.anita.springbootmongo.model.Domain;
import com.anita.springbootmongo.model.Employee;
import com.anita.springbootmongo.repository.BookNameRepository;
import com.anita.springbootmongo.repository.BookRepository;
import com.anita.springbootmongo.repository.SequenceRepository;


@RestController
public class BookController {

	
	@Autowired
	private BookRepository repository;
	
	
	@Autowired
	private BookNameRepository boi;
	
	@Autowired
	SequenceRepository repRepo;


	@PostMapping("/addBook")
	public String saveBook(@RequestBody Book book) {
		book.setId(repRepo.generateSequence(Employee.SEQUENCE_NAME));
		repository.save(book);
		return "Added book with id : " + book.getId();
	}

	@GetMapping("/findAllBooks")
	public List<Book> getBooks() {
		return repository.findAll();
	}

	@GetMapping("/getBook/{id}")
	public Optional<Book> getBook(@PathVariable int id) {
		return repository.findById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		repository.deleteById(id);
		return "book deleted with id : " + id;
	}
	
	@PostMapping("/update")
	public long updateBookByName(@RequestBody Book book){
		return boi.updateBookName(book.getBookName(), book.getAuthorName());
	}
	
	@PostMapping("/findByBookname")
	public List<Book> searchByBookName(@RequestBody Book book){
		return repository.findByBookName(book.getBookName());
	}
	
	@GetMapping("/deleteByBookName/{bookName}")
	public void deletebyBookName(@PathVariable("bookName") String bookName) {
		System.out.println("Delete book name ");
		repository.deleteByBookName(bookName);
		//return "book deleted with id : " + book.getBookName();
	}
	
	@GetMapping("/countTotalBooks")
	public long countTotalBooks(){
		return boi.count();
	}
	
	
	@GetMapping("/countByAuthorName/{authorName}")
	public long countByAuthorname(@PathVariable("authorName") String authorName){
		return boi.countByAuthorName(authorName);
	}
	
	
	@GetMapping("/sumPriceByAuthorName")
	public List<Domain> sumPriceByAuthorName(){
		return boi.sumPriceByAuthorName();
	}
	
	@GetMapping("/sortByPrice")
	public List<Book> sortByPrice(){
		return repository.sortByPrice();
	}
	
	
	
	//delete book by name
	//update Book author name
	
	
}
