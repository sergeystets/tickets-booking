package epam.cdp.spring.dao.impl.jdbc;

import static epam.cdp.spring.dao.impl.util.RowMapper.userMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import epam.cdp.spring.bean.User;
import epam.cdp.spring.dao.UserDao;

//@Repository
public class UserDaoJdbcImpl implements UserDao {

	private JdbcTemplate template;

	private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
	
	private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user WHERE login = ? and password = ?";

	private static final String INSERT_USER = "INSERT INTO User VALUES (?, ?)";

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public User getUserByLogin(String login) {
		List<User> users = template.query(FIND_USER_BY_LOGIN, userMapper(), login);
		if (users.isEmpty()) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public User login(String login, String password) {
		List<User> users = template.query(FIND_USER_BY_LOGIN_AND_PASSWORD, userMapper(), login, password);
		if (users.isEmpty()){
			return null;
		}
		return users.get(0);
	}

	@Override
	public User register(User user) {
		template.update(INSERT_USER, user.getLogin(), user.getPassword());
		return user;
	}
}
