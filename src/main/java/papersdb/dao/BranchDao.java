package papersdb.dao;

import java.util.List;

import papersdb.model.Branch;
import papersdb.model.College;

public interface BranchDao {

	int save(Branch branch);
	
	void delete(Branch branch);
	
	Branch get(int branchID);
	
	Branch get(String name);
	
	List<Branch> list();
	
	List<Branch> list(College college);

	List<Branch> list(String collegeID);
	
}
