package client.controle;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


/**
 * Handles requests for the application home page.
 */
@Controller
public class MultiController extends MultiActionController {

	private static final Logger logger = LoggerFactory.getLogger(MultiController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		return "/index";
	}
	
	
	@RequestMapping(value = "/test")
	public ModelAndView afficherLesClients(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "http://localhost:8080/webserviceRest/webservice/";
		
		Client c = Client.create();
		WebResource webResource = c.resource(url);
		String rep = webResource
						.path("json/product/get")
						.accept(MediaType.APPLICATION_JSON)
						.get(String.class);
		
		request.setAttribute("message", rep);
		
		return new ModelAndView("/test");
	}
}
