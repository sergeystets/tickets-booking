package epam.cdp.spring.dao;

import java.util.Date;

import epam.cdp.spring.bean.TicketCategory;

public class TicketFilterCriteria {

	private TicketCategory category;

	private Date date;

	private String title;

	public TicketCategory getCategory() {
		return category;
	}

	public void setCategory(TicketCategory category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "FilterCriteria [category=" + category + ", date=" + date + ", title=" + title + "]";
	}
}
