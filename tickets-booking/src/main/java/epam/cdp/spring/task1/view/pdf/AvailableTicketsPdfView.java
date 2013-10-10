package epam.cdp.spring.task1.view.pdf;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
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
		table.setAlignment(Element.ALIGN_CENTER);
		table.setWidth(100);

		table.addCell(getCenteredCell("id"));
		table.addCell(getCenteredCell("title"));
		table.addCell(getCenteredCell("date"));
		table.addCell(getCenteredCell("category"));
		table.addCell(getCenteredCell("place"));

		for (Ticket ticket : availableTickets) {
			table.addCell(getCenteredCell(ticket.getId()));
			table.addCell(getCenteredCell(ticket.getTitle()));
			table.addCell(getCenteredCell("" + ticket.getDate()));
			table.addCell(getCenteredCell("" + ticket.getCategory()));
			table.addCell(getCenteredCell("" + ticket.getPlace()));
		}
		document.add(table);
	}

	private Cell getCenteredCell(String text) {
		Cell cell = new Cell(text);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		return cell;
	}

}