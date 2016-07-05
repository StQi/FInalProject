package com.shentong.DAO;

import java.util.*;

import com.shentong.model.User;
import com.shentong.model.UserProfile;

public interface UserDAO {
	List<User> findAllUsers();

	User findByUsername(String UserName);

	void save(User user);
	
	//void updateUser(User user);

	void deleteByUsername(String UserName);

	
}