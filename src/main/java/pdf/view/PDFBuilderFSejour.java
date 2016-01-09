package pdf.view;

import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pdf.model.FactureSejour;
 
/**
 * This view class generates a PDF document 'on the fly' based on the data
 * contained in the model.
 * @author www.codejava.net
 *
 */
public class PDFBuilderFSejour extends AbstractITextPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        FactureSejour fs = (FactureSejour) model.get("factureSejour");

        doc.setPageSize(PageSize.A4);
        doc.setMargins(5, 5, 10, 10);
        
       	
                 
        Paragraph p = new Paragraph();
        p.setIndentationLeft(0);
        Paragraph pTitle = new Paragraph("Cerisaie");
        Font f = FontFactory.getFont(FontFactory.TIMES_BOLD);
        pTitle.setFont(f);
        p.add(pTitle);
        p.add(new Paragraph("Route de la plage"));
        p.add(new Paragraph("33121 - CARCANS"));
        p.add(new Paragraph("Etoiles : **"));
        p.add(new Paragraph("Téléphone : 05 - 67 - 78 - 56 - 45"));
        p.add(new Paragraph("Fax : 05 - 67 - 78 - 34 - 25"));
        p.add(new Paragraph(""));
        doc.add(p);
               
        Paragraph titre = new Paragraph("FACTURATION D'UN SEJOUR");
        titre.setAlignment(Paragraph.ALIGN_CENTER);        
        doc.add(titre);
        
        Paragraph numFac = new Paragraph("Numero de facture SE"+String.valueOf(fs.getNum()));
        numFac.setAlignment(numFac.ALIGN_LEFT);
        doc.add(numFac);
        
        Paragraph numpres = new Paragraph("Date de facture SE");
        numFac.setAlignment(numpres.ALIGN_LEFT);
        doc.add(numFac);
        
        /* 
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 2.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);
         
        // define font for table header row
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);
         
        // define table header cell
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setPadding(5);
         
        // write table header 
        cell.setPhrase(new Phrase("Book Title", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Author", font));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("ISBN", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Published Date", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);
         
        // write table row data
        for (Book aBook : listBooks) {
            table.addCell(aBook.getTitle());
            table.addCell(aBook.getAuthor());
            table.addCell(aBook.getIsbn());
            table.addCell(aBook.getPublishedDate());
            table.addCell(String.valueOf(aBook.getPrice()));
        }
         
        doc.add(table);*/
         
    }
 
}