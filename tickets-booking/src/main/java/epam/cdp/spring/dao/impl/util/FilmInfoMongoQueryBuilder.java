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

		List<Criteria> criteriaList = new ArrayList<Criteria>();
		
		if (title != null && !title.isEmpty()) {
			criteriaList.add(new Criteria("title").is(title));
		}
		if (studio != null && !studio.isEmpty()) {
			criteriaList.add(new Criteria("studio").is(studio));
		}
		if (actors != null && !actors.isEmpty()) {
			List<Criteria> actorsCriterion = new ArrayList<>();
			Criteria actorCriteria = new Criteria();
			for (String actor: actors){
				actorsCriterion.add(new Criteria("actors").is(actor));
			}
			actorCriteria.andOperator(actorsCriterion.toArray(new Criteria[actorsCriterion.size()]));
			criteriaList.add(actorCriteria);			
		}
		
		Criteria finalCreiteria = new Criteria(); 
		if (criteriaList.isEmpty()){
			return finalCreiteria;
		}

		return finalCreiteria.orOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
	}
}