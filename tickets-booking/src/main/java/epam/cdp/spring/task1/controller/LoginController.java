package epam.cdp.spring.task1.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import epam.cdp.spring.task1.bean.User;
import epam.cdp.spring.task1.controller.response.ControllerResponse;
import epam.cdp.spring.task1.exception.TicketServiceException;
import epam.cdp.spring.task1.exception.UserServiceException;
import epam.cdp.spring.task1.service.UserService;

@Controller
public class LoginController extends BaseController {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		logger.trace("showing login page");
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "login", required = true) String login,
			@RequestParam(value = "password", required = true) String password, HttpSession session,
			HttpServletRequest request) {
		User user = userService.login(login, password);
		if (user == null) {
			logger.info("user with login: " + login + " does not exist");
			request.setAttribute("errorMessage", "user with such login and password does not exist");
			return "login";
		} else {
			session.setAttribute("user", user);
			logger.info("user with login: " + login + " logged in succesfully");
			return "redirect:tickets";
		}
	}
	
	@ExceptionHandler(UserServiceException.class)
	 private ControllerResponse getLoginErrorResponse(TicketServiceException ex, HttpServletResponse response) throws IOException{
		response.sendError(400, ex.getMessage());
		ControllerResponse errorResponse = new ControllerResponse();
		errorResponse.setError(true);
		errorResponse.setMessage(ex.getMessage());
	
		return errorResponse;		
	}	
}