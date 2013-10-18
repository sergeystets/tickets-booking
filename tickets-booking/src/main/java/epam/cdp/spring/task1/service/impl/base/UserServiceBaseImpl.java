package epam.cdp.spring.task1.service.impl.base;

import org.springframework.beans.factory.annotation.Autowired;

import epam.cdp.spring.task1.bean.User;
import epam.cdp.spring.task1.dao.UserDao;
import epam.cdp.spring.task1.exception.UserServiceException;
import epam.cdp.spring.task1.service.UserService;

//@Service
public class UserServiceBaseImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean isUserExists(String login) {
		return userDao.isUserExists(login);
	}

	public User login(String login, String password) {
		return userDao.login(login, password);
	}

	public User register(User user){
		String login = user.getLogin();
		if (isUserExists(login)) {
			throw new UserServiceException("user already exists");
		}
		return userDao.register(user);
	}
}
