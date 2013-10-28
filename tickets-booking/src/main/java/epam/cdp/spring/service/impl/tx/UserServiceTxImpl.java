package epam.cdp.spring.service.impl.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epam.cdp.spring.bean.User;
import epam.cdp.spring.dao.UserDao;
import epam.cdp.spring.exception.UserServiceException;
import epam.cdp.spring.service.UserService;

@Service
@Transactional
public class UserServiceTxImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User getUserByLogin(String login) {
		return userDao.getUserByLogin(login);
	}

	public User login(String login, String password) {
		return userDao.login(login, password);
	}

	public User register(User user){		
		if (getUserByLogin(user.getLogin()) != null) {
			throw new UserServiceException("user with login: " + user.getLogin() + "already exists");
		}
		return userDao.register(user);
	}
}