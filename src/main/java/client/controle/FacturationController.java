package client.controle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import client.model.Activite;
import client.model.Sejour;
import client.model.Sport;
import pdf.model.FacturePrestations;
import pdf.model.FactureSejour;
import service.ClientService;

@Controller
public class FacturationController {
	private static final Logger logger = LoggerFactory.getLogger(FacturationController.class);
	
	@RequestMapping(value = "/facture/sejour/{id}")
	public ModelAndView pdfSejour(HttpServletRequest request, 
									HttpServletResponse response,
									@PathVariable Integer id) throws Exception 
	{		
		try {
			ClientService serv = new ClientService(MultiController.baseUrlWS);
			
			//get sejour
			JSONObject obj = (JSONObject)serv.get("sejour/get/"+String.valueOf(id));
			Sejour sej = Sejour.createFromJSON(obj);
			
			//get prix total from web service
			JSONObject fac = (JSONObject)serv.get("facture/sejour/"+String.valueOf(id));
			double prixTotal = fac.getDouble("prixTotal");
			
			FactureSejour fs = new FactureSejour(sej.getNum(), sej, new Date(), prixTotal);
		
			return new ModelAndView("pdfViewFactureSejour", "factureSejour", fs);
		} catch (Exception e) {
			request.setAttribute("erreur", "Une erreur est survenue pendant la facturation. Merci de reesayer plus tard.");
			e.printStackTrace();
			
			return new ModelAndView("erreur");
		}
	}
	
	@RequestMapping(value = "/facture/prestations/{id}")
	public ModelAndView pdfPrestations(HttpServletRequest request, 
									HttpServletResponse response,
									@PathVariable Integer id) throws Exception 
	{		
		try {
			ClientService serv = new ClientService(MultiController.baseUrlWS);
			
			//get sejour
			JSONObject obj = (JSONObject)serv.get("sejour/get/"+String.valueOf(id));
			Sejour sej = Sejour.createFromJSON(obj);
			
			//get prix total from web service
			JSONObject fac = (JSONObject)serv.get("facture/prestations/"+String.valueOf(id));
			double prixTotal = fac.getDouble("prixTotal");
			
			//activites
			JSONArray jsonArray  = (JSONArray)serv.get("activite/get/bySejour/"+String.valueOf(id));
			List<Activite> activites = new ArrayList();
			for(Object o : jsonArray)
				activites.add(Activite.createFromJSON((JSONObject)o));
			
			FacturePrestations fp = new FacturePrestations(sej.getNum(), sej, new Date(), activites, prixTotal);
		
			return new ModelAndView("pdfViewFacturePrestations", "facturePrest", fp);
		} catch (Exception e) {
			request.setAttribute("erreur", "Une erreur est survenue pendant la facturation. Merci de reesayer plus tard.");
			e.printStackTrace();
			
			return new ModelAndView("erreur");
		}
	}
	
	@RequestMapping(value = "/facture/sejour")
	public ModelAndView pdfSejour(HttpServletRequest request, 
									HttpServletResponse response) throws Exception 
	{		
		try {
			request.setAttribute("sejours", SejourController.getAllSejours());
			return new ModelAndView("facture/sejour");			
		} catch(Exception e) {
			request.setAttribute("erreur", "Une erreur est survenue pendant le chargement des donnees. Merci de reesayer plus tard.");
			return new ModelAndView("erreur");
		}		
	}
	
	@RequestMapping(value = "/facture/prestations")
	public ModelAndView pdfPrestations(HttpServletRequest request, 
									HttpServletResponse response) throws Exception 
	{		
		try {
			request.setAttribute("sejours", SejourController.getAllSejours());
			return new ModelAndView("facture/prestations");			
		} catch(Exception e) {
			request.setAttribute("erreur", "Une erreur est survenue pendant le chargement des donnees. Merci de reesayer plus tard.");
			return new ModelAndView("erreur");
		}		
	}
}
