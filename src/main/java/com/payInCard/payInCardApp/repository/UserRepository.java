package com.payInCard.payInCardApp.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.payInCard.payInCardApp.payload.User;

@Service
public class UserRepository {
	
	private List<User> userList = new ArrayList<User>();
	
	public List<User> getUserList() {
		return userList;
	}
	
	public void addUser(User user) {
		userList.add(user);
	}
	
	public User getUserByUserName(String username) {
		Iterator<User> iterator = userList.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();			
			if(user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}
}
