package com.mmgo.cashlessmap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmgo.cashlessmap.entity.User;
import com.mmgo.cashlessmap.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userrepository;
	
	public void save(User user) {
		userrepository.save(user);
	}
	
	public Boolean find(String username) {
		String result = userrepository.findByUsername(username);
		if(username.equals(result)) {
			return true;
		}else {
			return false;
		}
	}
}
