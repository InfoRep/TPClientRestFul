package pdf.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import client.model.Activite;
import pdf.model.FacturePrestations;

public class PDFBuilderPrestations extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc,
            PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // get data model which is passed by the Spring container
    	FacturePrestations fs = (FacturePrestations) model.get("facturePrest");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        doc.setPageSize(PageSize.A4);
        doc.setMargins(5, 5, 10, 10);       
        doc.addTitle("Facturation prestations sejour");
              
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
        		+ "FACTURATION DES PRESTATIONS<br /> SPORTIVES"
        		+ "</div>"
        		+ "<div style='height:30px;'></div>"
        		+ "<table style='width:100%;'>"
        		+ "<tr>"
        		+ "<td>Numero de facture : SO"+String.valueOf(fs.getNum())+"</td>"
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
        		+ "<div>*PRESTATIONS SPORTIVES*</div>"
        		+ "<div style='height:10px;'></div>"
        		+ "<table class='tableSej' align='center' style='text-align:center' border='1'>"
        		+ "<tr>"
        		+ "<td width='150'><b>Date de sport</b></td>"
        		+ "<td width='150'><b>Nom du sport</b></td>"
        		+ "<td width='150'><b>Prix/unite</b></td>"
        		+ "<td width='150'><b>Nombre d'unites</b></td>"
        		+ "<td width='150'><b>Montant</b></td>"
        		+ "</tr>";
        
        for (Activite a : fs.getActivites())
        {
        	double montant = a.getNbloc() * a.getSport().getTarif();
        	html += "<tr>"
        			+ "<td>"+simpleDateFormat.format(a.getDateJour())+"</td>"
        			+ "<td>"+a.getSport().getLibelle()+"</td>"
        			+ "<td>"+String.valueOf(a.getSport().getTarif())+"</td>"
        			+ "<td>"+String.valueOf(a.getNbloc())+"</td>"
        			+ "<td>"+String.valueOf(montant)+"</td>"
        			+ "</tr>";
        }
        
        html   += "</table>"
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
