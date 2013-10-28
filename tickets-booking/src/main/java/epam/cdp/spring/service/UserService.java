package epam.cdp.spring.service;

import epam.cdp.spring.bean.User;

public interface UserService {

	public User login(String login, String password);

	public User register(User user);
	
	public User getUserByLogin(String login);

}
