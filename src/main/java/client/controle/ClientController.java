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

import service.ClientService;
import service.NotValidException;

@Controller
public class ClientController {
	private static final Logger logger = LoggerFactory.getLogger(MultiController.class);
		
	@RequestMapping(value = "/client/delete/{id}")
	public String deleteClient(HttpServletRequest request, 
							   HttpServletResponse response, 
							   RedirectAttributes redirectAttrs, 
							   @PathVariable Integer id) throws Exception 
	{	
		try {
			ClientService serv = new ClientService(MultiController.baseUrlWS);
			serv.get("client/delete/"+String.valueOf(id));
			
			redirectAttrs.addFlashAttribute("messSuccess", "Le client "+id+" a bien été supprimé !");
		} catch (NotValidException e) {
			redirectAttrs.addFlashAttribute("messError", "Impossible de supprimer le client. Merci de supprimer les séjours avant.");
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("messError", "Un probleme est survenue!");
			System.out.println(e);
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
	public ModelAndView editClient(HttpServletRequest request, 
									HttpServletResponse response, 
									RedirectAttributes redAttr,
									@PathVariable Integer id) throws Exception 
	{
		String destination = "erreur";
		request.setAttribute("type", "modif");
		
		request = MultiController.dealWithFlashBagAttributes(request, redAttr);
		
		try {
			ClientService serv = new ClientService(MultiController.baseUrlWS);
			JSONObject obj = (JSONObject)serv.get("client/get/"+String.valueOf(id));
			client.model.Client cli = client.model.Client.createFromJSON(obj);
			
			request.setAttribute("client", cli);
			
			destination = "/client/modif";
		} catch (NotValidException e) {
			request.setAttribute("erreur", "Client introuvable");
		} catch (Exception e) {
			request.setAttribute("erreur", "Un probleme est survenue!");
			System.out.println(e);
		}
		
		return new ModelAndView(destination);
	}	
	
	@RequestMapping(value = "/client/persist")
	public String persistClient(HttpServletRequest request, 
								HttpServletResponse response,
								RedirectAttributes redAttr) throws Exception 
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
			MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
			formData.add("adrRueCli", adrRue);
			formData.add("cpCli", cp);
			formData.add("nomCli", nom);
			formData.add("numPieceCli", numPiece);
			formData.add("pieceCli", piece);
			formData.add("villeCli", ville);
								
			String path = type.contentEquals("modif") ? "client/edit/"+num : "client/add";
			new ClientService(MultiController.baseUrlWS).postForm(path, formData);
			
			if (type.contentEquals("modif"))
				redAttr.addFlashAttribute("messSuccess", "Le client "+nom+" a bien été enregistré !");
			else
				redAttr.addFlashAttribute("messSuccess", "Le client a bien été ajouté !");
		} catch(Exception e) {
			redAttr.addFlashAttribute("messError", "Une erreur est survenue, merci de contacter l'administrateur du site");
			System.out.println(e);
		}
		
		if (type.contentEquals("modif"))
			return "redirect:/client/edit/"+num;
		else
			return "redirect:/client/list";
	}
	
	@RequestMapping(value = "/client/list")
	public ModelAndView listClients(HttpServletRequest request, 
									HttpServletResponse response, 
									RedirectAttributes redAttr) throws Exception 
	{
		request = MultiController.dealWithFlashBagAttributes(request, redAttr);
		
		try {
			ClientService serv = new ClientService(MultiController.baseUrlWS);
			JSONArray jsonArray;
			
			String search = request.getParameter("nomClientSearch");
			if (search != null && !search.isEmpty())
			{	
				request.setAttribute("nomClientSearch", search);
				jsonArray = (JSONArray)serv.get("client/find/"+search);
			} else 
				jsonArray = (JSONArray)serv.get("client/getall");
			
			List<client.model.Client> mesClients = new ArrayList();
			
			for(Object o : jsonArray)
				mesClients.add(client.model.Client.createFromJSON((JSONObject)o));

			request.setAttribute("clients", mesClients);
		
			return new ModelAndView("/client/list");
		} catch (Exception e) {
			request.setAttribute("erreur", "Une erreur est survenue sur le chargement de la liste. Merci de reesayer plus tard.");
			e.printStackTrace();
			
			return new ModelAndView("erreur");
		}
	}
}
