package epam.cdp.spring.dao.impl.map;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import epam.cdp.spring.bean.Ticket;
import epam.cdp.spring.bean.TicketCategory;
import epam.cdp.spring.bean.User;
import epam.cdp.spring.dao.FilterCriteria;
import epam.cdp.spring.dao.TicketDao;

//@Repository
public class TicketDaoMapImpl implements TicketDao {

	private Map<String, Ticket> tickets;

	private Map<String, Set<Ticket>> bookedTikcets;

	private static final Logger logger = Logger.getLogger(TicketDaoMapImpl.class);

	public TicketDaoMapImpl() {
		tickets = new HashMap<String, Ticket>();
		bookedTikcets = new HashMap<String, Set<Ticket>>();
		Ticket t0 = new Ticket("0", "Terminator Salvation", new Date(), TicketCategory.STANDARD, 10);
		Ticket t4 = new Ticket("4", "King Lion",new Date(), TicketCategory.STANDARD, 10);
		Ticket t5 = new Ticket("5", "Film", new Date(), TicketCategory.STANDARD, 10);
		Ticket t6 = new Ticket("6", "Terminator 3", new Date(), TicketCategory.STANDARD, 10);
		Ticket t1 = new Ticket("1", "Saw 4",new Date(), TicketCategory.BAR, 4);
		Ticket t2 = new Ticket("2", "Forrest Gump", new Date(), TicketCategory.PREMIUM, 13);
		tickets.put(t0.getId(), t0);
		tickets.put(t1.getId(), t1);
		tickets.put(t2.getId(), t2);
		tickets.put(t4.getId(), t4);
		tickets.put(t5.getId(), t5);
		tickets.put(t6.getId(), t6);

		Set<Ticket> adminTickets = new TreeSet<Ticket>();
		t0.setUser(new User("admin", "admin"));
		adminTickets.add(t0);
		bookedTikcets.put("admin", adminTickets);
	}

	public synchronized void book(String ticketId, User user) {
		Ticket ticket = tickets.get(ticketId);
		if (ticket == null) {
			throw new RuntimeException("no ticket with ticketId: " + ticketId);
		}

		Set<Ticket> bookedTickets = bookedTikcets.get(user.getLogin());
		if (bookedTickets == null) {
			bookedTickets = new TreeSet<Ticket>();
		}
		bookedTickets.add(ticket);
	}

	@Override
	public synchronized Set<Ticket> getBookedTickets(User user, FilterCriteria criteria) {
		return bookedTikcets.get(user);
	}

	@Override
	public Set<Ticket> getAvailableTickets(FilterCriteria criteria) {
		Set<Ticket> bookedTickets = new TreeSet<Ticket>();
		for (Set<Ticket> tickets : bookedTikcets.values()) {
			bookedTickets.addAll(tickets);
		}
		Set<Ticket> availableTickets = new TreeSet<Ticket>(tickets.values());
		availableTickets.removeAll(bookedTickets);
		return filter(availableTickets, criteria);
	}

	/**
	 * Method for filter tickets. It is a temporary solution. Will be removed
	 * when working with DB.
	 * 
	 * @param ticketsToFilter
	 * @param criteria
	 * @return
	 */
	public Set<Ticket> filter(Set<Ticket> ticketsToFilter, FilterCriteria criteria) {
		logger.trace("filter criteria: " + criteria);
		Date date = criteria.getDate();
		String title = criteria.getTitle();
		TicketCategory category = criteria.getCategory();
		Set<Ticket> result = new TreeSet<Ticket>();
		for (Ticket ticket : ticketsToFilter) {
			if (date != null) {
				if (ticket.getDate().after(date)) {
					continue;
				}
			}
			if (title != null && !title.isEmpty()) {
				if (!ticket.getTitle().toLowerCase().contains(title.toLowerCase())) {
					continue;
				}
			}
			if (category != null) {
				if (!category.equals(ticket.getCategory())) {
					continue;
				}
			}
			result.add(ticket);
		}
		return result;
	}
}