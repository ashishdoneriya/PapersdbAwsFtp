package papersdb.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import papersdb.dao.SubjectDao;
import papersdb.model.Branch;
import papersdb.model.Subject;

@Transactional
@Repository("subjectDao")
public class SubjectDaoImpl implements SubjectDao {
	
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session session;

	@Override
	public int save(Subject subject) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(subject);
		return subject.getSubjectID();
	}

	@Override
	public void delete(Subject subject) {
		session = sessionFactory.getCurrentSession();
		session.delete(subject);
	}

	@Override
	public Subject get(int subjectID) {
		session = sessionFactory.getCurrentSession();
		return (Subject) session.get(Subject.class, subjectID);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public Subject get(String name) {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Subject.class);
		cr.add(Restrictions.eq("name", name));
		List<Subject> list = cr.list();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Subject> list() {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Subject.class);
		return cr.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Subject> list(String branchID) {
		try {
			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(Subject.class);
			cr.add(Restrictions.eq("branch.branchID", Integer.parseInt(branchID)));
			return cr.list();
		} catch (NumberFormatException e) {
			return new ArrayList<Subject>();
		}
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Subject> list(Branch branch) {
		if (branch == null) {
			return new ArrayList<Subject>();
		}
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Subject.class);
		cr.add(Restrictions.eq("branch", branch));
		return cr.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Subject> list(List<Branch> branches) {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Subject.class);
		cr.add(Restrictions.in("branch", branches));
		return cr.list();
	}
}
