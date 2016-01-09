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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


/**
 * Handles requests for the application home page.
 */
@Controller
public class MultiController extends MultiActionController {
	private static final Logger logger = LoggerFactory.getLogger(MultiController.class);
	
	//WEBSERVICE ADRESSE
	public static final String baseUrlWS = "http://localhost:8080/webserviceRest/webservice/";
	
	public static HttpServletRequest dealWithFlashBagAttributes(HttpServletRequest request, RedirectAttributes redAttr)
	{
		//recup flash bag attribute
		for (java.util.Map.Entry<String, ?> e : redAttr.getFlashAttributes().entrySet())
			request.setAttribute(e.getKey(), e.getValue());
		
		return request;
	}
	
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
		return "/home";
	}
}
