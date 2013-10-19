package epam.cdp.spring.task1.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;

import epam.cdp.spring.task1.exception.TicketServiceException;

public class BaseController {

	@ExceptionHandler(Exception.class)
	public void handleException(TicketServiceException ex, HttpServletResponse response)
			throws IOException {
		response.sendError(500, ex.getMessage());
	}
}