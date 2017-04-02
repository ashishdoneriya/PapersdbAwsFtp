package papersdb.dao;

import java.util.List;

import papersdb.model.PaperSubmitRequest;

public interface PaperSubmitRequestDao {
	
	int save(PaperSubmitRequest paperSubmitRequest);
	
	void delete(PaperSubmitRequest paperSubmitRequest);
	
	PaperSubmitRequest get(int requestID);
	
	List<PaperSubmitRequest> list();

}
