package papersdb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import papersdb.dao.CollegeDao;
import papersdb.model.College;

@Transactional
@Repository("collegeDao")
public class CollegeDaoImpl implements CollegeDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	@Override
	public int save(College college) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(college);
		return college.getCollegeID();
	}

	@Override
	public void delete(College college) {
		session = sessionFactory.getCurrentSession();
		session.delete(college);

	}

	@Override
	public College get(int collegeID) {
		session = sessionFactory.getCurrentSession();
		return (College) session.get(College.class, collegeID);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public College get(String name) {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(College.class);
		cr.add(Restrictions.eq("name", name));
		List<College> list = cr.list();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<College> list() {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(College.class);
		return cr.list();
	}

}
