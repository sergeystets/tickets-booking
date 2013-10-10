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
		User u0 = new User("user", "user");
		User u1 = new User("admin", "admin");
		users.put(u0.getLogin(), u0);
		users.put(u1.getLogin(), u1);
	}

	public synchronized boolean isUserExists(String userName) {
		return users.containsKey(userName);
	}

	@Override
	public synchronized User login(String login, String password) {
		if (users.get(login) == null) {
			return null;
		}
		User user = users.get(login);
		if (user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public User register(User user) throws Exception {
		String login = user.getLogin();
		if (users.containsKey(login)) {
			throw new Exception("user already exists");
		}
		users.put(login, user);
		return user;
	}
}