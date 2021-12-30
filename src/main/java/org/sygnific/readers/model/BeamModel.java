package org.sygnific.readers.model;

import jakarta.ws.rs.QueryParam;

public class BeamModel {
	
	private @QueryParam("id") String id;
	private @QueryParam("name") String name;
	private @QueryParam("author") String author;
	private @QueryParam("genre") String genre;
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	
	
}
