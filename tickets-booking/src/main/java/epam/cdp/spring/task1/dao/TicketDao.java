package epam.cdp.spring.task1.dao;

import java.util.List;

import epam.cdp.spring.task1.bean.Ticket;

public interface TicketDao {

	public void book(String ticketId, String userName);

	public List<Ticket> getBookedTickets(String userName, FilterCriteria criteria);
}