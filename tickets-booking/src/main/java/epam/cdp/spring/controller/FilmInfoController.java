package epam.cdp.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import epam.cdp.spring.bean.FilmInfo;
import epam.cdp.spring.service.FilmInfoService;

@Controller
public class FilmInfoController {
	
	@Autowired
	FilmInfoService filmInfoService;
	
	@RequestMapping(value = "/filmInfo", method = RequestMethod.GET)
	public ModelAndView showLoginPage() {
		ModelAndView filmInfo = new ModelAndView("filmInfo");
		List<FilmInfo> filmInfoList = filmInfoService.getFilms(null, null, null);
		filmInfo.addObject("filmInfo", filmInfoList);		
		return filmInfo;
	}

}
