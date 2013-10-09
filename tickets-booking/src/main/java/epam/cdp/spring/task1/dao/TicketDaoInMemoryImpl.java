package epam.cdp.spring.task1.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Repository;

import epam.cdp.spring.task1.bean.Ticket;
import epam.cdp.spring.task1.bean.TicketCategory;

@Repository
public class TicketDaoInMemoryImpl implements TicketDao {

	private Map<String, Ticket> tickets;

	private Map<String, Set<Ticket>> bookedTikcets;

	public TicketDaoInMemoryImpl() {
		tickets = new HashMap<String, Ticket>();
		bookedTikcets = new HashMap<String, Set<Ticket>>();
		Ticket t0 = new Ticket("0", "Terminator Salvation", new Date(), TicketCategory.STANDARD, 10);
		Ticket t1 = new Ticket("1", "Saw 4", new Date(1000), TicketCategory.BAR, 4);
		Ticket t2 = new Ticket("2", "Forrest Gump", new Date(1000), TicketCategory.PREMIUM, 13);
		tickets.put(t0.getId(), t0);
		tickets.put(t1.getId(), t1);
		tickets.put(t2.getId(), t2);

		Set<Ticket> adminTickets = new TreeSet<Ticket>();
		adminTickets.add(t0);
		bookedTikcets.put("admin", adminTickets);
	}

	public synchronized void book(String ticketId, String userName) {
		Ticket ticket = tickets.get(ticketId);
		if (ticket == null) {
			throw new RuntimeException("no ticket with ticketId: " + ticketId);
		}

		Set<Ticket> bookedTickets = bookedTikcets.get(userName);
		if (bookedTickets == null) {
			bookedTickets = new TreeSet<Ticket>();
		}
		bookedTickets.add(ticket);
	}

	public synchronized Set<Ticket> getBookedTickets(String userName, FilterCriteria criteria) {
		return bookedTikcets.get(userName);
	}

	@Override
	public Set<Ticket> getAvailableTickets(FilterCriteria criteria) {
		Set<Ticket> bookedTickets = new TreeSet<Ticket>();
		for (Set<Ticket> tickets : bookedTikcets.values()) {
			bookedTickets.addAll(tickets);
		}
		Set<Ticket> availableTickets = new TreeSet<Ticket>(tickets.values());
		availableTickets.removeAll(bookedTickets);
		return availableTickets;
	}
}