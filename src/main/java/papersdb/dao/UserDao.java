package papersdb.dao;

import java.util.List;

import papersdb.model.User;

public interface UserDao {
	
	void save(User user);
	
	void delete(User user);
	
	User get(String email);
	
	User get(String email, String password);
	
	List<User> list();

	
}
