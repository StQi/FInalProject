package com.shentong.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shentong.model.User;

@Repository("userDao")
public class UserDAOImp implements UserDAO {

	static final Logger logger = LoggerFactory.getLogger(UserDAOImp.class);

	@Autowired
	private SessionFactory sessionFactory;

	public UserDAOImp() {
	}

	public UserDAOImp(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<User> findAllUsers() {
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) sessionFactory.getCurrentSession().createCriteria(User.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listUser;
	}

	@Override
	@Transactional
	public void save(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Override
	@Transactional
	public void deleteByUsername(String username) {
		User userToDelete = new User();
		userToDelete.setUserName(username);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	@Override
	@Transactional
	public User findByUsername(String username) {
		String hql = "from User where UserName= '" + username+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) query.list();
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		}
		return null;

	}

//	@Override
//	public void updateUser(User user) {
//		// TODO Auto-generated method stub
//		
//	}

}
