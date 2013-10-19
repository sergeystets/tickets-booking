package epam.cdp.spring.service;

import epam.cdp.spring.bean.User;

public interface UserService {

	public boolean isUserExists(String login);

	public User login(String login, String password);

	public User register(User user);

}
