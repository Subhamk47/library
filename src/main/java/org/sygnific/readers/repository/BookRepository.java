package org.sygnific.readers.repository;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.glassfish.jersey.inject.hk2.Hk2RequestScope.Instance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sygnific.readers.model.Book;
import org.sygnific.readers.model.UserHistory;
import org.sygnific.readers.model.Users;

public class BookRepository {
	
	SessionFactory factory = new Configuration().configure().addAnnotatedClass(Book.class).buildSessionFactory();
	
	Session session = factory.getCurrentSession();
	
	
	public List<String> getBooksByAuthor(String author) {
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Book where author = :value");
			query.setParameter("value", author);
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			session.close();
		}
	}

	public List<String> getBooksByGenre(String genre) {
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Book where genre = :value");
			query.setParameter("value", genre);
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			session.close();
		}
	}

	public List<String> getBooksByName(String name) {
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Book where name = :value");
			query.setParameter("value", name);
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			session.close();
		}
	}

	public List<String> getBooksById(int userId) {
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Book where id = :value");
			query.setParameter("value", userId);
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			session.close();
		}
	}

	public List<String> getAllBooks() {

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Book");
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			session.close();
		}
	
	}

	public boolean availableBooks() {

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Book where avlInd = 'Y'");
			List<Book> list = query.getResultList();
			if(list.size()>0) return true;
		}catch (Exception e) {
			return false;
		}finally {
			session.close();
		}
	
		return false;
	}

	public void removeBook(int id) {

		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from Book where id = :bookId");
			query.setParameter("bookId", id);
			query.executeUpdate();
			session.getTransaction().commit();
		}catch (Exception e) {
			return ;
		}finally {
			session.close();
		}
	
	}

	public void updateBooks(int id, Book book) {

		try {
			session.beginTransaction();
			Book books = session.get(Book.class, id);
			books.setAuthor(book.getAuthor());
			books.setAvlInd(book.getAvlInd());
			books.setGenre(book.getGenre());
			books.setId(book.getId());
			books.setName(book.getName());
			session.update(books);
			session.getTransaction().commit();
		}catch (Exception e) {
			return ;
		}finally {
			session.close();
		}
	
	}

	public Book newBook(Book book) {
		try {
			session.beginTransaction();
			session.save(book);
			session.getTransaction().commit();
			return book;
		}catch (Exception e) {
			return null;
		}finally {
			session.close();
		}
	}

	public boolean isAvailable(int bookId, int userId) {
		try {
			session.beginTransaction();
			Book book = session.get(Book.class, bookId);
			Users user = session.get(Users.class, userId);
			if(book.getAvlInd()=='Y' && user.getUserInd()=='N') {
				return true;
			}
		}catch (Exception e) {
			return false;
		}finally {
			session.close();
		}
		return false;
	}

	public void assignBook(int userId, int bookId) {

		try {
			session.beginTransaction();
			Book book = session.get(Book.class, bookId);
			Users user = session.get(Users.class, userId);
			book.setAvlInd('N');
			user.setUserInd('Y');
			session.update(book);
			session.update(user);
			UserHistory history = new UserHistory();
			history.setUserId(userId);
			history.setBookId(bookId);
			history.setIssueDate(Timestamp.from(Instant.now()));
			session.getTransaction().commit();
		}catch (Exception e) {
			return ;
		}finally {
			session.close();
		}
	}


}
