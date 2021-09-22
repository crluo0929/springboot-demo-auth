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
import com.example.vo.NamePass;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterStyle;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "書本相關API")
public class BookController {

	@Autowired BookService bookService ;
	
	@Operation(summary= "列出所有書本", description="列出所有書本")
	@ApiResponses(value = {
			@ApiResponse(responseCode= "200", description  = "列出所有書本")
	})
	@GetMapping("/api/book/list")
	public List<Book> listBooks(){
		return bookService.listBooks() ;
	}
	
	@Operation(summary = "以ISBN號碼查詢書本", description="以ISBN號碼查詢書本")
	@Parameters(value = @Parameter(
			name = "isbn",
			required = true,
			description = "輸入ISBN號碼",
			style = ParameterStyle.SIMPLE
	))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "取得書本"),
			@ApiResponse(responseCode = "404", description = "查無書本")
	})
	@GetMapping("/api/book/isbn/{isbn}")
	public Book getBookByIsbn(@PathVariable String isbn) {
		return bookService.getBookByIsbn(isbn);
	}
	
	@Operation(summary = "新增書本", description="新增書本")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "新增書本成功")
	})
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			description = "輸入預新增的書本內容",
			content = @Content(schema = @Schema(allOf = Book.class) ) 
	)
	@PutMapping("/api/book")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}
	
	@Operation(summary = "以作者查詢書本", description="以作者查詢書本")
	@Parameters(value = @Parameter(
			name = "author",
			required = true,
			description="輸入作者",
			style = ParameterStyle.SIMPLE
	))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "取得書本"),
			@ApiResponse(responseCode = "404", description = "查無書本")
	})
	@GetMapping("/api/book/author/{author}")
	public List<Book> getBookByAuthor(@PathVariable String author) {
		return bookService.getBookByAuthor(author);
	}
	
	@Operation(summary = "以語言查詢書本", description="以語言查詢書本")
	@Parameters(value = @Parameter(
			name = "language",
			required = true,
			description="輸入語言",
			style = ParameterStyle.SIMPLE
	))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "取得書本"),
			@ApiResponse(responseCode = "404", description = "查無書本")
	})
	@GetMapping("/api/book/lang/{language}")
	public List<Book> getBookByLanguage(@PathVariable String language){
		return bookService.getBookByLanguage(language) ;
	}
	
	@Operation(summary = "以書名關鍵字查詢書本", description="以書名關鍵字查詢書本")
	@Parameters(value = @Parameter(
			name = "title",
			required = true,
			description="輸入書名關鍵字",
			style = ParameterStyle.SIMPLE
	))
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "取得書本"),
			@ApiResponse(responseCode = "404", description = "查無書本")
	})
	@GetMapping("/api/book/title/{title}")
	public List<Book> getBookTitleLike(@PathVariable String title){
		//用來測試打API失敗的情境
		if(title!=null && title.indexOf("64")>=0) throw new RuntimeException("出現了不合法的可怕關鍵字");
		return bookService.getBookTitleLike(title);
	}
	
	@Operation(summary = "以作者與書名關鍵字查詢書本", description="以作者與書名關鍵字查詢書本")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(
			required = true,
			description = "輸入作者與書名關鍵字",
			content = @Content(schema = @Schema(allOf = AuthorTitle.class) ) 
	)
	@PostMapping("/api/book/")
	public List<Book> getBookByAuthorAndTitle(@RequestBody AuthorTitle condition) {
		String author = condition.author ;
		String title = condition.title ;
		return bookService.getBookByAuthorAndTitle(author, title) ;
	}
	
}
@Schema(title="AuthorTitle",description = "作者與書名")
class AuthorTitle{
	@Schema(description = "作者")
	public String author ;
	@Schema(description = "書名")
	public String title ;
}