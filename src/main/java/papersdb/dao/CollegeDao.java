package papersdb.dao;

import java.util.List;

import papersdb.model.College;

public interface CollegeDao {
	
	int save(College college);
	
	void delete(College college);
	
	College get(int collegeID);
	
	College get(String name);
	
	List<College> list();

}
