package pdf.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import pdf.model.FactureSejour;
 
public class PDFBuilderFSejour extends AbstractITextPdfView {
 
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
        FactureSejour fs = (FactureSejour) model.get("factureSejour");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        doc.setPageSize(PageSize.A4);
        doc.setMargins(5, 5, 10, 10);       
        doc.addTitle("Facturation sejour");
              
        String html = "";
        html += "<div class='infoBand'>"
        		+ "<p class='bandName'>Cerisaie</p>"
        		+ "<p>Route de la plage</p>"
        		+ "<p>33121 - CARCANS</p>"
        		+ "<p>Etoiles : **</p>"
        		+ "<p>Telephone : 05 - 67 - 78 - 56 - 45</p>"
        		+ "<p>Fax : 05 - 67 - 78 - 34 - 25</p>"
        		+ "</div>"
        		+ "<div style='height:20px;'></div>"
        		+ "<div class='title'>"
        		+ "FACTURATION D'UN SEJOUR"
        		+ "</div>"
        		+ "<div style='height:30px;'></div>"
        		+ "<table style='width:100%;'>"
        		+ "<tr>"
        		+ "<td>Numero de facture : SE"+String.valueOf(fs.getNum())+"</td>"
        		+ "<td align=\"right\">Date de facturation : "+simpleDateFormat.format(fs.getDate())+"</td>"
        		+ "</tr>"
        		+ "</table>"
        		+ "<div style='height:10px;'></div>"
        		+ "<table>"
        		+ "<tr><td>Numero de sejour : </td><td>"+String.valueOf(fs.getSejour().getNum())+"</td></tr>"
        		+ "<tr><td width='200'>Numero d'emplacement : </td><td>"+String.valueOf(fs.getSejour().getEmplacement().getNum())+"</td></tr>"
				+ "<tr><td>Type d'emplacement : </td><td>"+fs.getSejour().getEmplacement().getType().getLib()+"</td></tr>"
        		+ "</table>"
        		+ "<div style='height:10px;'></div>"
        		+ "<div align='right'><b>Client : </b>"+fs.getSejour().getClient().getNom()+"</div>"
        		+ "<div style='height:20px;'></div>"
        		+ "<div>*SEJOUR*</div>"
        		+ "<div style='height:10px;'></div>"
        		+ "<table class='tableSej' align='center' style='text-align:center' border='1'>"
        		+ "<tr>"
        		+ "<td width='150'><b>Date de debut</b></td>"
        		+ "<td width='150'><b>Date de fin</b></td>"
        		+ "<td width='150'><b>Nbre de personnes</b></td>"
        		+ "<td width='150'><b>Prix/jour/personne</b></td>"
        		+ "</tr>"
        		+ "<tr>"
        		+ "<td>"+simpleDateFormat.format(fs.getSejour().getDateDeb())+"</td>"
        		+ "<td>"+simpleDateFormat.format(fs.getSejour().getDateFin())+"</td>"
        		+ "<td>"+String.valueOf(fs.getSejour().getNbPersonnes())+"</td>"
        		+ "<td>"+String.valueOf(fs.getSejour().getEmplacement().getType().getTarif())+"</td>"
        		+ "</tr>"
        		+ "</table>"
        		+ "<div style='height:40px;'></div>"
        		+ "<table align='right'>"
        		+ "<tr>"
        		+ "<td width='300'><b>Total a payer : </b></td>"
        		+ "<td style='text-align:right'>"+String.valueOf(fs.getPrixTotal())+"</td>"
        		+ "</tr>"
        		+ "</table>";
        
        
        String css = "";
        css += ".infoBand { font-size:12px; margin-bottom:10px; } "
        		+ ".bandName { color: blue; font-size: 20px; } "
        		+ ".title { text-align:center; font-size:30px; } "
        		+ ".tableSej td { padding:5px; }";
        
        
        InputStream inHtml = new ByteArrayInputStream(html.getBytes("UTF-8")); 
        InputStream inCss = new ByteArrayInputStream(css.getBytes("UTF-8")); 
        XMLWorkerHelper.getInstance().parseXHtml(writer, doc, inHtml, inCss);
        
    }
 
}