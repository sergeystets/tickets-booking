package epam.cdp.spring.task1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import epam.cdp.spring.task1.bean.Ticket;
import epam.cdp.spring.task1.dao.FilterCriteria;
import epam.cdp.spring.task1.dao.TicketDao;

@Service
public class TicketService {

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
			throw new RuntimeException("user with name: " + userName + " does not exist.");
		}
		ticketDao.book(ticketId, userName);
	}

	public List<Ticket> getBookedTickets(String userName, FilterCriteria criteria) {
		if (!userService.isUserExists(userName)) {
			throw new RuntimeException("user with name: " + userName + " does not exist.");
		}
		return ticketDao.getBookedTickets(userName, criteria);
	}
}