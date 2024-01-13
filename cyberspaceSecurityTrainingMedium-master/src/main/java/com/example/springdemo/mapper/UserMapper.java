package com.example.springdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo.entities.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
	User getUserById(Integer userId);
	int saveUser(User user);
	List<User> listUsers(User user);
	int updateUser(Integer userId, User user);
	int removeUser(Integer userId);

	User findByUsername(String userName);
}
