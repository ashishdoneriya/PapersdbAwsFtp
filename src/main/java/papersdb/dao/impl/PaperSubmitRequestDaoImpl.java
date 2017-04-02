package papersdb.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import papersdb.dao.PaperSubmitRequestDao;
import papersdb.model.PaperSubmitRequest;

@Transactional
@Repository("paperSubmitRequestDao")
public class PaperSubmitRequestDaoImpl implements PaperSubmitRequestDao {
	
    @Autowired
    private SessionFactory sessionFactory;
    
    private Session session;
    
	@Override
	public int save(PaperSubmitRequest paperSubmitRequest) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(paperSubmitRequest);
		return paperSubmitRequest.getRequestID();
	}

	@Override
	public void delete(PaperSubmitRequest paperSubmitRequest) {
		session = sessionFactory.getCurrentSession();
		session.delete(paperSubmitRequest);
	}

	@Override
	public PaperSubmitRequest get(int requestID) {
		session = sessionFactory.getCurrentSession();
		return (PaperSubmitRequest) session.get(PaperSubmitRequest.class, requestID);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PaperSubmitRequest> list() {
		session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(PaperSubmitRequest.class);
		return cr.list();
	}

}
