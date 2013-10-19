package epam.cdp.spring.dao.impl.map;

import java.util.HashMap;
import java.util.Map;

import epam.cdp.spring.bean.User;
import epam.cdp.spring.dao.UserDao;

//@Repository
public class UserDaoInMapImpl implements UserDao {

	private Map<String, User> users;

	public UserDaoInMapImpl() {
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
	public User register(User user) {
		users.put(user.getLogin(), user);
		return user;
	}
}