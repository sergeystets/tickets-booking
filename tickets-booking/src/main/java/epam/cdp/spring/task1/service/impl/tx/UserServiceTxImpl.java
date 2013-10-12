package epam.cdp.spring.task1.service.impl.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epam.cdp.spring.task1.bean.User;
import epam.cdp.spring.task1.dao.UserDao;
import epam.cdp.spring.task1.service.UserService;

@Service
@Transactional
public class UserServiceTxImpl implements UserService {

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

	public User register(User user) throws Exception {
		return userDao.register(user);
	}
}