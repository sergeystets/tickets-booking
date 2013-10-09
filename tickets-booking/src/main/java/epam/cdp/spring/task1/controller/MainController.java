package epam.cdp.spring.task1.controller;

import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import epam.cdp.spring.task1.bean.Ticket;
import epam.cdp.spring.task1.dao.FilterCriteria;
import epam.cdp.spring.task1.service.TicketService;

@Controller
public class MainController {

	private static final Logger logger = Logger.getLogger(MainController.class);

	private TicketService ticketService;

	@Autowired
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@RequestMapping("/")
	public String showLoginPage() {
		logger.trace("showing login page");
		return "login";
	}

	@RequestMapping("/tickets")
	public ModelAndView showTicketsPage() {
		logger.trace("showing tickets page");
		ModelAndView ticketsPage = new ModelAndView("tickets");
		Set<Ticket> availableTickets = ticketService.getAvailableTickets(new FilterCriteria());
		String ticketsOwner = "admin";
		Set<Ticket> bookedTickets = ticketService.getBookedTickets(ticketsOwner, new FilterCriteria());
		ticketsPage.addObject("availableTickets", availableTickets);
		ticketsPage.addObject("bookedTickets", bookedTickets);
		ticketsPage.addObject("ticketsOwner", ticketsOwner);
		return ticketsPage;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam(value = "login", required = false) String login,
			@RequestParam(value = "password", required = false) String password) {
		logger.info("user with login: " + login + " logged in succesfully");
		return "redirect:tickets";
	}

	@RequestMapping(value = "/availableTickets")
	public String getAvailableTickets(ModelMap model) {
		logger.trace("preparing available tickets..");
		Set<Ticket> availableTickets = ticketService.getAvailableTickets(new FilterCriteria());
		model.addAttribute("availableTickets", availableTickets);
		logger.trace("available tickets are ready");
		return "availableTickets";
	}
}