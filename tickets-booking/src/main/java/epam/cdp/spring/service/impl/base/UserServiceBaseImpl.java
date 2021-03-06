package epam.cdp.spring.service.impl.base;

import org.springframework.beans.factory.annotation.Autowired;

import epam.cdp.spring.bean.User;
import epam.cdp.spring.dao.UserDao;
import epam.cdp.spring.exception.UserServiceException;
import epam.cdp.spring.service.UserService;

//@Service
public class UserServiceBaseImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User isUserExists(String login) {
		return userDao.getUserByLogin(login);
	}

	public User login(String login, String password) {
		return userDao.login(login, password);
	}

	public User register(User user){
		String login = user.getLogin();
		if (getUserByLogin(login)!= null) {
			throw new UserServiceException("user already exists");
		}
		return userDao.register(user);
	}

	@Override
	public User getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}
}
