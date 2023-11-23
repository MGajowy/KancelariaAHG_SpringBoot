package pl.kancelaria.AHG.administration.dto.order;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import pl.kancelaria.AHG.administration.dto.ExportPDF;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderPDFExport implements ExportPDF {

    private final List<OrderDTO> listOrders;

    public OrderPDFExport(List<OrderDTO> listOrders) {
        this.listOrders = listOrders;
    }

    @Override
    public void writeTableHeader(PdfPTable table) {
        PdfPCell pdfPCell = new PdfPCell();
        pdfPCell.setBackgroundColor(Color.BLUE);
        pdfPCell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        pdfPCell.setPhrase(new Phrase("Nazwisko", font));
        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Imie", font));
        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Typ sprawy", font));
        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Data przyjecia", font));
        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Data zakonczenia", font));
        table.addCell(pdfPCell);
        pdfPCell.setPhrase(new Phrase("Telefon", font));
        table.addCell(pdfPCell);
    }

    @Override
    public void writeTableData(PdfPTable table) {
        for (OrderDTO orderDTO : listOrders) {
            table.addCell(orderDTO.getSurname());
            table.addCell(orderDTO.getName());
            table.addCell(orderDTO.getCaseType());
            table.addCell(orderDTO.getDateOfAdmission().toString());
            table.addCell(orderDTO.getEndDate() != null ? orderDTO.getEndDate().toString() : "W toku");
            table.addCell(orderDTO.getPhoneNumber());
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

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.BLACK);
        font.setSize(18);


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDateTime = dateFormat.format(new Date());
        Paragraph title = new Paragraph("Zlecenia klientów", fontTitle);
        Paragraph date = new Paragraph(currentDateTime, fontTitle);
        title.setAlignment(Paragraph.ALIGN_CENTER);
        date.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(title);
        document.add(date);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        table.setWidths(new float[]{4.0f, 4.0f, 4.0f, 4.0f, 4.0f, 4.0f});
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
