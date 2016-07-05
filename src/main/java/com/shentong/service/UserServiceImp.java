package com.shentong.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shentong.DAO.UserDAO;
import com.shentong.model.User;


@Service("userService")
@Transactional
public class UserServiceImp implements UserService{
	
	@Autowired
    private UserDAO dao;
	
   private static final AtomicLong counter = new AtomicLong();
	
	private static List<User> users;
	
	
	
	
	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}

	
	public void saveUser(User user) {
		user.setPassword(user.getPassword());
		user.setRole(user.getRole());
		user.setUserName(user.getUserName());
        dao.save(user);
		
	}

	
	public void updateUser(User user) {
		User entity = dao.findByUsername(user.getUserName());
        
           entity.setPassword(user.getPassword());
            entity.setRole(user.getRole());
            dao.save(user);
        
		
	}

	
	public List<User> findAllUsers() {
		List<User> users= dao.findAllUsers();
		return users;

	}

	
	


	public void deleteUserByUsername(String username) {
		dao.deleteByUsername(username);
		
	}

}
