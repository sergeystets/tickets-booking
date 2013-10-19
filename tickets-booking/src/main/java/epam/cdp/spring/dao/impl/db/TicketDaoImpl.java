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

	private static final String INSERT_BOOKED_TICKET = "INSERT INTO bookedTickets VALUES(?, ?)";

	private static final String GET_TICKET_BY_ID = "SELECT * FROM ticket WHERE id=?";

	private static final String GET_BOOKED_TICKETS = " SELECT ticket.id, ticket.title, ticket.dateTime, ticket.categoryId, ticket.place "
			+ "FROM ticket JOIN bookedTickets on ticket.id = bookedTickets.ticketId "
			+ "JOIN user on user.login = bookedTickets.userLogin WHERE user.login = ? ";

	private static final String GET_AVAILABLE_TICKETS = " SELECT * FROM ticket WHERE ticket.id NOT IN (SELECT ticketId from bookedTickets)";

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
		ensureTicketIdExists(ticketId);
		template.update(INSERT_BOOKED_TICKET, login, ticketId);
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

	private void ensureTicketIdExists(String ticketId) {
		List<Ticket> tickets = template.query(GET_TICKET_BY_ID, ticketMapper(), ticketId);
		if (tickets.isEmpty()) {
			throw new RuntimeException("ticket with id: " + ticketId + "does not exists");
		}
	}
}