package epam.cdp.spring.task1.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import epam.cdp.spring.task1.bean.User;

@Repository
public class UserDaoInMemoryImpl implements UserDao {

	private Map<String, User> users;

	public UserDaoInMemoryImpl() {
		users = new HashMap<String, User>();
		User u0 = new User("user");
		User u1 = new User("admin");
		users.put(u0.getName(), u0);
		users.put(u1.getName(), u1);
	}

	public synchronized boolean isUserExists(String userName) {
		return users.containsKey(userName);
	}
}
