package com.shentong.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shentong.DAO.UserProfileDAO;
import com.shentong.model.UserProfile;


@Service("userProfileService")
@Transactional
public class UserProfileServiceImp implements UserProfileService{

	@Autowired
    UserProfileDAO dao;
	
	

	
	public UserProfile findByUsername(String username) {
		
		return dao.findByUsername(username);
	}




	public void save(UserProfile userProfile) {
		userProfile.setFirstName(userProfile.getFirstName());
		userProfile.setLastName(userProfile.getLastName());
		userProfile.setPhoneNum(userProfile.getPhoneNum());
		userProfile.setAddress(userProfile.getAddress());
		userProfile.setEmail(userProfile.getEmail());
        dao.save(userProfile);
	}





	public void delete(String username) {
		dao.delete(username);
		
	}


}
