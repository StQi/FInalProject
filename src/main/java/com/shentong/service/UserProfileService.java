package com.shentong.service;

import java.util.List;

import com.shentong.model.UserProfile;

public interface UserProfileService {
	UserProfile findByUsername(String username);
	void save(UserProfile userProfile);

	void delete(String username);

	//List<UserRole> findAll();
}
