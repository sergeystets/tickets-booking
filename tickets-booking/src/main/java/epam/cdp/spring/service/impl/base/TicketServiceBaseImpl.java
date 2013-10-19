package epam.cdp.spring.service.impl.base;

import java.util.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import epam.cdp.spring.bean.Ticket;
import epam.cdp.spring.bean.TicketCategory;
import epam.cdp.spring.dao.FilterCriteria;
import epam.cdp.spring.dao.TicketDao;
import epam.cdp.spring.exception.TicketServiceException;
import epam.cdp.spring.service.TicketService;
import epam.cdp.spring.service.UserService;

//@Service
public class TicketServiceBaseImpl implements TicketService {

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

	public void book(String ticketId, String userName) {
		if (!userService.isUserExists(userName)) {
			throw new TicketServiceException("user with name: " + userName + " does not exist.");
		}
		ticketDao.book(ticketId, userName);
	}

	public Set<Ticket> getBookedTickets(String userName, TicketCategory category, String title, Date date) {
		if (!userService.isUserExists(userName)) {
			throw new TicketServiceException("user with name: " + userName + " does not exist.");
		}
		FilterCriteria criteria = new FilterCriteria();
		criteria.setCategory(category);
		criteria.setTitle(title);
		criteria.setDate(date);	
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
	
	@Override
	public Set<Ticket> getBookedTickets(String userName) {
		if (!userService.isUserExists(userName)) {
			throw new TicketServiceException("user with name: " + userName + " does not exist.");
		}
		
		return ticketDao.getBookedTickets(userName,new FilterCriteria());
	}

}