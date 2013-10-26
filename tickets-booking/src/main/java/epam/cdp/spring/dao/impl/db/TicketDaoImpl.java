package epam.cdp.spring.dao.impl.db;

import static epam.cdp.spring.dao.impl.db.util.RowMapper.ticketMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import epam.cdp.spring.bean.Ticket;
import epam.cdp.spring.dao.FilterCriteria;
import epam.cdp.spring.dao.TicketDao;
import epam.cdp.spring.dao.impl.db.util.SqlQueryBuilder;

@Repository
public class TicketDaoImpl implements TicketDao {

	private JdbcTemplate template;

	private static final String GET_TICKET_BY_ID = "SELECT * FROM ticket WHERE id=?";

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
	public void book(String ticketId, String login) {
		Ticket ticket = getTicket(ticketId);
		if (ticket == null) {
			throw new RuntimeException("ticket with id: " + ticketId + " does not exists");
		}

		ensureTicketIsNotAlreadyBooked(ticket, login);
		template.update(BOOK_TICKET, login, ticketId);
	}

	private Ticket getTicket(String ticketId) {
		List<Ticket> tickets = template.query(GET_TICKET_BY_ID, ticketMapper(), ticketId);
		if (tickets != null && !tickets.isEmpty()) {
			return tickets.get(0);
		}
		return null;
	}

	private void ensureTicketIsNotAlreadyBooked(Ticket ticket, String login) {
		String currentOwner = ticket.getUserLogin();
		if (currentOwner != null && !currentOwner.equals(login)) {
			throw new RuntimeException("ticket with id: " + ticket.getId() + " is alreay booked.");
		}
	}

	@Override
	public Set<Ticket> getBookedTickets(final String login, final FilterCriteria criteria) {
		List<Ticket> tickets = template.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				return queryBuilder.appendCriteriaToQuery(GET_BOOKED_TICKETS, criteria, connection, true, login);
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