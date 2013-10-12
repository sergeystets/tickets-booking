package epam.cdp.spring.task1.dao.impl.db.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import epam.cdp.spring.task1.bean.TicketCategory;
import epam.cdp.spring.task1.dao.FilterCriteria;

@Component
public class SqlQueryBuilder {

	public PreparedStatement appendCriteriaToQuery(String baseQuery, FilterCriteria criteria, Connection conn,
			boolean containsWhereClause, Object ...params) throws SQLException {

		List<Object> parameters = new ArrayList<Object>(Arrays.asList(params));

		TicketCategory category = criteria.getCategory();
		Date date = criteria.getDate();
		String title = criteria.getTitle();

		if (date != null) {
			String condition = " dateTime <= ?";
			baseQuery = appendCondition(condition, baseQuery, containsWhereClause);
			parameters.add(date);
			containsWhereClause = true;
		}

		if (title != null) {
			String condition = " title = ?";
			baseQuery = appendCondition(condition, baseQuery, containsWhereClause);
			parameters.add(title);
			containsWhereClause = true;
		}

		if (category != null) {
			String condition = " categoryId = ? ";
			baseQuery = appendCondition(condition, baseQuery, containsWhereClause);
			parameters.add(category.ordinal());
			containsWhereClause = true;
		}

		PreparedStatement ps = conn.prepareStatement(baseQuery);
		for (int i = 0; i < parameters.size(); i++) {
			ps.setObject(i + 1, parameters.get(i));
		}

		return ps;
	}

	private String appendCondition(String condition, String query, boolean containtsCondition) {
		if (containtsCondition) {
			return query += " AND " + condition;
		} else {
			return query += " WHERE " + condition;
		}
	}
}