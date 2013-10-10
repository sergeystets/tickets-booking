package epam.cdp.spring.task1.dao;

import epam.cdp.spring.task1.bean.User;

public interface UserDao {

	boolean isUserExists(String login);

	User login(String login, String password);
	
	User register(User user) throws Exception;
}