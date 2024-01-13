package com.example.springdemo.service;

import com.example.springdemo.entities.User;
import java.util.List;

public interface UserService {
	User findByUsername(String userName);
	User getUserById(Integer userId);
	int saveUser(User user);

	List<User> listUsers(User user);

	int updateUser(Integer userId, User user);

	int removeUser(Integer userId);
}
