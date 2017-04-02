package papersdb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import papersdb.dao.UserDao;
import papersdb.model.User;

@Transactional
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session session;
    
	@Override
	public void save(User user) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	@Override
	public void delete(User user) {
		session = sessionFactory.getCurrentSession();
		session.delete(user);
	}

	@Override
	public User get(String email) {
		session = sessionFactory.getCurrentSession();
		return (User) session.get(User.class, email);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public User get(String email, String password) {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(User.class);
		cr.add(Restrictions.eq("email", email));
		cr.add(Restrictions.eq("password", password));
		List<User> list = cr.list();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> list() {
		session = sessionFactory.getCurrentSession();
		return session.createCriteria(User.class).list();
	}

}
