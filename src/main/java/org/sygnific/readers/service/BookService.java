package org.sygnific.readers.service;

import java.util.List;
import org.sygnific.readers.model.Book;
import org.sygnific.readers.repository.BookRepository;

public class BookService {
	
	BookRepository repository = new BookRepository();
	
	public List<String> getBooksByAuthor(String author) {
		return repository.getBooksByAuthor(author);
	}

	public List<String> getBooksByGenre(String genre) {
		return repository.getBooksByGenre(genre);
	}

	public List<String> getBooksByName(String name) {
		return repository.getBooksByName(name);
	}

	public List<String> getBooksById(String id) {
		int userId = Integer.parseInt(id);
		return repository.getBooksById(userId);
	}

	public List<String> getAllBooks() {
		return repository.getAllBooks();
	}

	public boolean removeBooks(int id) {
		if(repository.availableBooks()) {
			repository.removeBook(id);
		}
		return false;
	}

	public boolean updateBooks(int id, Book book) {
		if(repository.availableBooks()) {
			repository.updateBooks(id,book);
			return true;
		}
		return false;
	}

	public Book addBook(Book book) {
		return repository.newBook(book);
	}

	public boolean assignBook(int userId, int bookId) {
		if(repository.isAvailable(bookId,userId)) {
			repository.assignBook(userId,bookId); 
			return true;
		}
		return false;
	}

}
