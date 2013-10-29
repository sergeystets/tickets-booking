package epam.cdp.spring.dao;

import java.util.List;

import epam.cdp.spring.bean.FilmInfo;

public interface FilmInfoDao {
	
	List<FilmInfo>getFilms(FilmInfoFilterCriteria filter);
}