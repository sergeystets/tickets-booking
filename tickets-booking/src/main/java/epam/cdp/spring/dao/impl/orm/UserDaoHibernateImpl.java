package epam.cdp.spring.dao.impl.orm;

import static org.hibernate.criterion.Restrictions.conjunction;
import static org.hibernate.criterion.Restrictions.eq;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import epam.cdp.spring.bean.User;
import epam.cdp.spring.dao.UserDao;

@Repository
public class UserDaoHibernateImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean isUserExists(String login) {
		User user = (User) sessionFactory.getCurrentSession().get(User.class, login);
		return user != null;
	}

	@Override
	public User login(String login, String password) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(conjunction().add(eq("login", login)).add(eq("password", password)));

		@SuppressWarnings("unchecked")
		List<User> user = criteria.list();
		if (user == null || user.isEmpty()) {
			return null;
		}

		return user.get(0);
	}

	@Override
	public User register(User user) {
		sessionFactory.getCurrentSession().save(user);
		return user;
	}
}
