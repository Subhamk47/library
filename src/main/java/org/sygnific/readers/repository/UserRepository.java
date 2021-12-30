package org.sygnific.readers.repository;

import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.sygnific.readers.model.Book;
import org.sygnific.readers.model.UserHistory;
import org.sygnific.readers.model.Users;

public class UserRepository {
	
	SessionFactory factory = new Configuration().configure().addAnnotatedClass(Book.class).buildSessionFactory();
	
	Session session = factory.getCurrentSession();
	
	public List<Users> getAllUsers() {
		try {
			session.beginTransaction();
			return session.createQuery("from Users").list();
		}catch (Exception e) {
		}finally {
			session.close();
		}
		return null;
	}

	public Users getUserById(int userId) {
		try {
			session.beginTransaction();
			return session.get(Users.class, userId);
		}catch (Exception e) {
		}finally {
			session.close();
		}
		return null;
	}

	public boolean addUsers(Users user) {

		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}catch (Exception e) {
		}finally {
			session.close();
		}
	
		return true;
	}

	public void removeUser(int userId) {

		try {
			session.beginTransaction();
			Query query = session.createQuery("delete from Users where userId = :value");
			query.setParameter("value", userId);
			query.executeUpdate();
			session.getTransaction().commit();
		}catch (Exception e) {
		}finally {
			session.close();
		}
	}

	public boolean isUserAvailabe(int id) {
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Users where userId = :value");
			query.setParameter("value", id);
			List<Users> list = query.getResultList();
			if(list.size()>0) return true;
		}catch (Exception e) {
		}finally {
			session.close();
		}
		return false;
	}

	public List<UserHistory> getHistoryOfUsers(int userId) {
		try {
			session.beginTransaction();
			Query query = session.createQuery("from UserHistory");
			return query.getResultList();
		} catch (Exception e) {
			System.out.println("Exception.....!");
		} finally {
			session.close();
		}
		return null;
	}

}
