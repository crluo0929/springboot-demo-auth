package com.example.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Book;
import com.example.repo.BookRepository;

@Service
public class BookService {

	@Autowired BookRepository bookDao ;
	
	public List<Book> listBooks(){
		return iterableToList(bookDao.findAll()) ;
	}
	
	public Book addBook(Book book) {
		Book b = bookDao.save(book);
		return b ;
	}
	
	public Book getBookByIsbn(String isbn) {
		return bookDao.findByIsbn(isbn).orElse(null) ;
	}
	
	public List<Book> getBookByAuthor(String author) {
		return iterableToList(bookDao.findByAuthor(author)) ;
	}
	
	public List<Book> getBookByLanguage(String language){
		return iterableToList(bookDao.findByLang(language));
	}
	
	public List<Book> getBookTitleLike(String title){
		return iterableToList(bookDao.findByTitleLike("%"+title+"%")) ;
	}
	
	public List<Book> getBookByAuthorAndTitle(String author, String title) {
		return iterableToList(bookDao.queryAuthorAndTitle(author, title)) ;
	}
	
	private List<Book> iterableToList(Iterable<Book> itr){
		return StreamSupport
				.stream(itr.spliterator(), false)
				.collect(Collectors.toList()) ;
	}
}
