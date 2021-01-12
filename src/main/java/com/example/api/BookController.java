package com.example.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Book;
import com.example.service.BookService;

@RestController
public class BookController {

	@Autowired BookService bookService ;
	
	@GetMapping("/api/book/list")
	public List<Book> listBooks(){
		return bookService.listBooks() ;
	}
	
	@GetMapping("/api/book/isbn/{isbn}")
	public Book getBookByIsbn(@PathVariable String isbn) {
		return bookService.getBookByIsbn(isbn);
	}
	
	@PutMapping("/api/book")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}
	
	@GetMapping("/api/book/author/{author}")
	public List<Book> getBookByAuthor(@PathVariable String author) {
		return bookService.getBookByAuthor(author);
	}
	
	@GetMapping("/api/book/lang/{language}")
	public List<Book> getBookByLanguage(@PathVariable String language){
		return bookService.getBookByLanguage(language) ;
	}
	
	@GetMapping("/api/book/title/{title}")
	public List<Book> getBookTitleLike(@PathVariable String title){
		return bookService.getBookTitleLike(title);
	}
	
	@PostMapping("/api/book/")
	public List<Book> getBookByAuthorAndTitle(@RequestBody Map<String,String> condition) {
		String author = condition.get("author") ;
		String title = condition.get("title") ;
		return bookService.getBookByAuthorAndTitle(author, title) ;
	}
	
	
}