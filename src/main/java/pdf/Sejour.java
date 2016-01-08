package pdf;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class Sejour {

	  public static void generateSejour(Document FacturationSejour, Sejour sej) {

	    Document document = new Document(PageSize.A4);
	    
	    try {
	    	PdfWriter writer = PdfWriter.getInstance(document,
	          new FileOutputStream("c:/FacturationSejour.pdf"));
	    	writer.setViewerPreferences(PdfWriter.PageLayoutSinglePage | PdfWriter.PageModeUseThumbs);
	      
	      document.addTitle("Facturation d'un séjour");
	      document.addCreationDate();
	      document.open();
	      
	      // theme de la page
	      BaseFont theme = BaseFont.createFont(
	    		  "C:/Windows/FONTS/ARIAL.TTF",
	              BaseFont.CP1252,
	              BaseFont.NOT_EMBEDDED);
	          Font themeSejour = new Font(theme);
	          themeSejour.setColor(color.black);
	          themeSejour.setStyle(Font.BOLD);
	          themeSejour.setSize(38.0f);
	          
	      // Titre    
	      document.add(new Paragraph("Numéro de facture", themeSejour));    
	      
	      // Tableau de presentation
	      Paragraph parPres = new Paragraph("Numéro du séjour");
	      parPres.setAlignment(Element.ALIGN_RIGHT);
	      Table tabPresentation = new Table(2, 3);
	      tabPresentation.setAutoFillEmptyCells(false);
	      tabPresentation.setPadding(2);
	      document.add(tabPresentation);
	      
	      // Client
	      Paragraph parClient = new Paragraph("Client");
	      parClient.setAlignment(Element.ALIGN_LEFT);
	      document.add(parClient);
	      
	      // Tableau du séjour
	      Paragraph parSejour = new Paragraph("Séjour");
	      Table tableau = new Table(2, 4);
          tableau.setAutoFillEmptyCells(true);
          tableau.setPadding(2);

          Cell cell = new Cell("Date de début");
          cell.setHeader(true);
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          tableau.addCell(cell);

          cell = new Cell("Date de fin");
          cell.setHeader(true);
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          tableau.addCell(cell);
          tableau.endHeaders();
          
          Cell cell = new Cell("Nbre de personnes");
          cell.setHeader(true);
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          tableau.addCell(cell);

          cell = new Cell("Prix/jour/personne");
          cell.setHeader(true);
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          tableau.addCell(cell);
          tableau.endHeaders();

          cell = new Cell("1.1");
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          tableau.addCell(cell);

          tableau.addCell();
          tableau.addCell();
          tableau.addCell();
	          		
	      document.add(tableau);    
	      
	      // Total
	      Paragraph parTotal = new Paragraph("Total à payer");
	      parTotal.setAlignment(Element.ALIGN_LEFT);
	      document.add(parTotal);

	    } catch (DocumentException de) {
	      de.printStackTrace();
	    } catch (IOException ioe) {
	      ioe.printStackTrace();
	    }

	    document.close();
	  }
}
