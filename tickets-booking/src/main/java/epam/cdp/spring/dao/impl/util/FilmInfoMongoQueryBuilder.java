package epam.cdp.spring.dao.impl.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;

import epam.cdp.spring.dao.FilmInfoFilterCriteria;

public class FilmInfoMongoQueryBuilder {

	public Criteria build(FilmInfoFilterCriteria filterCriteria) {
		String title = filterCriteria.getTitle();
		String studio = filterCriteria.getStudio();
		List<String> actors = filterCriteria.getActors();

		List<Criteria> criterion = new ArrayList<Criteria>();
		
		if (title != null && !title.isEmpty()) {
			criterion.add(new Criteria("title").is(title));
		}
		if (studio != null && !studio.isEmpty()) {
			criterion.add(new Criteria("studio").is(studio));
		}
		if (actors != null && !actors.isEmpty()) {
			criterion.add(new Criteria("actors").is(actors));
		}
		
		Criteria criteria = new Criteria(); 
		if (criterion.isEmpty()){
			return criteria;
		}

		return criteria.orOperator(criterion.toArray(new Criteria[criterion.size()]));
	}
}