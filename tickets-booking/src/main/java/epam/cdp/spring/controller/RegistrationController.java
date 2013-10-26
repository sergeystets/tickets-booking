package epam.cdp.spring.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import epam.cdp.spring.bean.UserRegistrationBean;
import epam.cdp.spring.bean.User;
import epam.cdp.spring.controller.response.ControllerResponse;
import epam.cdp.spring.exception.TicketServiceException;
import epam.cdp.spring.exception.UserServiceException;
import epam.cdp.spring.service.UserService;

@Controller
public class RegistrationController extends BaseController {

	private static final Logger logger = Logger.getLogger(RegistrationController.class);

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/registration")
	public String shorRegistrationPage() {
		return "registration";
	}

	@RequestMapping("/complete")
	public String showCompletePage() {
		return "complete";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid UserRegistrationBean userBean, BindingResult result, HttpServletRequest request) {
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
		} catch (UserServiceException ex) {
			logger.error(ex);
			errors.add(ex.getMessage());
			request.setAttribute("errors", errors);
			return "registration";
		}

		return "redirect:complete";
	}

	@RequestMapping(value = "/registration/checkLogin", method = RequestMethod.POST)
	public ControllerResponse register(@RequestParam(value = "login", required = false, defaultValue = "") String login) {
		logger.trace("login to check: " + login);
		ControllerResponse response = new ControllerResponse();

		boolean userExists = userService.isUserExists(login);
		if (userExists) {
			logger.error("user with login " + login + " exists");
			response.setError(true);
			response.setMessage("user with such login already exists");
		}

		logger.trace("user with login " + login + " does not exist.");
		return response;
	}

	@ExceptionHandler(UserServiceException.class)
	public void handleUserServiceException(TicketServiceException ex, HttpServletResponse response) throws IOException {
		response.sendError(400, ex.getMessage());
	}
}