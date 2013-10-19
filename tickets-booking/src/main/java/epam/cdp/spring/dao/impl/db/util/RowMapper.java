package epam.cdp.spring.dao.impl.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import epam.cdp.spring.bean.Ticket;
import epam.cdp.spring.bean.TicketCategory;
import epam.cdp.spring.bean.User;

public class RowMapper {

	private static ParameterizedRowMapper<User> userMapper = new ParameterizedRowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {

			String login = rs.getString("login");
			String password = rs.getString("password");
			return new User(login, password);
		}
	};

	private static ParameterizedRowMapper<Ticket> ticketMapper = new ParameterizedRowMapper<Ticket>() {
		@Override
		public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
			String id = rs.getString("id");
			String title = rs.getString("title");
			Date date = rs.getDate("dateTime");
			TicketCategory category = TicketCategory.values()[rs.getInt("categoryId")];
			int place = rs.getInt("place");
			return new Ticket(id, title, date, category, place);

		}
	};

	public static ParameterizedRowMapper<User> userMapper() {
		return userMapper;
	}

	public static ParameterizedRowMapper<Ticket> ticketMapper() {
		return ticketMapper;
	}
}
