package epam.cdp.spring.task1.service.impl.tx;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import epam.cdp.spring.task1.bean.Ticket;
import epam.cdp.spring.task1.bean.TicketCategory;
import epam.cdp.spring.task1.dao.FilterCriteria;
import epam.cdp.spring.task1.dao.TicketDao;
import epam.cdp.spring.task1.service.TicketService;
import epam.cdp.spring.task1.service.UserService;

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
		if (!userService.isUserExists(login)) {
			throw new RuntimeException("user with name: " + login + " does not exist.");
		}
		ticketDao.book(ticketId, login);
	}

	public Set<Ticket> getBookedTickets(String login, TicketCategory category, String title, Date date) {
		if (!userService.isUserExists(login)) {
			throw new RuntimeException("user with login: " + login + "does not exist");
		}
		FilterCriteria criteria = new FilterCriteria();
		criteria.setCategory(category);
		criteria.setDate(date);
		criteria.setTitle(title);
		return ticketDao.getBookedTickets(login, criteria);
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

	public Set<Ticket> getBookedTickets(String login) {
		if (!userService.isUserExists(login)) {
			throw new RuntimeException("user with login: " + login + "does not exist");
		}
		
		return ticketDao.getBookedTickets(login, new FilterCriteria());
	}
}
