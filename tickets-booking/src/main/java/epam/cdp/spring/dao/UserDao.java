package epam.cdp.spring.dao;

import epam.cdp.spring.bean.User;

public interface UserDao {

	User getUserByLogin(String login);

	User login(String login, String password);
	
	User register(User user);
}