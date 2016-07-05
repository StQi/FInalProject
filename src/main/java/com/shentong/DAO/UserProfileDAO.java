package com.shentong.DAO;


import java.util.List;

import com.shentong.model.UserProfile;

public interface UserProfileDAO {
	

	UserProfile findByUsername(String username);

	List<UserProfile> findAllUserProfiles();

	void save(UserProfile userProfile);

	void delete(String username);

}
