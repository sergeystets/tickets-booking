package epam.cdp.spring.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import epam.cdp.spring.bean.FilmInfo;
import epam.cdp.spring.service.FilmInfoService;

@Controller
public class FilmInfoController {

	private static final Logger logger = Logger.getLogger(FilmInfoController.class);

	@Autowired
	private FilmInfoService filmInfoService;

	@RequestMapping(value = "/filmInfo", method = RequestMethod.GET)
	public ModelAndView showLoginPage(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "studio", required = false) String studio,
			@RequestParam(value = "actors", required = false) List<String> actors) {
		logger.info("title: "+title);
		logger.info("actors: "+actors);
		logger.info("studio: "+studio);
		ModelAndView filmInfo = new ModelAndView("filmInfo");
		List<FilmInfo> filmInfoList = filmInfoService.getFilms(title, actors, studio);
		filmInfo.addObject("filmInfo", filmInfoList);
		return filmInfo;
	}

}
