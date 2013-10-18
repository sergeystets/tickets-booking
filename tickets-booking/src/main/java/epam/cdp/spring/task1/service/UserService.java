package epam.cdp.spring.task1.service;

import epam.cdp.spring.task1.bean.User;

public interface UserService {

	public boolean isUserExists(String login);

	public User login(String login, String password);

	public User register(User user);

}
