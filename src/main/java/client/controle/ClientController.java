package client.controle;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

@Controller
public class ClientController {
	private static final Logger logger = LoggerFactory.getLogger(MultiController.class);
		
	@RequestMapping(value = "/client/delete/{id}")
	public String deleteClient(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) throws Exception 
	{
		try {
			Client c = Client.create();
			WebResource webResource = c.resource(MultiController.baseUrlWS);
			webResource
					.path("client/delete/"+String.valueOf(id))
					.accept(MediaType.APPLICATION_JSON)
					.get(Object.class);
			
			request.setAttribute("messSuccess", "Le client "+id+" a bien été supprimé !");
		} catch(Exception e) {
			request.setAttribute("messError", "Impossible de suppimer ce client");
		}
				
		return "redirect:/client/list";
	}	
	
	@RequestMapping(value = "/client/add")
	public ModelAndView addClient(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		request.setAttribute("type", "ajout");
		
		return new ModelAndView("/client/modif");
	}	
	
	@RequestMapping(value = "/client/edit/{id}")
	public ModelAndView editClient(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer id) throws Exception 
	{
		request.setAttribute("type", "modif");
		
		Client c = Client.create();
		WebResource webResource = c.resource(MultiController.baseUrlWS);
		String rep = webResource
								.path("client/get/"+String.valueOf(id))
								.accept(MediaType.APPLICATION_JSON)
								.get(String.class);
		
		client.model.Client cli = client.model.Client.createFromJSON(new JSONObject(rep));
		request.setAttribute("client", cli);
		
		return new ModelAndView("/client/modif");
	}	
	
	@RequestMapping(value = "/client/persist")
	public String persistClient(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String type = request.getParameter("type");
		String num = request.getParameter("num");
		String nom = request.getParameter("nom");
		if (nom.length() > 30)
			nom = nom.substring(0, 30);
		String adrRue = request.getParameter("adrRue");
			if (adrRue.length() > 40)
				adrRue = adrRue.substring(0, 40);
		String cp = request.getParameter("cp");
			if (cp.length() > 5)
				cp = cp.substring(0, 5);
		String piece = request.getParameter("piece");
			if (piece.length() > 10)
				piece = piece.substring(0, 10);
		String ville = request.getParameter("ville");
			if (ville.length() > 40)
				ville = ville.substring(0, 40);
		String numPiece = request.getParameter("numPiece");
			if (numPiece.length() > 40)
				numPiece = numPiece.substring(0, 20);
		
		try {
			Client c = Client.create();
			
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("adrRueCli", adrRue);
			formData.add("cpCli", cp);
			formData.add("nomCli", nom);
			formData.add("numPieceCli", numPiece);
			formData.add("pieceCli", piece);
			formData.add("villeCli", ville);
								
			c.resource(MultiController.baseUrlWS)
				.path(type.contentEquals("modif") ? "client/edit/"+num : "client/add")
				.type("application/x-www-form-urlencoded")
				.post(ClientResponse.class, formData);
			
			request.setAttribute("messSuccess", "Le client "+nom+" a bien été enregistré !");
		} catch(Exception e) {
			request.setAttribute("messError", "Une erreur est survenue, merci de contacter l'administrateur du site");
			System.out.println(e);
		}
		
		if (type.contentEquals("modif"))
			return "redirect:/client/edit/"+num;
		else
			return "redirect:/client/list";
	}
	
	@RequestMapping(value = "/client/list")
	public ModelAndView listClients(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		String search = request.getParameter("nomClientSearch");
		if (search == null || search.isEmpty())
		{
			
		}
		
		Client c = Client.create();
		WebResource webResource = c.resource(MultiController.baseUrlWS);
		String rep = webResource
						.path("client/getall")
						.accept(MediaType.APPLICATION_JSON)
						.get(String.class);
		
		JSONArray jsonArray = new JSONArray(rep);
		List<client.model.Client> mesClients = new ArrayList();
		
		for(Object o : jsonArray)
			mesClients.add(client.model.Client.createFromJSON((JSONObject)o));

		request.setAttribute("clients", mesClients);
		
		return new ModelAndView("/client/list");
	}
}
