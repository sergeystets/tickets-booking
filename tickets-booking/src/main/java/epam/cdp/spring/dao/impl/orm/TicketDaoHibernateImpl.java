package epam.cdp.spring.dao.impl.orm;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.isNull;

import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import epam.cdp.spring.bean.Ticket;
import epam.cdp.spring.bean.User;
import epam.cdp.spring.dao.FilterCriteria;
import epam.cdp.spring.dao.TicketDao;
import epam.cdp.spring.dao.impl.util.HibernateQueryBuilder;

@Repository
public class TicketDaoHibernateImpl implements TicketDao {

	@Autowired
	private SessionFactory sessionFactory;

	private HibernateQueryBuilder queryBuilder;

	public TicketDaoHibernateImpl() {
		queryBuilder = new HibernateQueryBuilder();
	}

	@Override
	public void book(String ticketId, User user) {
		Ticket ticket = getTicket(ticketId);
		if (ticket == null) {
			throw new RuntimeException("ticket with id: " + ticketId + " does not exists");
		}

		ensureTicketIsNotAlreadyBooked(ticket, user);
		ticket.setUser(user);
		sessionFactory.getCurrentSession().update(ticket);

	}

	private void ensureTicketIsNotAlreadyBooked(Ticket ticket, User user) {
		User currentOwner = ticket.getUser();
		if (currentOwner != null && !currentOwner.equals(user)) {
			throw new RuntimeException("ticket with id: " + ticket.getId() + " is alreay booked.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Ticket> getBookedTickets(User user, FilterCriteria filter) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = queryBuilder.build(filter, session, Ticket.class, eq("user", user));
		return new TreeSet<Ticket>(criteria.list());

	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<Ticket> getAvailableTickets(FilterCriteria filter) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = queryBuilder.build(filter, session, Ticket.class, isNull("user"));
		return new TreeSet<Ticket>(criteria.list());
	}

	private Ticket getTicket(String id) {
		return (Ticket) sessionFactory.getCurrentSession().get(Ticket.class, id);
	}

}
