package com.example.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(title="Book", description="書")
@Entity
public class Book {

	@Schema(description = "id")
	@Id @GeneratedValue(strategy = GenerationType.AUTO)	
	private int id ;
	@Schema(description = "標題")
	private String title ;
	@Schema(description = "ISBN編號")
	private String isbn ;
	@Schema(description = "作者")
	private String author ;
	@Schema(description = "語言")
	private String lang ;
	@Schema(description = "發行日")
	@DateTimeFormat(pattern="yyyy-MM-dd",iso = ISO.DATE)
//	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Shanghai")	
	private Date publish ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Date getPublish() {
		return publish;
	}
	public void setPublish(Date publish) {
		this.publish = publish;
	}
	
}
