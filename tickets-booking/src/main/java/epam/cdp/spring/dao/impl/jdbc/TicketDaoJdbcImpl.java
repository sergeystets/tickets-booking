package epam.cdp.spring.dao.impl.jdbc;

import static epam.cdp.spring.dao.impl.util.RowMapper.ticketMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import epam.cdp.spring.bean.Ticket;
import epam.cdp.spring.bean.User;
import epam.cdp.spring.dao.FilterCriteria;
import epam.cdp.spring.dao.TicketDao;
import epam.cdp.spring.dao.impl.util.SqlQueryBuilder;

//@Repository
public class TicketDaoJdbcImpl implements TicketDao {

	private JdbcTemplate template;

	private static final String GET_TICKET_BY_ID = "SELECT * FROM ticket JOIN User on ticket.userLogin = user.login WHERE id=?";

	private static final String BOOK_TICKET = "UPDATE ticket SET userLogin = ? where id = ?";

	private static final String GET_BOOKED_TICKETS = "SELECT * FROM ticket JOIN user on userLogin = login WHERE login = ?";

	private static final String GET_AVAILABLE_TICKETS = "SELECT * FROM ticket WHERE userLogin is NULL";

	private SqlQueryBuilder queryBuilder;

	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Autowired
	public void setQueryBuilder(SqlQueryBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

	@Override
	public void book(String ticketId, User user) {
		Ticket ticket = getTicket(ticketId);
		if (ticket == null) {
			throw new RuntimeException("ticket with id: " + ticketId + " does not exists");
		}

		ensureTicketIsNotAlreadyBooked(ticket, user);
		template.update(BOOK_TICKET, user.getLogin(), ticketId);
	}

	private Ticket getTicket(String ticketId) {
		List<Ticket> tickets = template.query(GET_TICKET_BY_ID, ticketMapper(), ticketId);
		if (tickets != null && !tickets.isEmpty()) {
			return tickets.get(0);
		}
		return null;
	}

	private void ensureTicketIsNotAlreadyBooked(Ticket ticket, User user) {
		User currentOwner = ticket.getUser();
		if (currentOwner != null && !currentOwner.equals(user)) {
			throw new RuntimeException("ticket with id: " + ticket.getId() + " is alreay booked.");
		}
	}

	@Override
	public Set<Ticket> getBookedTickets(final User user, final FilterCriteria criteria) {
		List<Ticket> tickets = template.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				return queryBuilder.appendCriteriaToQuery(GET_BOOKED_TICKETS, criteria, connection, true, user.getLogin());
			}
		}, ticketMapper());

		return new TreeSet<Ticket>(tickets);
	}

	@Override
	public Set<Ticket> getAvailableTickets(final FilterCriteria criteria) {
		List<Ticket> tickets = template.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				return queryBuilder.appendCriteriaToQuery(GET_AVAILABLE_TICKETS, criteria, connection, true);
			}
		}, ticketMapper());

		return new TreeSet<Ticket>(tickets);
	}

}