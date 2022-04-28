package com.payInCard.payInCardApp.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyAuthService implements UserDetailsService {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	List<User> userList;
	
	public MyAuthService() {
		userList = new ArrayList<User>();
	}
	
	public void addUser(String username , String password) {
		User user = new User(username , passwordEncoder.encode(password) , new ArrayList<>());
		userList.add(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	for (User user : userList) {
		if(user.getUsername().equals(username)) {
			return user;
		}
	}
	
	throw new UsernameNotFoundException("Login yoki parol xato");
}

	
}
