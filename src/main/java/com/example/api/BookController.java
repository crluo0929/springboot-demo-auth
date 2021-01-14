package com.example.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Book;
import com.example.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "書本相關API")
public class BookController {

	@Autowired BookService bookService ;
	
	@ApiOperation(value = "列出所有書本", notes="列出所有書本")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "列出所有書本")
	})
	@GetMapping("/api/book/list")
	public List<Book> listBooks(){
		return bookService.listBooks() ;
	}
	
	@ApiOperation(value = "以ISBN號碼查詢書本", notes="以ISBN號碼查詢書本")
	@ApiImplicitParams(value = @ApiImplicitParam(
			name = "isbn",
			required = true,
			value="輸入ISBN號碼",
			paramType = "path",
			dataTypeClass = String.class
	))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "取得書本"),
			@ApiResponse(code = 404, message = "查無書本")
	})
	@GetMapping("/api/book/isbn/{isbn}")
	public Book getBookByIsbn(@PathVariable String isbn) {
		return bookService.getBookByIsbn(isbn);
	}
	
	@ApiOperation(value = "新增書本", notes="新增書本")
	@ApiImplicitParams(value = @ApiImplicitParam(
			name = "書本內容",
			required = true,
			value="輸入預新增的書本內容",
			paramType = "body",
			dataTypeClass = Book.class
	))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "新增書本成功")
	})
	@PutMapping("/api/book")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}
	
	@ApiOperation(value = "以作者查詢書本", notes="以作者查詢書本")
	@ApiImplicitParams(value = @ApiImplicitParam(
			name = "author",
			required = true,
			value="輸入作者",
			paramType = "path",
			dataTypeClass = String.class
	))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "取得書本"),
			@ApiResponse(code = 404, message = "查無書本")
	})
	@GetMapping("/api/book/author/{author}")
	public List<Book> getBookByAuthor(@PathVariable String author) {
		return bookService.getBookByAuthor(author);
	}
	
	@ApiOperation(value = "以語言查詢書本", notes="以語言查詢書本")
	@ApiImplicitParams(value = @ApiImplicitParam(
			name = "language",
			required = true,
			value="輸入語言",
			paramType = "path",
			dataTypeClass = String.class
	))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "取得書本"),
			@ApiResponse(code = 404, message = "查無書本")
	})
	@GetMapping("/api/book/lang/{language}")
	public List<Book> getBookByLanguage(@PathVariable String language){
		return bookService.getBookByLanguage(language) ;
	}
	
	@ApiOperation(value = "以書名關鍵字查詢書本", notes="以書名關鍵字查詢書本")
	@ApiImplicitParams(value = @ApiImplicitParam(
			name = "title",
			required = true,
			value="輸入書名關鍵字",
			paramType = "path",
			dataTypeClass = String.class
	))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "取得書本"),
			@ApiResponse(code = 404, message = "查無書本")
	})
	@GetMapping("/api/book/title/{title}")
	public List<Book> getBookTitleLike(@PathVariable String title){
		return bookService.getBookTitleLike(title);
	}
	
	@ApiOperation(value = "以作者與書名關鍵字查詢書本", notes="以作者與書名關鍵字查詢書本")
	@ApiImplicitParams(value = @ApiImplicitParam(
			name = "作者與與書名關鍵字",
			required = true,
			value="輸入作者與書名關鍵字",
			paramType = "body",
			dataTypeClass = String.class
	))
	@PostMapping("/api/book/")
	public List<Book> getBookByAuthorAndTitle(@RequestBody AuthorTitle condition) {
		String author = condition.author ;
		String title = condition.title ;
		return bookService.getBookByAuthorAndTitle(author, title) ;
	}
	
}
@ApiModel(value = "作者與書名")
class AuthorTitle{
	@ApiModelProperty(value = "作者")
	public String author ;
	@ApiModelProperty(value = "書名")
	public String title ;
}