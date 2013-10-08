package epam.cdp.spring.task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epam.cdp.spring.task1.dao.UserDao;

@Service
public class UserService {

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public boolean isUserExists(String userName) {
		return userDao.isUserExists(userName);
	}
}