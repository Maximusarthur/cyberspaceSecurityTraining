package com.example.springdemo.service.impl;

import com.example.springdemo.mapper.UserMapper;
import com.example.springdemo.entities.User;
import com.example.springdemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public User findByUsername(String userName) {
		return userMapper.findByUsername(userName);
	}

	@Override
	public User getUserById(Integer userId){
		return userMapper.getUserById(userId);
	}
	
	@Override
	public int saveUser(User user){
		return userMapper.saveUser(user);
	}

	@Override
	public List<User> listUsers(User user){
		return userMapper.listUsers(user);
	}

	@Override
	public int updateUser(Integer userId, User user){
		return userMapper.updateUser(userId, user);
	}

	@Override
	public int removeUser(Integer userId){
		return userMapper.removeUser(userId);
	}
}
