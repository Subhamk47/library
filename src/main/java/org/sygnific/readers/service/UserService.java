package org.sygnific.readers.service;

import java.util.List;

import org.sygnific.readers.model.UserHistory;
import org.sygnific.readers.model.Users;
import org.sygnific.readers.repository.UserRepository;

public class UserService {
	
	UserRepository DAO = new UserRepository();
	
	public List<Users> getAllUsers() {
		return DAO.getAllUsers();
	}

	public Users getUserById(int userId) {
		return DAO.getUserById(userId);
	}

	public boolean addUsers(Users user) {
		return DAO.addUsers(user);
	}

	public boolean removerUsers(int userId) {
		if(DAO.isUserAvailabe(userId)) {
			DAO.removeUser(userId);
		}
		return false;
	}

	public List<UserHistory> getUserHistory(int userId) {
		return DAO.getHistoryOfUsers(userId);
	}
	
}
