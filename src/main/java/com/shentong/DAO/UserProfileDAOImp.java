package com.shentong.DAO;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shentong.model.User;
import com.shentong.model.UserProfile;

@Repository("userProfileDao")
public class UserProfileDAOImp implements UserProfileDAO{

	
	@Autowired
	private SessionFactory sessionFactory;

	//I delete the method findByType and findAll



	@Override
	@Transactional
	public UserProfile findByUsername(String username) {
		String hql = "from UserProfile where UserName= '" + username+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<UserProfile> listUserProfile = (List<UserProfile>) query.list();
		if (listUserProfile != null && !listUserProfile.isEmpty()) {
			return listUserProfile.get(0);
		}
		return null;

	}

	@Override
	@Transactional
	public List<UserProfile> findAllUserProfiles() {
		@SuppressWarnings("unchecked")
		List<UserProfile> listUserProfile = (List<UserProfile>) sessionFactory.getCurrentSession().createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUserProfile;
	}

	@Override
	@Transactional
	public void save(UserProfile userProfile) {
		sessionFactory.getCurrentSession().saveOrUpdate(userProfile);
		
	}

	@Override
	@Transactional
	public void delete(String username) {
		UserProfile userToDelete = new UserProfile();
		userToDelete.setUserName(username);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}
	}


