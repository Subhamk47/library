package org.sygnific.readers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Book")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "Book_Id")
	private int id;
	
	@Column(name = "Book_Name")
	private String name;
	
	@Column(name = "Book_Author")
	private String author;
	
	@Column(name = "Book_Genre")
	private String genre;
	
	@Column(name = "Book_Ind")
	private char avlInd;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public char getAvlInd() {
		return avlInd;
	}
	public void setAvlInd(char avlInd) {
		this.avlInd = avlInd;
	}
	
	
}
