package epam.cdp.spring.task1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import epam.cdp.spring.task1.bean.RegistrationUserBean;
import epam.cdp.spring.task1.bean.User;
import epam.cdp.spring.task1.service.UserService;

@Controller
public class RegistrationController {

	private static final Logger logger = Logger.getLogger(RegistrationController.class);

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String shorRegistrationPage() {
		logger.trace("showing registration page started...");
		return "registration";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid RegistrationUserBean userBean, BindingResult result, HttpServletRequest request) {
		logger.trace("showing registration page started...");
		if (result.hasErrors()) {
			logger.error(result.getAllErrors());
			request.setAttribute("errors", result.getAllErrors());
			return "registration";
		}

		List<String> errors = new ArrayList<String>();
		if (!userBean.getPassword().equals(userBean.getPasswordRepeat())) {
			errors.add("passwords do not match");
			request.setAttribute("errors", errors);
			logger.error(errors);
			return "registration";
		}

		User user = new User(userBean.getLogin(), userBean.getPassword());
		try {
			userService.register(user);
		} catch (Exception e) {
			logger.error(e);
			errors.add(e.getMessage());
			request.setAttribute("errors", errors);
			return "registration";
		}

		return "redirect:complete";
	}

	@RequestMapping(value = "/registration/checkLogin", method = RequestMethod.POST)
	public @ResponseBody
	String register(@RequestParam(value = "login", required = false, defaultValue = "") String login) {
		logger.trace("login to check: " + login);
		boolean userExists = userService.isUserExists(login);
		if (userExists) {
			logger.error("user with login " + login + " exists");
			return "{\"error\":\"user with such login already exists\"}";
		}
		logger.trace("user with login " + login + " does not exist.");
		return "{}";
	}

	@RequestMapping("/complete")
	public String showCompletePage() {
		logger.trace("showing complete page");
		return "complete";
	}
}
