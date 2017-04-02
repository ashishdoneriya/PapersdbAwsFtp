package papersdb.dao;

import java.util.List;

import papersdb.model.Branch;
import papersdb.model.Subject;

public interface SubjectDao {
	
	int save(Subject subject);
	
	void delete(Subject subject);
	
	Subject get(int subjectID);
	
	Subject get(String name);
	
	List<Subject> list();
	
	List<Subject> list(Branch branch);

	List<Subject> list(List<Branch> branches);

	List<Subject> list(String branchID);
}
