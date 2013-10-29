package epam.cdp.spring.dao;

import java.util.List;

public class FilmInfoFilterCriteria {

	private String title;

	private List<String> actors;

	private String studio;

	public String getTitle() {
		return title;
	}

	public FilmInfoFilterCriteria() {

	}

	public FilmInfoFilterCriteria(String title, List<String> actors, String studio) {
		this.title = title;
		this.actors = actors;
		this.studio = studio;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	@Override
	public String toString() {
		return "FilmInfoFilterCriteria [title=" + title + ", actors=" + actors + ", studio=" + studio + "]";
	}

}
