package epam.cdp.spring.task1.service;

import java.util.Date;
import java.util.Set;

import epam.cdp.spring.task1.bean.Ticket;
import epam.cdp.spring.task1.bean.TicketCategory;
import epam.cdp.spring.task1.dao.FilterCriteria;

public interface TicketService {

	public void book(String ticketId, String userName);

	public Set<Ticket> getBookedTickets(String userName, FilterCriteria criteria);

	public Set<Ticket> getAvailableTickets(TicketCategory category, String title, Date date);
	
	public Set<Ticket> getAvailableTickets();

}
