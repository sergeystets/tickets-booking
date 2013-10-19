package epam.cdp.spring.service;

import java.util.Date;
import java.util.Set;

import epam.cdp.spring.bean.Ticket;
import epam.cdp.spring.bean.TicketCategory;
import epam.cdp.spring.exception.TicketServiceException;

public interface TicketService {

	public void book(String ticketId, String login) throws TicketServiceException;

	public Set<Ticket> getBookedTickets(String login, TicketCategory category, String title, Date date)
			throws TicketServiceException;

	public Set<Ticket> getAvailableTickets(TicketCategory category, String title, Date date)
			throws TicketServiceException;

	public Set<Ticket> getAvailableTickets() throws TicketServiceException;

	public Set<Ticket> getBookedTickets(String login) throws TicketServiceException;

}