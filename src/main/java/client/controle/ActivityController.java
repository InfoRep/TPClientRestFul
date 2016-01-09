package client.controle;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.jersey.core.util.MultivaluedMapImpl;

import client.model.Client;
import client.model.Emplacement;
import client.model.Sejour;
import client.model.Sport;
import service.ClientService;
import service.NotValidException;

@Controller
public class ActivityController {
	private static final Logger logger = LoggerFactory.getLogger(MultiController.class);
		
	private List<Sport> getAllSports() throws Exception
	{
		//get all emplacement
		ClientService serv = new ClientService(MultiController.baseUrlWS);
		JSONArray jsonArray = (JSONArray)serv.get("/sport/getall");
		
		List<Sport> sports = new ArrayList();
		for(Object o : jsonArray)
			sports.add(Sport.createFromJSON((JSONObject)o));
		
		return sports;
	}
	
	
	@RequestMapping(value = "/activity/add")
	public ModelAndView addActivity(HttpServletRequest request, 
									HttpServletResponse response) throws Exception 
	{
		request.setAttribute("sports", this.getAllSports());
		request.setAttribute("sejours", SejourController.getAllSejours());
		
		return new ModelAndView("/activity/add");
	}	
		
	@RequestMapping(value = "/activity/persist")
	public String persistSejour(HttpServletRequest request, 
								HttpServletResponse response,
								RedirectAttributes redAttr) throws Exception 
	{
		String sejour = request.getParameter("sejour");
		String sport = request.getParameter("sport");
		String date = request.getParameter("date");
		String nbUnite = request.getParameter("nbUnite");
						
		try {	
			int nbU = Integer.valueOf(nbUnite);
			if (nbU < 0)
				nbU = 0;
					
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("sejour", sejour);
			formData.add("sport", sport);
			formData.add("date", date);
			formData.add("nbUnite", String.valueOf(nbU));
								
			new ClientService(MultiController.baseUrlWS).postForm("activite/add/", formData);
			
			redAttr.addFlashAttribute("messSuccess", "L'activité a bien été ajoutée !");
		} catch(Exception e) {
			redAttr.addFlashAttribute("messError", "Une erreur est survenue, merci de contacter l'administrateur du site");
			System.out.println(e);
		}
				
		return "redirect:/sejour/list";
	}
}

