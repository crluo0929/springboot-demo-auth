package com.example.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer>{

	public Optional<Book> findByIsbn(String isbn) ;
	
	public Iterable<Book> findByAuthor(String author) ;
	
	public Iterable<Book> findByLang(String language) ;
	
	public Iterable<Book> findByTitleLike(String title) ;
	
	//可以使用 findByAuthorAndTitleLike()
	@Query(value = "select * from book where author=?1 and title like %?2%", nativeQuery = true)
	public Iterable<Book> queryAuthorAndTitle(String author, String title) ;
}
