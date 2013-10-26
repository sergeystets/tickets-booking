package epam.cdp.spring.bean;

import java.util.Date;

public class Ticket implements Comparable<Ticket> {

	private String id;

	private String title;

	private Date date;

	private TicketCategory category;

	private int place;

	private String userLogin;

	public Ticket() {
	}

	public Ticket(String id, String title, Date date, TicketCategory category, int place, String userLogin) {
		this.id = id;
		this.title = title;
		this.date = date;
		this.category = category;
		this.place = place;
		this.userLogin = userLogin;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TicketCategory getCategory() {
		return category;
	}

	public void setCategory(TicketCategory category) {
		this.category = category;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", title=" + title + ", date=" + date + ", category=" + category + ", place="
				+ place + ", userLogin=" + userLogin + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Ticket t) {
		return id.compareTo(t.getId());
	}

}
