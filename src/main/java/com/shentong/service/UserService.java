package com.shentong.service;

import java.util.List;

import com.shentong.model.User;

public interface UserService {
	User findByUsername(String username);

	void saveUser(User user);

	void updateUser(User user);

	List<User> findAllUsers();

	void deleteUserByUsername(String username);

}
