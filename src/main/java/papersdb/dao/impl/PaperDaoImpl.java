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

import papersdb.dao.PaperDao;
import papersdb.model.Paper;
import papersdb.model.Subject;

@Transactional
@Repository("paperDao")
public class PaperDaoImpl implements PaperDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    private Session session;
    
    @Override
    public void save(Paper paper) {
    	session = sessionFactory.getCurrentSession();
        session.save(paper);
    }
    
    @Override
    public void update(Paper paper) {
    	session = sessionFactory.getCurrentSession();
    	session.update(paper);
    }
    
    @Override
    public void delete(Paper paper) {
        session = sessionFactory.getCurrentSession();
        session.delete(paper);
    }

	@Override
    public Paper get(int paperID) {
		session = sessionFactory.getCurrentSession();
		return (Paper) session.get(Paper.class, paperID);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Paper> list() {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Paper.class);
		return cr.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Paper> list(String subjectID) {
		try {
			session = sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(Paper.class);
			cr.add(Restrictions.eq("subject.subjectID", Integer.parseInt(subjectID)));
			return cr.list();
		} catch (NumberFormatException e) {
			return new ArrayList<Paper>();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Paper> list(Subject subject) {
		if (subject == null) {
			return new ArrayList<Paper>();
		}
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Paper.class);
		cr.add(Restrictions.eq("subject", subject));
		return cr.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Paper> list(List<Subject> subjects) {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Paper.class);
		cr.add(Restrictions.in("subject", subjects));
		return cr.list();
	}
}
