package client.controle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
public class ClientController {
	private static final Logger logger = LoggerFactory.getLogger(MultiController.class);

	/*
	@RequestMapping(value = "/test.htm")
	public ModelAndView afficherLesClients(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String destinationPage;

		
		destinationPage = "/showMessage";

		return new ModelAndView(destinationPage);
	}*/
}
