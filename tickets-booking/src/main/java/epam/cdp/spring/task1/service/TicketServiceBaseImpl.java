package epam.cdp.spring.task1.service;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epam.cdp.spring.task1.bean.Ticket;
import epam.cdp.spring.task1.bean.TicketCategory;
import epam.cdp.spring.task1.dao.FilterCriteria;
import epam.cdp.spring.task1.dao.TicketDao;

@Service
public class TicketServiceBaseImpl implements TicketService {

	private TicketDao ticketDao;

	private UserServiceBaseImpl userService;

	@Autowired
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	@Autowired
	public void setUserService(UserServiceBaseImpl userService) {
		this.userService = userService;
	}

	public void book(String ticketId, String userName) {
		if (!userService.isUserExists(userName)) {
			throw new RuntimeException("user with name: " + userName + " does not exist.");
		}
		ticketDao.book(ticketId, userName);
	}

	public Set<Ticket> getBookedTickets(String userName, FilterCriteria criteria) {
		if (!userService.isUserExists(userName)) {
			throw new RuntimeException("user with name: " + userName + " does not exist.");
		}
		return ticketDao.getBookedTickets(userName, criteria);
	}

	@Override
	public Set<Ticket> getAvailableTickets(TicketCategory category, String title, Date date) {
		FilterCriteria criteria = new FilterCriteria();
		criteria.setCategory(category);
		criteria.setTitle(title);
		criteria.setDate(date);		
		return ticketDao.getAvailableTickets(criteria);
	}

	@Override
	public Set<Ticket> getAvailableTickets() {
		FilterCriteria criteria = new FilterCriteria();
		return ticketDao.getAvailableTickets(criteria);
		
	}
}