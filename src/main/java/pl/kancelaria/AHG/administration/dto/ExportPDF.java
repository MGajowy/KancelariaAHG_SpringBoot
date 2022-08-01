package pl.kancelaria.AHG.administration.dto;

import com.lowagie.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExportPDF {
    void writeTableHeader(PdfPTable table);

    void writeTableData(PdfPTable table);

    void export(HttpServletResponse response) throws IOException;
}
