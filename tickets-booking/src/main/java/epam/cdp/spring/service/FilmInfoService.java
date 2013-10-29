package epam.cdp.spring.service;

import java.util.List;

import epam.cdp.spring.bean.FilmInfo;

public interface FilmInfoService {	
	
	List<FilmInfo>getFilms(String title, List<String > actors, String studio);

}
