package epam.cdp.spring.service.impl.tx;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epam.cdp.spring.bean.Ticket;
import epam.cdp.spring.bean.TicketCategory;
import epam.cdp.spring.bean.User;
import epam.cdp.spring.dao.FilterCriteria;
import epam.cdp.spring.dao.TicketDao;
import epam.cdp.spring.exception.TicketServiceException;
import epam.cdp.spring.service.TicketService;
import epam.cdp.spring.service.UserService;

@Service
@Transactional
public class TicketServiceTxImpl implements TicketService {

	private TicketDao ticketDao;

	private UserService userService;

	@Autowired
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void book(String ticketId, String login) {
		User user = userService.getUserByLogin(login);
		if (user == null) {
			throw new TicketServiceException("user with name: " + login + " does not exist.");
		}
		
		ticketDao.book(ticketId, user);
	}

	public Set<Ticket> getBookedTickets(String login, TicketCategory category, String title, Date date) {
		User user = userService.getUserByLogin(login);
		if (user == null) {
			throw new TicketServiceException("user with name: " + login + " does not exist.");
		}
		FilterCriteria criteria = new FilterCriteria();
		criteria.setCategory(category);
		criteria.setDate(date);
		criteria.setTitle(title);
		return ticketDao.getBookedTickets(user, criteria);
	}

	@Override
	public Set<Ticket> getAvailableTickets(TicketCategory category, String title, Date date) {
		FilterCriteria criteria = new FilterCriteria();
		criteria.setCategory(category);
		criteria.setDate(date);
		criteria.setTitle(title);
		return ticketDao.getAvailableTickets(criteria);
	}

	@Override
	public Set<Ticket> getAvailableTickets() {
		return ticketDao.getAvailableTickets(new FilterCriteria());
	}

	@Override
	public Set<Ticket> getBookedTickets(String login) {
		User user = userService.getUserByLogin(login);
		if (user == null) {
			throw new TicketServiceException("user with name: " + login + " does not exist.");
		}
		
		return ticketDao.getBookedTickets(user, new FilterCriteria());
	}
}
