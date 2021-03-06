package epam.cdp.spring.view.pdf;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import epam.cdp.spring.bean.Ticket;

@Component
public class BookedTicketsPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		Set<Ticket> bookedTickets = (Set<Ticket>) model.get("bookedTicketsContent");

		PdfPTable table = new PdfPTable(5);

		table.getDefaultCell().setFixedHeight(35);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

		Paragraph p = new Paragraph("Booked tickets");
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		document.add(Chunk.NEWLINE);

		table.addCell("#");
		table.addCell("title");
		table.addCell("date");
		table.addCell("category");
		table.addCell("place");

		int count = 1;
		for (Ticket ticket : bookedTickets) {
			table.addCell("" + count++);
			table.addCell(ticket.getTitle());
			table.addCell("" + ticket.getDate());
			table.addCell("" + ticket.getCategory());
			table.addCell("" + ticket.getPlace());
		}
		document.add(table);
	}

}