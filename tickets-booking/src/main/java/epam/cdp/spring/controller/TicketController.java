package epam.cdp.spring.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import epam.cdp.spring.bean.Ticket;
import epam.cdp.spring.bean.TicketCategory;
import epam.cdp.spring.bean.User;
import epam.cdp.spring.exception.TicketServiceException;
import epam.cdp.spring.service.TicketService;

@Controller
public class TicketController extends BaseController {

	private static final Logger logger = Logger.getLogger(TicketController.class);

	private TicketService ticketService;

	@Autowired
	public void setTicketService(TicketService ticketService) {
		this.ticketService = ticketService;
	}

	@RequestMapping("/book")
	public @ResponseBody
	String book(@RequestParam(value = "ticketId", required = true, defaultValue = "") String ticketId,
			HttpSession session) throws TicketServiceException {
		User user = (User) session.getAttribute("user");
		ticketService.book(ticketId, user.getLogin());
		return "{}";
	}

	@RequestMapping("/bookedTickets")
	public ModelAndView showMyTicketsPage(HttpSession session) {
		User user = (User) session.getAttribute("user");
		ModelAndView bookedTicketsPage = new ModelAndView("bookedTickets");
		Set<Ticket> bookedTickets = ticketService.getBookedTickets(user.getLogin());
		bookedTicketsPage.addObject("bookedTicketsContent", bookedTickets);
		return bookedTicketsPage;
	}

	@RequestMapping("/availableTickets")
	public ModelAndView showTicketsPage() {
		ModelAndView ticketsPage = new ModelAndView("availableTickets");
		Set<Ticket> availableTickets = ticketService.getAvailableTickets();
		ticketsPage.addObject("availableTicketsContent", availableTickets);
		return ticketsPage;
	}

	@RequestMapping(value = "/availableTicketsContent")
	public String getAvailableTickets(ModelMap model,
			@RequestParam(value = "category", required = false) TicketCategory category,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "date", required = false) Date date) {
		Set<Ticket> availableTickets = ticketService.getAvailableTickets(category, title, date);
		logger.trace("available tickes: " +availableTickets);
		model.addAttribute("availableTicketsContent", availableTickets);
		return "availableTicketsContent";
	}

	@RequestMapping(value = "/bookedTicketsContent")
	public String getBookedTickets(HttpSession session,
			@RequestParam(value = "category", required = false) TicketCategory category,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "date", required = false) Date date, ModelMap model) {
		User user = (User) session.getAttribute("user");
		Set<Ticket> bookedTickets = ticketService.getBookedTickets(user.getLogin(), category, title, date);
		logger.trace("bookedTickets for user: '" + user.getLogin() + "' are ready: " + bookedTickets);
		model.addAttribute("bookedTicketsContent", bookedTickets);
		return "bookedTicketsContent";
	}

	@ExceptionHandler(TicketServiceException.class)
	public void handleTicketServiceException(TicketServiceException ex, HttpServletResponse response) throws IOException {
		response.sendError(400, ex.getMessage());
	}
}