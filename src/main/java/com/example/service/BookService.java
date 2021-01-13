package com.example.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Book;
import com.example.repo.BookRepository;
import static com.example.util.IterableUtil.* ;

@Service
public class BookService {

	@Autowired BookRepository bookDao ;
	
	public List<Book> listBooks(){
		return toList(bookDao.findAll()) ;
	}
	
	public Book addBook(Book book) {
		Book b = bookDao.save(book);
		return b ;
	}
	
	public Book getBookByIsbn(String isbn) {
		return bookDao.findByIsbn(isbn).orElse(null) ;
	}
	
	public List<Book> getBookByAuthor(String author) {
		return toList(bookDao.findByAuthor(author)) ;
	}
	
	public List<Book> getBookByLanguage(String language){
		return toList(bookDao.findByLang(language));
	}
	
	public List<Book> getBookTitleLike(String title){
		return toList(bookDao.findByTitleLike("%"+title+"%")) ;
	}
	
	public List<Book> getBookByAuthorAndTitle(String author, String title) {
		return toList(bookDao.queryAuthorAndTitle(author, title)) ;
	}
	
}
