package epam.cdp.spring.service.impl.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import epam.cdp.spring.bean.FilmInfo;
import epam.cdp.spring.dao.FilmInfoDao;
import epam.cdp.spring.dao.FilmInfoFilterCriteria;
import epam.cdp.spring.service.FilmInfoService;

@Repository
public class FilmInfoServiceBaseImpl implements FilmInfoService {

	@Autowired
	private FilmInfoDao filmInfoDao;

	@Override
	public List<FilmInfo> getFilms(String title, List<String> actors, String studio) {
		return filmInfoDao.getFilms(new FilmInfoFilterCriteria(title, actors, studio));
	}	
}
