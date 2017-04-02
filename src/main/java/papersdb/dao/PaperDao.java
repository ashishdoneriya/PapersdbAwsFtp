package papersdb.dao;

import java.util.List;

import papersdb.model.Paper;
import papersdb.model.Subject;

public interface PaperDao {
	
	void save(Paper paper);
	
	void delete(Paper paper);
	
	Paper get(int paperID);
	
	List<Paper> list();
	
	List<Paper> list(Subject subject);

	List<Paper> list(List<Subject> subjects);

	List<Paper> list(String subjectID);

	void update(Paper paper);
}
