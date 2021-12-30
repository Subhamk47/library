package org.sygnific.readers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "USER_NAME")
	private String name;
	
	@Column(name = "USER_IND")
	private char userInd;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getUserInd() {
		return userInd;
	}
	public void setUserInd(char userInd) {
		this.userInd = userInd;
	}
	
	
}
