package epam.cdp.spring.dao;

import epam.cdp.spring.bean.User;

public interface UserDao {

	boolean isUserExists(String login);

	User login(String login, String password);
	
	User register(User user);
}