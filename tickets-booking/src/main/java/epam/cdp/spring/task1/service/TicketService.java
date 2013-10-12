package epam.cdp.spring.task1.service;

import java.util.Date;
import java.util.Set;

import epam.cdp.spring.task1.bean.Ticket;
import epam.cdp.spring.task1.bean.TicketCategory;

public interface TicketService {

	public void book(String ticketId, String login);

	public Set<Ticket> getBookedTickets(String login, TicketCategory category, String title, Date date);

	public Set<Ticket> getAvailableTickets(TicketCategory category, String title, Date date);
	
	public Set<Ticket> getAvailableTickets();
	
	public Set<Ticket> getBookedTickets(String login);

}