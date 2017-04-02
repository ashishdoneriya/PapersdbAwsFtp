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

import papersdb.dao.BranchDao;
import papersdb.model.Branch;
import papersdb.model.College;

@Transactional
@Repository("branchDao")
public class BranchDaoImpl implements BranchDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session session;

	@Override
	public int save(Branch branch) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(branch);
		return branch.getBranchID();
	}

	@Override
	public void delete(Branch branch) {
		session = sessionFactory.getCurrentSession();
		session.delete(branch);
	}

	@Override
	public Branch get(int branchID) {
		session = sessionFactory.getCurrentSession();
		return (Branch) session.get(Branch.class, branchID);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Branch get(String name) {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Branch.class);
		cr.add(Restrictions.eq("name", name));
		List<Branch> list = cr.list();
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Branch> list() {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Branch.class);
		return cr.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Branch> list(String collegeID) {
		try {
			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(Branch.class);
			cr.add(Restrictions.eq("college.collegeID", Integer.parseInt(collegeID)));
			return cr.list();
		} catch (NumberFormatException e) {
			return new ArrayList<Branch>();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Branch> list(College college) {
		if (college == null) {
			return new ArrayList<Branch>();
		}
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Branch.class);
		cr.add(Restrictions.eq("college", college));
		return cr.list();
	}

	public static void main(String[] args) {
		Integer.parseInt("asdsad");
	}

}
