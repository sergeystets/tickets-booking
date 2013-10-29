package epam.cdp.spring.bean;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

public class FilmInfo {

	@NotBlank(message = "title can not be blank")
	private String title;

	@NotBlank(message = "studio can not be blank")
	private String studio;

	@NotBlank(message = "actors can not be blank")
	private List<String> actors;

	private String description;

	public FilmInfo() {

	}

	public FilmInfo(String title, String studio, List<String> actors, String description) {
		this.title = title;
		this.studio = studio;
		this.actors = actors;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudio() {
		return studio;
	}

	public void setStudio(String studio) {
		this.studio = studio;
	}

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = actors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "FilmInfo [title=" + title + ", studio=" + studio + ", actors=" + actors + ", description="
				+ description + "]";
	}
}
