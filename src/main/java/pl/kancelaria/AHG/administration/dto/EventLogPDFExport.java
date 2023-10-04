package pl.kancelaria.AHG.administration.dto;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventLogPDFExport implements ExportPDF {
    private List<EventLogDTO> listEvent;

    public EventLogPDFExport(List<EventLogDTO> listEvent) {
        this.listEvent = listEvent;
    }

    @Override
    public void writeTableHeader(PdfPTable table) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBackgroundColor(Color.BLUE);
        pdfPCell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        pdfPCell.setPhrase(new Phrase("Nazwa czynnosci", font));
        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Uzytkownik", font));
        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Data logu", font));
        table.addCell(pdfPCell);
    }

    @Override
    public void writeTableData(PdfPTable table) {
        for (EventLogDTO eventLogDTO : listEvent) {
            table.addCell(eventLogDTO.getAction());
            table.addCell(eventLogDTO.getUserName());
            table.addCell(eventLogDTO.getDateAction().toString());
        }
    }

    @Override
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLUE);
        font.setSize(18);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDateTime = dateFormat.format(new Date());
        Paragraph title = new Paragraph("Dziennik zdarzeń dla aplikacji AHG", font);
        Paragraph date = new Paragraph(currentDateTime, font);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        date.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        document.add(date);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{6.0f, 2.0f, 4.0f});
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
