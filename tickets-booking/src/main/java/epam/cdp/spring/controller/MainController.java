package epam.cdp.spring.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController extends BaseController  {

	private static final Logger logger = Logger.getLogger(MainController.class);

	@RequestMapping("/")
	public String showStartPage() {
		logger.trace("showing index page");
		return "login";
	}
}