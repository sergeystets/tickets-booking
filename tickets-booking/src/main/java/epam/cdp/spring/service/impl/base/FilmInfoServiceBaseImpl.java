package epam.cdp.spring.service.impl.base;

import java.util.ArrayList;
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
		title = prepareString(title);
		studio = prepareString(studio);
		actors = prepareList(actors);
		return filmInfoDao.getFilms(new FilmInfoFilterCriteria(title, actors, studio));
	}
	
	private List<String> prepareList(List<String> list){
		List<String>result = new ArrayList<String>();
		
		if (list != null && !list.isEmpty()){
			for (int i = 0; i < list.size(); i++){
				String el = list.get(i);
				el = prepareString(el);
				if (el != null && !el.isEmpty()){
					result.add(el);
				}
			}			
		}
		return result;
	}	
	
	private String prepareString(String param){
		if (param != null){
			param = param.trim();
			return param.replaceAll("\\s+", " ");
		}
		return param;
	}
}