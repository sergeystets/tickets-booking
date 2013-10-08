package epam.cdp.spring.task1.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);

	@RequestMapping("/")
	public String showWelcomePage() {
		logger.trace("index page");
		return "index";
	}
}
