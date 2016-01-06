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
import service.ClientService;
import service.NotValidException;

@Controller
public class SejourController {
	private static final Logger logger = LoggerFactory.getLogger(MultiController.class);
	
	@RequestMapping(value = "/sejour/list")
	public ModelAndView listSejours(HttpServletRequest request, 
									HttpServletResponse response, 
									RedirectAttributes redAttr) throws Exception 
	{
		request = MultiController.dealWithFlashBagAttributes(request, redAttr);
		
		try {
			ClientService serv = new ClientService(MultiController.baseUrlWS);
			JSONArray jsonArray;
			
			String search = request.getParameter("idSejourSearch");
			if (search != null && !search.isEmpty())
			{	
				request.setAttribute("idSejourSearch", search);
				jsonArray = new JSONArray();
				try {
					jsonArray.put(serv.get("sejour/get/"+search));
				} catch(NotValidException e) {}
			} else 
				jsonArray = (JSONArray)serv.get("sejour/getall");
			
			List<Sejour> mesSejours = new ArrayList();
			
			for(Object o : jsonArray)
				mesSejours.add(Sejour.createFromJSON((JSONObject)o));

			request.setAttribute("sejours", mesSejours);
		
			return new ModelAndView("/sejour/list");
		} catch (Exception e) {
			request.setAttribute("erreur", "Une erreur est survenue sur le chargement de la liste. Merci de reesayer plus tard.");
			e.printStackTrace();
			
			return new ModelAndView("erreur");
		}
	}
	
	@RequestMapping(value = "/sejour/delete/{id}")
	public String deleteClient(HttpServletRequest request, 
							   HttpServletResponse response, 
							   RedirectAttributes redirectAttrs, 
							   @PathVariable Integer id) throws Exception 
	{	
		try {
			ClientService serv = new ClientService(MultiController.baseUrlWS);
			serv.get("sejour/delete/"+String.valueOf(id));
			
			redirectAttrs.addFlashAttribute("messSuccess", "Le sejour "+id+" a bien été supprimé !");
		} catch (NotValidException e) {
			redirectAttrs.addFlashAttribute("messError", "Impossible de supprimer le sejour. Merci de supprimer les emplacements avant.");
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("messError", "Un probleme est survenue!");
			System.out.println(e);
		}
				
		return "redirect:/sejour/list";
	}
	
	private List<Emplacement> getAllEmplacements() throws Exception
	{
		//get all emplacement
		ClientService serv = new ClientService(MultiController.baseUrlWS);
		JSONArray jsonArray = (JSONArray)serv.get("/emplacement/getall");
		
		List<Emplacement> emplacements = new ArrayList();
		for(Object o : jsonArray)
			emplacements.add(Emplacement.createFromJSON((JSONObject)o));
		
		return emplacements;
	}
	
	private List<Client> getAllClients() throws Exception
	{
		//get all emplacement
		ClientService serv = new ClientService(MultiController.baseUrlWS);
		JSONArray jsonArray = (JSONArray)serv.get("/client/getall");
		
		List<Client> clients = new ArrayList();
		for(Object o : jsonArray)
			clients.add(Client.createFromJSON((JSONObject)o));
		
		return clients;
	}
	
	@RequestMapping(value = "/sejour/add")
	public ModelAndView addSejour(HttpServletRequest request, 
									HttpServletResponse response) throws Exception 
	{
		request.setAttribute("type", "ajout");
		
		//get all emplacement
		List<Emplacement> emplacements = this.getAllEmplacements();
		request.setAttribute("emplacements", emplacements);
		
		return new ModelAndView("/sejour/modif");
	}	
	
	@RequestMapping(value = "/sejour/edit/{id}")
	public ModelAndView editSejour(HttpServletRequest request, 
									HttpServletResponse response, 
									RedirectAttributes redAttr,
									@PathVariable Integer id) throws Exception 
	{
		String destination = "erreur";
		request.setAttribute("type", "modif");
		
		request = MultiController.dealWithFlashBagAttributes(request, redAttr);
		
		try {
			ClientService serv = new ClientService(MultiController.baseUrlWS);
			JSONObject obj = (JSONObject)serv.get("sejour/get/"+String.valueOf(id));
			Sejour sej = Sejour.createFromJSON(obj);
			
			//get all emplacement
			List<Emplacement> emplacements = this.getAllEmplacements();
			request.setAttribute("emplacements", emplacements);
			
			request.setAttribute("client", sej);
			
			destination = "/sejour/modif";
		} catch (NotValidException e) {
			request.setAttribute("erreur", "Sejour introuvable");
		} catch (Exception e) {
			request.setAttribute("erreur", "Un probleme est survenue!");
			System.out.println(e);
		}
		
		return new ModelAndView(destination);
	}	
	
	@RequestMapping(value = "/sejour/persist")
	public String persistSejour(HttpServletRequest request, 
								HttpServletResponse response,
								RedirectAttributes redAttr) throws Exception 
	{
		String type = request.getParameter("type");
		String num = request.getParameter("num");
		String dateDeb = request.getParameter("dateDeb");
		String dateFin = request.getParameter("dateFin");
		String emplacement = request.getParameter("emplacement");
		String client = request.getParameter("client");
		String nbPersonnes = request.getParameter("nbPersonnes");
				
		try {			
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("datedebSej", dateDeb);
			formData.add("dateFinSej", dateFin);
			formData.add("nbPersonnes", nbPersonnes);
			formData.add("client", client);
			formData.add("emplacement", emplacement);
								
			String path = type.contentEquals("modif") ? "sejour/edit/"+num : "sejour/add";
			new ClientService(MultiController.baseUrlWS).postForm(path, formData);
			
			redAttr.addFlashAttribute("messSuccess", "Le sejour "+num+" a bien été enregistré !");
		} catch(Exception e) {
			redAttr.addFlashAttribute("messError", "Une erreur est survenue, merci de contacter l'administrateur du site");
			System.out.println(e);
		}
		
		if (type.contentEquals("modif"))
			return "redirect:/sejour/edit/"+num;
		else
			return "redirect:/sejour/list";
	}
}

