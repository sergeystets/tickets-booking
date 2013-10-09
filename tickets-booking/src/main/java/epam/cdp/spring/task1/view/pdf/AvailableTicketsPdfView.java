package epam.cdp.spring.task1.view.pdf;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import epam.cdp.spring.task1.bean.Ticket;

@Component
public class AvailableTicketsPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		Set<Ticket> availableTickets = (Set<Ticket>) model.get("availableTickets");

		Table table = new Table(5);
		table.addCell("id");
		table.addCell("title");
		table.addCell("date");
		table.addCell("category");
		table.addCell("place");

		for (Ticket ticket : availableTickets) {
			table.addCell(ticket.getId());
			table.addCell(ticket.getTitle());
			table.addCell("" + ticket.getDate());
			table.addCell("" + ticket.getCategory());
			table.addCell("" + ticket.getPlace());
		}
		document.add(table);
	}
}