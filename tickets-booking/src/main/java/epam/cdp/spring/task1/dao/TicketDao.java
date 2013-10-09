package epam.cdp.spring.task1.dao;

import java.util.Set;

import epam.cdp.spring.task1.bean.Ticket;

public interface TicketDao {

	public void book(String ticketId, String userName);

	public Set<Ticket> getBookedTickets(String userName, FilterCriteria criteria);

	public Set<Ticket> getAvailableTickets(FilterCriteria criteria);
}