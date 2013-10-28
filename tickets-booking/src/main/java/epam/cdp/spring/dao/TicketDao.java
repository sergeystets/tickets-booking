package epam.cdp.spring.dao;

import java.util.Set;

import epam.cdp.spring.bean.Ticket;
import epam.cdp.spring.bean.User;

public interface TicketDao {

	public void book(String ticketId, User user);

	public Set<Ticket> getBookedTickets(User user, FilterCriteria criteria);

	public Set<Ticket> getAvailableTickets(FilterCriteria criteria);
}