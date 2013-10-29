package epam.cdp.spring.dao.impl.nosql;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import epam.cdp.spring.bean.FilmInfo;
import epam.cdp.spring.dao.FilmInfoDao;
import epam.cdp.spring.dao.FilmInfoFilterCriteria;
import epam.cdp.spring.dao.impl.util.FilmInfoMongoQueryBuilder;

@Repository
public class FilmInfoDaoMongoImpl implements FilmInfoDao {

	public static final String COLLECTION_NAME = "FilmInfo";

	@Autowired
	private MongoOperations mongoOperations;
	
	private FilmInfoMongoQueryBuilder queryBuilder;
	
	public FilmInfoDaoMongoImpl(){
		queryBuilder = new FilmInfoMongoQueryBuilder();
	}

	@Override
	public List<FilmInfo> getFilms(FilmInfoFilterCriteria filter) {
		Criteria cr = queryBuilder.build(filter);
		return mongoOperations.find(new Query(cr), FilmInfo.class);
		
	}

	@PostConstruct
	public void initStorage() {
		if (!mongoOperations.collectionExists(COLLECTION_NAME)) {
			List<String> actors = new ArrayList<String>();
			actors.add("Tom Hanks");
			actors.add("Robin Wright");
			FilmInfo forestGumpFilm = new FilmInfo("Forest Gump", "Paramount Pictures", actors,
					"Forrest Gump is a simple man with a low I.Q. "
							+ "but good intentions. He is running through childhood "
							+ "with his best and only friend Jenny. His 'mama' teaches him the ways of "
							+ "life and leaves him to choose his destiny. Forrest "
							+ "joins the army for service in Vietnam, finding new friends called Dan and Bubba,"
							+ " he wins medals, creates a famous shrimp fishing fleet, inspires people to jog,"
							+ " starts a ping-pong craze, create the smiley,"
							+ " write bumper stickers and songs, donating to people and meeting the "
							+ "president several times. However, "
							+ "this is all irrelevant to Forrest who can only think of his "
							+ "childhood sweetheart Jenny Curran. "
							+ "Who has messed up her life. Although in the end all he wants "
							+ "to prove is that anyone can love anyone.");
			mongoOperations.save(forestGumpFilm);

			actors = new ArrayList<String>();
			actors.add("Christian Bale");
			actors.add("Heath Ledger");
			FilmInfo darkKnightInfo = new FilmInfo("The Dark Knight", "Warner Bros. Pictures", actors,
					"When Batman, Gordon and Harvey Dent launch an assault on the mob, "
							+ "they let the clown out of the box, "
							+ "the Joker, bent on turning Gotham on itself and "
							+ "bringing any heroes down to his level.");
			mongoOperations.save(darkKnightInfo);
		}
	}	
}