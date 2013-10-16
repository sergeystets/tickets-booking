package epam.cdp.spring.task1.controller;

import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import epam.cdp.spring.task1.bean.Ticket;
import epam.cdp.spring.task1.bean.TicketCategory;
import epam.cdp.spring.task1.bean.User;
import epam.cdp.spring.task1.service.TicketService;

@Controller
public class TicketController {

	private static final Logger logger = Logger.getLogger(TicketController.class);

	private TicketService ticketService;

	@Autowired
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@RequestMapping("/book")
	public @ResponseBody
	String book(@RequestParam(value = "ticketId", required = true, defaultValue = "") String ticketId,
			HttpSession session) {
		logger.trace("booking");
		User user = (User) session.getAttribute("user");
		ticketService.book(ticketId, user.getLogin());
		return "{}";
	}

	@RequestMapping("/myTickets")
	public ModelAndView showMyTicketsPage(HttpSession session) {
		logger.trace("showing my tickets page");
		User user = (User) session.getAttribute("user");
		ModelAndView bookedTicketsPage = new ModelAndView("myTickets");
		Set<Ticket> availableTickets = ticketService.getBookedTickets(user.getLogin());
		bookedTicketsPage.addObject("bookedTickets", availableTickets);
		logger.trace("available tickets: " + availableTickets);
		return bookedTicketsPage;
	}

	@RequestMapping("/tickets")
	public ModelAndView showTicketsPage() {
		logger.trace("showing tickets page");
		ModelAndView ticketsPage = new ModelAndView("tickets");
		Set<Ticket> availableTickets = ticketService.getAvailableTickets();
		ticketsPage.addObject("availableTickets", availableTickets);
		logger.trace("available tickets: " + availableTickets);
		return ticketsPage;
	}

	@RequestMapping(value = "/availableTickets")
	public String getAvailableTickets(ModelMap model,
			@RequestParam(value = "category", required = false) TicketCategory category,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "date", required = false) Date date) {
		logger.trace("preparing available tickets..");
		Set<Ticket> availableTickets = ticketService.getAvailableTickets(category, title, date);
		model.addAttribute("availableTickets", availableTickets);
		logger.trace("available tickets are ready: " + availableTickets);
		return "availableTickets";
	}

	@RequestMapping(value = "/bookedTickets")
	public String getBookedTickets(HttpSession session,
			@RequestParam(value = "category", required = false) TicketCategory category,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "date", required = false) Date date, ModelMap model) {
		User user = (User) session.getAttribute("user");
		Set<Ticket> bookedTickets = ticketService.getBookedTickets(user.getLogin(), category, title, date);
		logger.trace("bookedTickets for user :" + user.getLogin() + " are ready: " + bookedTickets);
		model.addAttribute("bookedTickets", bookedTickets);
		return "bookedTickets";
	}
}
